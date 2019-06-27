package com.yb.uadnd.matchcentre.model;

import java.util.ArrayList;

public class Match {
    private String status;
    private Data data;
    private MetaData metaData;

    static class Data {
        private String id;
        private int feedMatchId;
        private String competition;
        private int competitionId;
        private String status;
        private String period;
        private int seasonId;
        private String season;
        private int round;
        private int minute;
        private int attendance;
        private String date;
        private Team homeTeam;
        private Team awayTeam;
        private Venue venue;
        private ArrayList<Event> events;
        private ArrayList<Official> officials;

        static class Team {
            private String id;
            private String name;
            private String shortname;
            private int score;
            private int halfTimeScore;
            private ArrayList<Player> players;
            private TeamStats teamStats;

            static class Player {
                private int id;
                private String firstName;
                private String lastName;
                private String position;
                private int shirtNumber;
                private String status;
                private boolean captain;
                private PlayerStats playerStats;

                static class PlayerStats {
                    private int bottomCentreSaves;
                    private int bottomLeftGoals;
                    private int bottomLeftSaves;
                    private int bottomRightGoals;
                    private int bottomRightSaves;
                    private int centreBoxShots;
                    private int concededShotsOnTargetInsideBox;
                    private int concededShotsOnTargetOutsideBox;
                    private int cornersLost;
                    private int cornersTaken;
                    private int cornersWon;
                    private int defenderBlocks;
                    private int directFreeKicks;
                    private int finalThirdFouls;
                    private int formationPlace;
                    private int freeKickCrosses;
                    private int goalAssists;
                    private int goalKicks;
                    private int goals;
                    private int goalsConceded;
                    private int goalsConcededInsideBox;
                    private int handBalls;
                    private int headerGoals;
                    private int headerMisses;
                    private int headerShots;
                    private int insideBoxBlocks;
                    private int insideBoxGoalkeeperSaves;
                    private int insideBoxGoals;
                    private int insideBoxMisses;
                    private int insideBoxSaves;
                    private int intentionalGoalAssists;
                    private int keeperDivingSaves;
                    private int leftBoxShots;
                    private int leftFootShotGoals;
                    private int leftFootShots;
                    private int leftFootShotSaves;
                    private int leftMisses;
                    private int minutesPlayed;
                    private int numberOfFouls;
                    private int openPlayGoalAssists;
                    private int openPlayGoals;
                    private int openPlayShots;
                    private int outsideBoxBlocks;
                    private int outsideBoxCentreShots;
                    private int outsideBoxGoalkeeperSaves;
                    private int outsideBoxMisses;
                    private int outsideBoxSaves;
                    private int penaltiesConceded;
                    private int penaltiesFaced;
                    private int penaltyGoals;
                    private int penaltyGoalsConceded;
                    private int rightFootGoals;
                    private int rightFootSaves;
                    private int rightFootShots;
                    private int rightMisses;
                    private int saves;
                    private int shotsBlocked;
                    private int shotsOffTarget;
                    private int shotsOnGoal;
                    private int shotsOnTarget;
                    private int shotsOnTargetAssists;
                    private int started;
                    private int subsOff;
                    private int subsOn;
                    private int throwIns;
                    private int topMisses;
                    private int topRightGoals;
                    private int totalCornersIntoBox;
                    private int woodworkHits;
                    private int yellowCards;
                }
            }

            static class TeamStats {
                private int bottomCentreSaves;
                private int bottomLeftGoals;
                private int bottomLeftSaves;
                private int bottomRightGoals;
                private int bottomRightSaves;
                private int centreBoxShots;
                private int concededShotsOnTargetInsideBox;
                private int concededShotsOnTargetOutsideBox;
                private int cornersLost;
                private int cornersTaken;
                private int cornersWon;
                private int defenderBlocks;
                private int defenderGoals;
                private int directFreeKicks;
                private int finalThirdFouls;
                private int firstHalfGoals;
                private int formation;
                private int freeKickCrosses;
                private int freeKicksConceded;
                private int freeKicksWon;
                private int goalAssists;
                private int goalKicks;
                private int goals;
                private int goalsConceded;
                private int goalsConcededInsideBox;
                private int handBalls;
                private int headerGoals;
                private int headerMisses;
                private int headerShots;
                private int insideBoxBlocks;
                private int insideBoxGoalkeeperSaves;
                private int insideBoxGoals;
                private int insideBoxMisses;
                private int insideBoxSaves;
                private int intentionalGoalAssists;
                private int keeperDivingSaves;
                private int leftBoxShots;
                private int leftFootShotGoals;
                private int leftFootShots;
                private int leftFootShotSaves;
                private int leftMisses;
                private int midfielderGoals;
                private int openPlayGoalAssists;
                private int openPlayGoals;
                private int openPlayShots;
                private int outsideBoxBlocks;
                private int outsideBoxCentreShots;
                private int outsideBoxGoalkeeperSaves;
                private int outsideBoxMisses;
                private int outsideBoxSaves;
                private int penaltiesConceded;
                private int penaltiesFaced;
                private int penaltiesWon;
                private int penaltyGoals;
                private int penaltyGoalsConceded;
                private int possession;
                private int rightFootGoals;
                private int rightFootSaves;
                private int rightFootShots;
                private int rightMisses;
                private int saves;
                private int shotsBlocked;
                private int shotsOffTarget;
                private int shotsOnGoal;
                private int shotsOnTarget;
                private int shotsOnTargetAssists;
                private int substitutionsMade;
                private int teamYellowCards;
                private int throwIns;
                private int topMisses;
                private int topRightGoals;
                private int totalCornersIntoBox;
                private int woodworkHits;
            }
        }

        static class Venue {
            private int id;
            private String name;
            private String location;
        }

        static class Event {
            private long eventId;
            private String period;
            private String time;
            private int optaMinute;
            private int minute;
            private String teamId;
            private String type;
            private String eventTimestamp;
            private Goal goalDetails;
            private Booking bookingDetails;
            private Substitution substitutionDetails;

            static class Goal {
                private Player player;
                private String type;
            }

            static class Player {
                private int playerId;
                private String firstName;
                private String lastName;
                private String known;
            }

            static class Booking {
                private Player player;
                private String type;
            }

            static class Substitution {
                private Player playerSubOff;
                private Player playerSubOn;
                private String reason;
            }
        }

        static class Official {
            private int id;
            private String mame;
            private String type;
            private boolean referee;
        }
    }

    static class MetaData {
        private String createdAt;
    }
}
