package com.excode.amandahomestay.note.async;

import android.os.AsyncTask;
import android.util.Log;

import com.excode.amandahomestay.note.models.Note;
import com.excode.amandahomestay.note.persistence.NoteDao;

/**
 * Created by Shakil Ahmed Shaj on 28-Apr-19.
 * shakilahmedshaj@gmail.com
 */
public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

    private static final String TAG = "DeleteAsyncTask";

    private NoteDao mNoteDao;


    public DeleteAsyncTask(NoteDao dao) {
        Log.d(TAG, "DeleteAsyncTask: thread: " + Thread.currentThread().getName());

        mNoteDao = dao;

    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDao.delete(notes);
        return null;
    }
}
