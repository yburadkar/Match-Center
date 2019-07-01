package com.yb.uadnd.matchcentre.model.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.yb.uadnd.matchcentre.model.Commentary.Data.CommentaryEntry;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "comment")
public class Comment {
    @PrimaryKey(autoGenerate = true)
    int id;
    int matchId;
    private String type;
    private String comment;
    private String time;
    private String period;

    Comment(int id, int matchId, String type, String comment, String time, String period) {
        this.id = id;
        this.matchId = matchId;
        this.type = type;
        this.comment = comment;
        this.time = time;
        this.period = period;
    }

    @Ignore
    private Comment(int matchId, String type, String comment, String time, String period) {
        this.matchId = matchId;
        this.type = type;
        this.comment = comment;
        this.time = time;
        this.period = period;
    }

    @Ignore
    public Comment(int matchId, CommentaryEntry entry){
        this(matchId, entry.getType(), entry.getComment(), entry.getTime(), entry.getPeriod());
    }

    @Ignore
    public Comment() {
    }

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

    public void setType(String type) {
        this.type = type;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public static ArrayList<CommentaryEntry> toEntries(List<Comment> comments){
        ArrayList<CommentaryEntry> entries = new ArrayList<>();
        if(comments != null){
            for(Comment comment: comments){
                entries.add(new CommentaryEntry(comment.type, comment.comment, comment.time, comment.period));
            }
        }
        return entries;
    }
}
