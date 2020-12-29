package com.example.notesapp.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.notesapp.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesRepository
{
    private static NotesRepository instance;
    public LiveData<List<NoteEntity>>noteEntityList;
    private NotesDatabase notesDatabase;
    private Executor executor;
    public static final String TAG = "1111";

    public NotesRepository(Context context)
    {
        notesDatabase = NotesDatabase.getInstance(context);
        noteEntityList = getNoteEntityList();
        executor = Executors.newSingleThreadExecutor();

    }

    public static NotesRepository getInstance(Context context)
    {
        if(instance==null)
        {
            instance = new NotesRepository(context);
        }
        return instance;
    } // getInstance closed

    private LiveData<List<NoteEntity>>getNoteEntityList()
    {
        return notesDatabase.notesDao().getAllNotes();
    } // getNoteEntityList closed

    public NoteEntity loadNote(int noteId)
    {
        return notesDatabase.notesDao().getNoteById(noteId);
    }

    public void updateNote(final NoteEntity noteEntity)
    {
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                notesDatabase.notesDao().insertNote(noteEntity);
                Log.d(TAG, "run: ");
            }
        });

    } // update note closed

    public void deleteItem(final NoteEntity noteEntity)
    {
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                notesDatabase.notesDao().deleteNote(noteEntity);
            } // run closed
        });

    } // deleteItem closed
} // NotesRepository closed
