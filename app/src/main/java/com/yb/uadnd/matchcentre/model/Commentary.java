package com.yb.uadnd.matchcentre.model;

import java.util.ArrayList;

public class Commentary {

    String status;
    Data data;
    Metadata metadata;

    public Data getData() {
        return data;
    }
    public static class Data {

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

        public static class CommentaryEntry {
            String type;
            String comment;
            String time;
            String period;

            public String getType() {
                return type;
            }

            public String getComment() {
                return comment;
            }

            public String getTime() {
                return time;
            }

            public String getPeriod() {
                return period;
            }
        }
    }

    private class Metadata {
        String createdAt;
    }
}
