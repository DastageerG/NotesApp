package com.example.notesapp.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Notes")
public class NoteEntity
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String NotesTitle;
    private String NotesDescription;


    public NoteEntity()
    {
    }

    public NoteEntity(int id, Date date, String notesTitle, String notesDescription)
    {
        this.id = id;
        this.date = date;
        NotesTitle = notesTitle;
        NotesDescription = notesDescription;
    }

    @Ignore
    public NoteEntity(Date date, String notesTitle, String notesDescription)
    {
        this.date = date;
        NotesTitle = notesTitle;
        NotesDescription = notesDescription;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getNotesTitle()
    {
        return NotesTitle;
    }

    public void setNotesTitle(String notesTitle)
    {
        NotesTitle = notesTitle;
    }

    public String getNotesDescription()
    {
        return NotesDescription;
    }

    public void setNotesDescription(String notesDescription)
    {
        NotesDescription = notesDescription;
    }

    @Override
    public String toString()
    {
        return "NoteEntity{" +
                "id=" + id +
                ", date=" + date +
                ", NotesTitle='" + NotesTitle + '\'' +
                ", NotesDescription='" + NotesDescription + '\'' +
                '}';
    }
} /// Notes closed
