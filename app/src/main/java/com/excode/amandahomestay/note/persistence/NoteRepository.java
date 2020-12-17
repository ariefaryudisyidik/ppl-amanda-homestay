package com.excode.amandahomestay.note.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.t3ch.shaj.easynotes.async.DeleteAsyncTask;
import com.t3ch.shaj.easynotes.async.InsertAsyncTask;
import com.t3ch.shaj.easynotes.async.UpdateAsyncTask;
import com.t3ch.shaj.easynotes.models.Note;

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
