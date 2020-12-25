package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.adapter.NotesItemsAdapter;
import com.example.notesapp.utils.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    private Context context = MainActivity.this;
    @BindView(R.id.floatingActionButtonAdd)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.recycleViewNotes)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context,NoteDetailsActivity.class);
                startActivity(intent);
            }
        });
        initRecyclerView(recyclerView);
    } /// onCreate closed

    private void initRecyclerView(RecyclerView recyclerView)
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        NotesItemsAdapter adapter = new NotesItemsAdapter(context, SampleDataProvider.getSampleData());
        recyclerView.setAdapter(adapter);
    }

} // MainActivity closed