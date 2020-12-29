package com.example.notesapp.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesapp.database.NoteEntity;
import com.example.notesapp.database.NotesRepository;
import com.example.notesapp.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel
{
    public LiveData<List<NoteEntity>> noteEntitiesList;
    private NotesRepository notesRepository;
    public static final String TAG = "1111";
    public ListActivityViewModel(@NonNull Application application)
    {
        super(application);
        notesRepository = NotesRepository.getInstance(application.getApplicationContext());
        noteEntitiesList = notesRepository.noteEntityList;
        Log.d(TAG, "ListActivityViewModel: "+noteEntitiesList.toString());
    }

    public void deleteItem(NoteEntity noteEntity)
    {
        notesRepository.deleteItem(noteEntity);
    }
}
