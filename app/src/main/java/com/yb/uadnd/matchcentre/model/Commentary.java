package com.yb.uadnd.matchcentre.model;

import java.util.ArrayList;

public class Commentary {

    String status;
    Data data;
    Metadata metadata;

    private class Data {
        String id;
        int matchId;
        String homeTeamName;
        String homeTeamId;
        int homeScore;
        String awayTeamName;
        int awayTeamId;
        int awayScore;
        int competitionId;
        String competition;
        ArrayList<CommentaryEntry> commentaryEntries;

        public int getHomeScore() {
            return homeScore;
        }

        public int getAwayScore() {
            return awayScore;
        }

        public ArrayList<CommentaryEntry> getCommentaryEntries() {
            return commentaryEntries;
        }
    }

    private class Metadata {
        String createdAt;
    }
}
