package com.excode.amandahomestay.note.async;

import android.os.AsyncTask;
import android.util.Log;

import com.t3ch.shaj.easynotes.models.Note;
import com.t3ch.shaj.easynotes.persistence.NoteDao;

/**
 * Created by Shakil Ahmed Shaj on 28-Apr-19.
 * shakilahmedshaj@gmail.com
 */
public class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

    private static final String TAG = "InsertAsyncTask";

    private NoteDao mNoteDao;


    public InsertAsyncTask(NoteDao dao) {
        Log.d(TAG, "InsertAsyncTask: thread: " + Thread.currentThread().getName());

        mNoteDao = dao;

    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDao.insertNotes(notes);
        return null;
    }
}
