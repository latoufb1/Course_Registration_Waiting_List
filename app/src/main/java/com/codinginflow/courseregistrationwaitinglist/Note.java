package com.codinginflow.courseregistrationwaitinglist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String FName;

    private String LName;

    private String description;

    private int priority;

    public Note(String FName, String LName, String description, int priority) {
        this.FName = FName;
        this.LName = LName;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}

