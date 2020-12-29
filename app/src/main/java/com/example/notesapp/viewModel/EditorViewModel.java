package com.example.notesapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.notesapp.database.NoteEntity;
import com.example.notesapp.database.NotesRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel
{
    private NotesRepository notesRepository;
    public MutableLiveData<NoteEntity>mLiveNote = new MutableLiveData<>();
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application)
    {
        super(application);
        notesRepository = NotesRepository.getInstance(application.getApplicationContext());
    } // constructor closed

    public void loadNote(final int noteId)
    {
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                NoteEntity noteEntity = notesRepository.loadNote(noteId);
                mLiveNote.postValue(noteEntity);
             }
        });


    } // loadNote closed

    public void saveAndExit(String title, String description)
    {
        NoteEntity noteEntity = mLiveNote.getValue();

        if(noteEntity==null) // creating a new notes
        {
            if(title.isEmpty()||description.isEmpty())
            {
                return;
            } // if closed
            else
            {
                noteEntity = new NoteEntity();
                noteEntity.setNotesTitle(title);
                noteEntity.setNotesDescription(description);
                noteEntity.setDate(new Date());

            } // else closed
        } // if closed
        else /// updating an existing note
        {
            noteEntity.setNotesTitle(title);
            noteEntity.setNotesDescription(description);
            noteEntity.setDate(new Date());
        }
        notesRepository.updateNote(noteEntity); // inserting new or updating
    } // saveAndExitClosed
} // EditorViewModel closed
