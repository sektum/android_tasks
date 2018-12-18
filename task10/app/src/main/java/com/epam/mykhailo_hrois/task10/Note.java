package com.epam.mykhailo_hrois.task10;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

/**
 * The entity used as description of a table
 */
@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return priority == note.priority &&
                Objects.equals(title, note.title) &&
                Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
