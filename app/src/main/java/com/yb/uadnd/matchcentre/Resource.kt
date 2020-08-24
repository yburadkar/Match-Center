package com.yb.uadnd.matchcentre

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? = null
)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}