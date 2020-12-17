package com.excode.amandahomestay.note.async;

import android.os.AsyncTask;
import android.util.Log;

import com.t3ch.shaj.easynotes.models.Note;
import com.t3ch.shaj.easynotes.persistence.NoteDao;

/**
 * Created by Shakil Ahmed Shaj on 28-Apr-19.
 * shakilahmedshaj@gmail.com
 */
public class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {

    private static final String TAG = "DeleteAsyncTask";

    private NoteDao mNoteDao;


    public UpdateAsyncTask(NoteDao dao) {
        Log.d(TAG, "DeleteAsyncTask: thread: " + Thread.currentThread().getName());

        mNoteDao = dao;

    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDao.update(notes);
        return null;
    }
}
