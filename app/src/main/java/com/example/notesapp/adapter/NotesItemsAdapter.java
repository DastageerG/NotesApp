package com.example.notesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.NoteDetailsActivity;
import com.example.notesapp.R;
import com.example.notesapp.database.NoteEntity;
import com.example.notesapp.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotesItemsAdapter extends RecyclerView.Adapter<NotesItemsAdapter.ViewHolder>
{
    private Context context;
    private List<NoteEntity>notesList;
    private SelectedItem selectedItem;

    public interface SelectedItem
    {
        void clickedItemId(NoteEntity noteEntity);
    }

    public NotesItemsAdapter(Context context, List<NoteEntity> notesList)
    {
        this.context = context;
        this.notesList = notesList;
        this.selectedItem = (SelectedItem) context;
    } //

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.textViewLayoutNotesItemsTitle)
        TextView textViewTitle;
        @BindView(R.id.imageViewLayoutNotesItemsDelete)
        ImageView imageViewDelete;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    } // ViewHolder class closed

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_notes_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        final NoteEntity noteEntity = notesList.get(position);
        holder.textViewTitle.setText(""+noteEntity.getNotesTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, NoteDetailsActivity.class);
                intent.putExtra(Constants.noteId,noteEntity.getId());
                context.startActivity(intent);
            }
        });

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedItem.clickedItemId(noteEntity);
            }
        });

    } // onBindViewHolder closed

    @Override
    public int getItemCount()
    {
        return notesList.size();
    }
} // NotesItemsAdapter closed
