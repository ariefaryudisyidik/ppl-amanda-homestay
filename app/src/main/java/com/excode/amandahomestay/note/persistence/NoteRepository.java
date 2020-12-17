package com.excode.amandahomestay.note.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.excode.amandahomestay.note.async.DeleteAsyncTask;
import com.excode.amandahomestay.note.async.InsertAsyncTask;
import com.excode.amandahomestay.note.async.UpdateAsyncTask;
import com.excode.amandahomestay.note.models.Note;

import java.util.List;


/**
 * Created by Shakil Ahmed Shaj on 27-Apr-19.
 * shakilahmedshaj@gmail.com
 */
public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {

        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note) {

        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }

    public void updateNoteTask(Note note) {

        new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }


    public void deleteNoteTask(Note note) {

        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }
}
