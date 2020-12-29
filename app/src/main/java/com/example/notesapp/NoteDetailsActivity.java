package com.example.notesapp;

import android.os.Bundle;

import com.example.notesapp.database.NoteEntity;
import com.example.notesapp.utils.Constants;
import com.example.notesapp.utils.SampleDataProvider;
import com.example.notesapp.viewModel.EditorViewModel;
import com.example.notesapp.viewModel.ListActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailsActivity extends AppCompatActivity
{

    private EditorViewModel editorViewModel;
    public static final String TAG = "1111";
    @BindView(R.id.editTextNotesDetailsActivityTitle)
    EditText editTextTitle;
    @BindView(R.id.editTextNotesDetailsActivityDescription)
    EditText editTextDescription;
    private int noteId;
    private boolean isEditing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViewModel();

        if(savedInstanceState!=null)
        {
            isEditing = savedInstanceState.getBoolean(Constants.isEditing);
        } // if closed

    } // onCreate closed

    private void initViewModel()
    {
        Observer<NoteEntity>noteEntityObserver = new Observer<NoteEntity>()
        {
            @Override
            public void onChanged(NoteEntity noteEntity)
            {
                if (noteEntity!=null && !isEditing)
                {
                    editTextTitle.setText(noteEntity.getNotesTitle());
                    editTextDescription.setText(noteEntity.getNotesDescription());
                } // if closed
//                else
//                {
//                    finish();
//                }
            } // onChanged closed
        }; // observer closed
        editorViewModel = new ViewModelProvider(NoteDetailsActivity.this).get(EditorViewModel.class);
        editorViewModel.mLiveNote.observe(NoteDetailsActivity.this,noteEntityObserver);

        if(getIntent().getExtras()!=null)
        {
            noteId = getIntent().getExtras().getInt(Constants.noteId);
            editorViewModel.loadNote(noteId);
            getSupportActionBar().setTitle("Edit Note");
        } // if closed
        else
        {
            getSupportActionBar().setTitle("New Note");
        } // else closed

    } /// initViewModel closed


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                saveAndExit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit()
    {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        editorViewModel.saveAndExit(title,description);

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState)
    {
        outState.putBoolean(Constants.isEditing,true);
        super.onSaveInstanceState(outState);

    }
} // NoteDetailsActivity closed