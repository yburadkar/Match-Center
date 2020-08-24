package com.yb.uadnd.matchcentre

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 * This class accepts only one data type by design.
 * Any mapping between the api model and the ui model should be done outside of this resource file,
 * typically in a mapper object inside a ViewModel or a Repository.
 *
 * A [Resource] is modelled in 3 different states - Resource.loading, Resource.error, Resource.success.
 * Each state stores different meta-data, depending on the use case.
 *
 * Logic overview:
 * 1. Load data from database.
 * 2. Before any operation, send a loading resource to the emitter with no data.
 * 3. If database fetch is successful, check if you should fetch fresh data from the network.
 * 4. If you should fetch data, perform a network call, otherwise send the db data in a success resource and complete.
 * 5. If network call is successful, sync the database with the new data, emit the response in a success resource, and terminate.
 * 6. If fetch has failed, return an error resource.
 *
 *
 * @param T Generic request/result data type.
 * @property emitter Emitter that emits resource values to the class that's responsible of handling the [Resource].
 * @property ioScheduler ioScheduler to perform network calls.
 * @property uiScheduler uiScheduler to observe the result in the main thread.
 * @property compScheduler computation scheduler to database operations.
 */
abstract class NetworkBoundResource<T>(
    private val emitter: ObservableEmitter<Resource<T>>,
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler,
    private val compScheduler: Scheduler
) {
    private val disposables = CompositeDisposable()

    init {
        loadFromDb()
            .doOnSubscribe {
                emitter.onNext(Resource.loading(null))
            }
            .doOnSuccess { dbResource ->
                if (shouldFetch(dbResource.data)) {
                    if (!dbResource.isError) emitter.onNext(dbResource)
                    fetchFromNetwork(dbResource.data)
                        .subscribeBy(onSuccess = {
                            emitter.onNext(it)
                            emitter.onComplete()
                        })
                        .addTo(disposables)
                } else {
                    val finalResource = if (dbResource.isError) dbResource else Resource.success(dbResource.data)
                    emitter.onNext(finalResource)
                    emitter.onComplete()
                }
            }
            .subscribe()
            .let {
                disposables.add(it)
            }

        emitter.setCancellable { disposables.clear() }
    }

    /**
     * Stream always returns a [Resource], either of loading or error type.
     */
    private fun loadFromDb(): Single<Resource<T>> =
            getLocal()
                .map {
                    Resource.loading(it)
                }
                .onErrorReturn {
                    Resource.error(null, it)
                }
                .subscribeOn(compScheduler)
                .observeOn(uiScheduler)

    /**
     * Stream always returns a [Resource] in the subscribe block, either of success, or error type.
     */
    private fun fetchFromNetwork(dbData: T?): Single<Resource<T>> =
            getRemote()
                .map {
                    Resource.success(it)
                }
                .doOnSuccess {
                    saveCallResult(it.data)
                }
                .onErrorReturn {
                    Resource.error(dbData, it)
                }
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)

    abstract fun getLocal(): Single<T>

    abstract fun getRemote(): Single<T>

    abstract fun shouldFetch(data: T?): Boolean

    abstract fun saveCallResult(data: T?)
}

/**
 * Helper function to initalise a [NetworkBoundResource].
 *
 * @param T Generic request/result data type.
 * @param getLocal Get local data actions.
 * @param getRemote Get remote data actions.
 * @param saveCallResult Save network response to database actions..
 * @param shouldFetch Condition whether to fetch new data from the network or not.
 */
fun <T> networkBoundResource(
    getLocal: () -> Single<T>,
    getRemote: () -> Single<T>,
    saveCallResult: (data: T?) -> Unit,
    shouldFetch: (data: T?) -> Boolean = { true }
): Observable<Resource<T>> = Observable.create<Resource<T>> { emitter ->
    emitter.let {
        object : NetworkBoundResource<T>(emitter, Schedulers.io(), AndroidSchedulers.mainThread(), Schedulers.computation()) {
            override fun getLocal(): Single<T> = getLocal.invoke()

            override fun getRemote(): Single<T> = getRemote.invoke()

            override fun shouldFetch(data: T?): Boolean = shouldFetch.invoke(data)

            override fun saveCallResult(data: T?) = saveCallResult.invoke(data)

        }
    }
}