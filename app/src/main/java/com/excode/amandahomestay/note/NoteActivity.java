package com.excode.amandahomestay.note;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.excode.amandahomestay.R;
import com.excode.amandahomestay.note.models.Note;
import com.excode.amandahomestay.note.persistence.NoteRepository;
import com.excode.amandahomestay.note.util.Utility;


public class NoteActivity extends AppCompatActivity implements
        View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnClickListener, TextWatcher {

    //ui components
    private LineEditText mLineEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;
    private RelativeLayout mCheckContainer, mBackArrowContainer;
    private ImageButton mCheck, mBackArrow;

    //vars
    private boolean mIsNewNote;
    private Note mInitialNote;
    private GestureDetector mGestureDetector;
    private int mMode;
    private NoteRepository mNoteRepository;
    private Note mNoteFinal;

    private static final String TAG = "NoteActivity";
    private static final int EDIT_MODE_ENABLED = 1;
    private static final int EDIT_MODE_DISABLED = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mLineEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);
        mCheck = findViewById(R.id.toolbar_check);
        mBackArrow = findViewById(R.id.toolbar_back_arrow);
        mCheckContainer = findViewById(R.id.check_container);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);

        mNoteRepository = new NoteRepository(this);

        if (getIncomingIntent()) {
            // this is a new note (EDIT MODE)

            setNewNoteProperties();
            enableEditMode();

        } else {
            // this is note a new note (VIEW MODE)
            setNoteProperties();
            disableContentInteraction();
        }
        setListeners();

    }

    private void setListeners() {

        mLineEditText.setOnTouchListener(this);

        mGestureDetector = new GestureDetector(this, this);

        mCheck.setOnClickListener(this);
        mViewTitle.setOnClickListener(this);

        mBackArrow.setOnClickListener(this);
        mEditTitle.addTextChangedListener(this);

    }


    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            mInitialNote = getIntent().getParcelableExtra("selected_note");

            mNoteFinal = new Note();
            mNoteFinal.setTitle(mInitialNote.getTitle());
            mNoteFinal.setContent(mInitialNote.getContent());
            mNoteFinal.setTimestamp(mInitialNote.getTimestamp());
            mNoteFinal.setId(mInitialNote.getId());

            mMode = EDIT_MODE_DISABLED;

            mIsNewNote = false;
            return false;
        }
        mMode = EDIT_MODE_ENABLED;
        mIsNewNote = true;
        return true;
    }

    private void saveChanges() {

        if (mIsNewNote) {
            saveNewNote();
        } else {
            // update note
            updateNotes();
        }
    }

    private void updateNotes() {
        mNoteRepository.updateNoteTask(mNoteFinal);
    }


    private void saveNewNote() {

        mNoteRepository.insertNoteTask(mNoteFinal);
    }


    private void enableEditMode() {
        mBackArrowContainer.setVisibility(View.GONE);
        mCheckContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;

        enableContentInteraction();

    }

    private void enableContentInteraction() {
        mLineEditText.setKeyListener(new EditText(this).getKeyListener());
        mLineEditText.setFocusable(true);
        mLineEditText.setFocusableInTouchMode(true);
        mLineEditText.setCursorVisible(true);
        mLineEditText.requestFocus();
    }

    private void disableEditMode() {
        Log.d(TAG, "disableEditMode: called.");
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mCheckContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;

        disableContentInteraction();


        // Check if they typed anything into the note. Don't want to save an empty note.
        String temp = mLineEditText.getText().toString();
        temp = temp.replace("\n", "");
        temp = temp.replace(" ", "");
        if (temp.length() > 0) {
            mNoteFinal.setTitle(mEditTitle.getText().toString());
            mNoteFinal.setContent(mLineEditText.getText().toString());
            String timestamp = Utility.getCurrentTimeStamp();
            mNoteFinal.setTimestamp(timestamp);

            // If the note was altered, save it.
            if (!mNoteFinal.getContent().equals(mInitialNote.getContent())
                    || !mNoteFinal.getTitle().equals(mInitialNote.getTitle())) {
                saveChanges();
            }
        }


    }

    private void hideKeyboard() {

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            view = new View(this);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private void disableContentInteraction() {
        mLineEditText.setKeyListener(null);
        mLineEditText.setFocusable(false);
        mLineEditText.setFocusableInTouchMode(false);
        mLineEditText.setCursorVisible(false);
        mLineEditText.clearFocus();
    }

    private void setNoteProperties() {

        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mLineEditText.setText(mInitialNote.getContent());


    }

    private void setNewNoteProperties() {

        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");

        mNoteFinal = new Note();
        mInitialNote = new Note();
        mInitialNote.setTitle("Note Title");
        mNoteFinal.setTitle("Note Title");


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        enableEditMode();

        Log.d(TAG, "onDoubleTap: double tapped");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {


        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_check: {

                hideKeyboard();
                disableEditMode();

                break;
            }
            case R.id.note_text_title: {
                enableEditMode();
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }

            case R.id.toolbar_back_arrow: {
                finish();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (mMode == EDIT_MODE_ENABLED) {
            onClick(mCheck);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode", mMode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMode = savedInstanceState.getInt("mode");
        if (mMode == EDIT_MODE_ENABLED) {
            enableEditMode();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        mViewTitle.setText(s.toString());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
