package com.excode.amandahomestay.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.excode.amandahomestay.model.Bookkeeping;
import com.excode.amandahomestay.note.persistence.NoteDatabase;

import java.util.List;

public class BookkeepingRepository {

    private BookkeepingDatabase mBookkeepingDatabase;

    public BookkeepingRepository(Context context) {
        mBookkeepingDatabase = BookkeepingDatabase.getInstance(context);
    }
    public LiveData<List<Bookkeeping>> retrieveNotesTask() {
        return mBookkeepingDatabase.bookkeepingDao().selectAllBookkeepings();
    }
}
