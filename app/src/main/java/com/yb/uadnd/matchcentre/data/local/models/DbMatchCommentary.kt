package com.yb.uadnd.matchcentre.data.local.models

import com.yb.uadnd.matchcentre.domain.models.MatchCommentary

class DbMatchCommentary(
    override val feedMatchId: Int = 0,
    override val homeTeamName: String? = null,
    override val homeScore: Int = 0,
    override val awayTeamName: String? = null,
    override val awayScore: Int = 0,
    override val competition: String? = null,
    override val commentaryEntries: List<DbComment>? = null,
    override val homeTeamImageUrl: String? = null,
    override val awayTeamImageUrl: String? = null,
    override val lastRefreshed: Long
): MatchCommentary {

    constructor(dbMatchInfoComments: DbMatchInfoComments) : this(
        dbMatchInfoComments.matchInfo.matchId,
        dbMatchInfoComments.matchInfo.homeTeamName,
        dbMatchInfoComments.matchInfo.homeScore,
        dbMatchInfoComments.matchInfo.awayTeamName,
        dbMatchInfoComments.matchInfo.awayScore,
        dbMatchInfoComments.matchInfo.competition,
        dbMatchInfoComments.comments,
        dbMatchInfoComments.matchInfo.homeTeamImageUrl,
        dbMatchInfoComments.matchInfo.awayTeamImageUrl,
        dbMatchInfoComments.matchInfo.lastRefreshed
    )

}