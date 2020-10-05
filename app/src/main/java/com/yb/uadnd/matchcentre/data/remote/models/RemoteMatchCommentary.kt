package com.yb.uadnd.matchcentre.data.remote.models

import com.yb.uadnd.matchcentre.domain.models.MatchCommentary

class RemoteMatchCommentary (
    override val feedMatchId: Int = 0,
    override val homeTeamName: String? = null,
    override val homeScore: Int = 0,
    override val awayTeamName: String? = null,
    override val awayScore: Int = 0,
    override val competition: String? = null,
    override val commentaryEntries: List<ApiCommentaryEntry>? = null,
    override val homeTeamImageUrl: String? = null,
    override val awayTeamImageUrl: String? = null,
    override val lastRefreshed: Long
) : MatchCommentary {

    constructor(commentaryData: ApiCommentaryData?) : this(
        feedMatchId = commentaryData?.feedMatchId ?: 0,
        homeTeamName = commentaryData?.homeTeamName,
        homeScore = commentaryData?.homeScore ?: 0,
        awayTeamName = commentaryData?.awayTeamName,
        awayScore = commentaryData?.awayScore ?: 0,
        competition = commentaryData?.competition,
        commentaryEntries = commentaryData?.commentaryEntries,
        homeTeamImageUrl = commentaryData?.homeTeamImageUrl,
        awayTeamImageUrl = commentaryData?.awayTeamImageUrl,
        lastRefreshed = System.currentTimeMillis() / 1000
    )

}