package com.excode.amandahomestay.note.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.excode.amandahomestay.note.models.Note;

import java.util.List;

/**
 * Created by Shakil Ahmed Shaj on 27-Apr-19.
 * shakilahmedshaj@gmail.com
 */
@Dao
public interface NoteDao {

    @Insert
    long[] insertNotes(Note... notes);


    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();


    @Delete
    int delete(Note... notes);


    @Update
    int update(Note... notes);


}
