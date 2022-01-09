package com.excode.amandahomestay.note;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.excode.amandahomestay.R;
import com.excode.amandahomestay.note.adapters.NotesRecyclerAdapter;
import com.excode.amandahomestay.note.models.Note;
import com.excode.amandahomestay.note.persistence.NoteDao;
import com.excode.amandahomestay.note.persistence.NoteDao_Impl;
import com.excode.amandahomestay.note.persistence.NoteDatabase;
import com.excode.amandahomestay.note.persistence.NoteDatabase_Impl;
import com.excode.amandahomestay.note.persistence.NoteRepository;
import com.excode.amandahomestay.note.util.VerticalSpacingItemDecorator;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesListActivity extends AppCompatActivity implements
        NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener {

    private static final String TAG = "NotesListActivity";

    //UI Components
    private ImageView ivBgNotFound2;
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNoteRecyclerAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        ivBgNotFound2 = findViewById(R.id.iv_bg_not_found);
        mRecyclerView = findViewById(R.id.recyclerView);


        findViewById(R.id.fab).setOnClickListener(this);

        mNoteRepository = new NoteRepository(this);
        
        initRecyclerView();
        retrieveNotes();
        //insertFakeNotes();
        //Log.d(TAG, "onCreate: thread: " + Thread.currentThread().getName());
        //setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        //setTitle("Notes");
    }

    private void retrieveNotes() {

        mNoteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                if (mNotes.size() > 0) {
                    mNotes.clear();
                }
                if (notes != null) {
                    mNotes.addAll(notes);
                }
                mNoteRecyclerAdapter.notifyDataSetChanged();

                if (mNotes.isEmpty()) {
                    mRecyclerView.setVisibility(View.GONE);
                    ivBgNotFound2.setVisibility(View.VISIBLE);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    ivBgNotFound2.setVisibility(View.GONE);
                }
            }
        });

    }

/*
    private void insertFakeNotes() {
        for (int i = 0; i < 1000; i++) {
            Note note = new Note();
            note.setTitle("title #" + i);
            note.setContent("content #: " + i);
            note.setTimestamp("Jan 2019");
            mNotes.add(note);
        }
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }
 */

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);


    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked " + position);


        Intent intent = new Intent(this, NoteActivity.class);

        intent.putExtra("selected_note", mNotes.get(position));


        startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);

    }

    private void deleteNote(Note note) {
        mNotes.remove(note);
        mNoteRecyclerAdapter.notifyDataSetChanged();
        mNoteRepository.deleteNoteTask(note);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}
