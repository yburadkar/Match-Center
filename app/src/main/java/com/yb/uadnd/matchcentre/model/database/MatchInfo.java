package com.yb.uadnd.matchcentre.model.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "matchInfo")
public class MatchInfo {
    String id;
    @PrimaryKey(autoGenerate = false)
    int matchId;
    private String homeTeamName;
    private String homeTeamId;
    private int homeScore;
    private String awayTeamName;
    private String awayTeamId;
    private int awayScore;
    int competitionId;
    private String competition;

    @Ignore
    public MatchInfo() {
    }

    public MatchInfo(String id, int matchId, String homeTeamName, String homeTeamId, int homeScore, String awayTeamName, String awayTeamId, int awayScore, int competitionId, String competition) {
        this.id = id;
        this.matchId = matchId;
        this.homeTeamName = homeTeamName;
        this.homeTeamId = homeTeamId;
        this.homeScore = homeScore;
        this.awayTeamName = awayTeamName;
        this.awayTeamId = awayTeamId;
        this.awayScore = awayScore;
        this.competitionId = competitionId;
        this.competition = competition;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getCompetition() {
        return competition;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }
}
