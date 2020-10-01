package com.yb.uadnd.matchcentre.data.remote.models

import com.yb.uadnd.matchcentre.domain.Booking
import com.yb.uadnd.matchcentre.domain.Goal
import com.yb.uadnd.matchcentre.domain.Match
import com.yb.uadnd.matchcentre.domain.MatchData
import com.yb.uadnd.matchcentre.domain.MatchEvent
import com.yb.uadnd.matchcentre.domain.MatchTeam
import com.yb.uadnd.matchcentre.domain.Player
import com.yb.uadnd.matchcentre.domain.Substitution
import com.yb.uadnd.matchcentre.domain.TeamPlayer
import com.yb.uadnd.matchcentre.domain.TeamStats

class ApiMatch(
    override val data: ApiMatchData? = null
) : Match

class ApiMatchData(
    override val id: String? = null,
    override val homeTeam: ApiMatchTeam? = null,
    override val awayTeam: ApiMatchTeam? = null,
    override val events: List<ApiMatchEvent>? = null
) : MatchData

class ApiMatchTeam(
    override val id: String? = null,
    override val name: String? = null,
    override val players: List<ApiTeamPlayer>? = null,
    override val teamStats: ApiTeamStats? = null,
    override val imageUrl: String? = null
) : MatchTeam

class ApiTeamPlayer(
    override val id: Int = 0,
    override val firstName: String? = null,
    override val lastName: String? = null,
    override val position: String? = null,
    override val shirtNumber: Int = 0
) : TeamPlayer

class ApiTeamStats(
    override val cornersWon: Int = 0,
    override val possession: Float = 0.0F,
    override val saves: Int = 0,
    override val shotsOnGoal: Int = 0,
    override val shotsOnTarget: Int = 0,
    override val substitutionsMade: Int = 0
) : TeamStats

class ApiMatchEvent(
    override val time: String? = null,
    override val teamId: String? = null,
    override val type: String? = null,
    override val goalDetails: ApiGoal? = null,
    override val bookingDetails: ApiBooking? = null,
    override val substitutionDetails: ApiSubstitution? = null,
    override var teamImageUrl: String? = null
) : MatchEvent

class ApiGoal(
    override val player: ApiPlayer? = null,
    override val type: String? = null
) : Goal

class ApiPlayer(
    override val firstName: String? = null,
    override val lastName: String? = null
) : Player

class ApiBooking(
    override val player: ApiPlayer? = null,
    override val type: String? = null
) : Booking

class ApiSubstitution(
    override val playerSubOff: ApiPlayer? = null,
    override val playerSubOn: ApiPlayer? = null,
    override val reason: String? = null
) : Substitution
