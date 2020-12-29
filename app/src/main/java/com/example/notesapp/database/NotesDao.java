package com.example.notesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NotesDao
{
    /// NOTES DAO  = NOTES DATA ACCESS OBJECT

    // insert / update note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    // insert list of notes / dummy data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<NoteEntity> noteEntityList);

    // delete note
    @Delete
    void deleteNote(NoteEntity noteEntity);

    // get particular or selected note
    @Query("SELECT * FROM Notes WHERE id = :id")
    NoteEntity getNoteById(int id);

    // List of all notes , showing  last added on top
    @Query("SELECT * FROM Notes ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAllNotes();

    // delete all from notes
    @Query("DELETE  FROM Notes")
    int deleteAllNotes();

    // return no of notes
    @Query("SELECT  COUNT(*) FROM Notes")
    int getNotesCount();


} // NotesDao closed
