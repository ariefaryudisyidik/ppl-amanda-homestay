package com.excode.amandahomestay.note.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.t3ch.shaj.easynotes.models.Note;

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
