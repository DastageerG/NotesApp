package com.example.notesapp.database;

import android.app.SyncNotedAppOp;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.locks.Lock;

@Database(entities = {NoteEntity.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class NotesDatabase extends RoomDatabase
{
    public static final String DATABASE_NAME = "NotesDatabase";

    public static volatile NotesDatabase instance;

    public abstract NotesDao notesDao();

    public static NotesDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            synchronized (NotesDatabase.class)
            {
                if(instance==null)
                {
                    instance = Room.databaseBuilder(context,NotesDatabase.class,DATABASE_NAME).build();
                } // if closed
            } // synchronized closed
        } // if closed
        return instance;
    } // getInstance closed

} // NotesDatabase closed

