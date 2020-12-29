package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.adapter.NotesItemsAdapter;
import com.example.notesapp.database.NoteEntity;
import com.example.notesapp.utils.Constants;
import com.example.notesapp.viewModel.ListActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NotesItemsAdapter.SelectedItem
{
    private static final String TAG = "1111";
    private Context context = MainActivity.this;
    @BindView(R.id.floatingActionButtonAdd)
    FloatingActionButton floatingActionButton; // for adding  new note


    @BindView(R.id.recycleViewNotes)
    RecyclerView recyclerView;

    private NotesItemsAdapter adapter;
    private List<NoteEntity> noteEntityList = new ArrayList<>();
    private ListActivityViewModel listActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewModel();

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context,NoteDetailsActivity.class);
                startActivity(intent);
            } // onCLick closed
        }); // floatingActionButton closed
        initRecyclerView(recyclerView);
    } /// onCreate closed

    private void initViewModel()
    {
        Observer<List<NoteEntity>>notesObserver = new Observer<List<NoteEntity>>()
        {
            @Override
            public void onChanged(List<NoteEntity> noteEntities)
            {
                Log.d(TAG, "onChanged: "+noteEntities);
                noteEntityList.clear();
                noteEntityList.addAll(noteEntities);
                if(adapter==null)
                {
                    adapter = new NotesItemsAdapter(context,noteEntityList);
                    recyclerView.setAdapter(adapter);
                } // if closed
                else
                {
                    adapter.notifyDataSetChanged();
                } // else closed

            } // onChanged closed
        };
                listActivityViewModel = new ViewModelProvider(MainActivity.this).get(ListActivityViewModel.class);
                listActivityViewModel.noteEntitiesList.observe(MainActivity.this,notesObserver);

    } // initViewModel closed

    private void initRecyclerView(RecyclerView recyclerView)
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                clickedItemId(noteEntityList.get(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView); // swipe to delete

        recyclerView.addItemDecoration(new DividerItemDecoration(context,getResources().getConfiguration().orientation));

    } // initRecyclerView closed

    @Override
    public void clickedItemId(NoteEntity noteEntity)
    {
        listActivityViewModel.deleteItem(noteEntity);
        Toast.makeText(context, "Item Deleted SuccessFully", Toast.LENGTH_SHORT).show();
    } //

} // MainActivity closed