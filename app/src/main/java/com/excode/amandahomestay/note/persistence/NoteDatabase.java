package com.excode.amandahomestay.note.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.t3ch.shaj.easynotes.models.Note;

/**
 * Created by Shakil Ahmed Shaj on 27-Apr-19.
 * shakilahmedshaj@gmail.com
 */

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes_db";
    public static NoteDatabase instance;

    static NoteDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }
    public abstract NoteDao getNoteDao();


}
