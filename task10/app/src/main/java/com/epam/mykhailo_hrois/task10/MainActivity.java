package com.epam.mykhailo_hrois.task10;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;


/**
 * Activity that holds all data processing
 * and also holds a main page view
 */
public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteAdapter = new NoteAdapter();

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, this::onDataLoaded);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(noteAdapter);

        new ItemTouchHelper(new SwipeListener(noteViewModel, noteAdapter, this)).attachToRecyclerView(recyclerView);

        buttonAddNote.setOnClickListener(this::addNote);
        noteAdapter.setOnItemClickListener(this::editItem);
    }

    /**
     * @param notes
     *
     * Refreshes Recycler view by notifying adapter
     */
    private void onDataLoaded(List<Note> notes) {
        noteAdapter.submitList(notes);
    }

    /**
     * @param note
     *
     * Edit behavior for a note
     */
    private void editItem(Note note) {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
        intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
        intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
        intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
        startActivityForResult(intent, EDIT_NOTE_REQUEST);
    }

    /**
     * @param view
     *
     * Add behavior for a note
     */
    private void addNote(View view) {
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE_REQUEST);
    }

    /**
     * @param requestCode from {@link AddEditNoteActivity} to find out the behavior
     * @param resultCode from {@link AddEditNoteActivity} to find out that all went well
     * @param data which holds a note
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            addNoteToDb(data);
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            saveEditedNote(data);
        } else {
            Toast.makeText(this, R.string.note_not_saved, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param data holds a note from {@link AddEditNoteActivity}
     *
     * Returns when there is no data
     * Calls on edit note's behavior and updates a note
     */
    private void saveEditedNote(Intent data) {
        int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

        if (id == -1) {
            Toast.makeText(this, R.string.note_cant_be_updated, Toast.LENGTH_SHORT).show();
            return;
        }

        String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
        String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
        int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);
        Note note = new Note(title, description, priority);
        note.setId(id);
        noteViewModel.update(note);
        Toast.makeText(this, R.string.note_updated, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param data holds a note from {@link AddEditNoteActivity}
     *
     * Returns when there is no data
     * Calls on add note's behavior and inserts a note
     */
    private void addNoteToDb(Intent data) {
        String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
        String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
        int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

        Note note = new Note(title, description, priority);
        noteViewModel.insert(note);

        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, R.string.all_notes_deleted, Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Swipe callback for deleting note by swiping over the view
     */
    private static class SwipeListener extends ItemTouchHelper.SimpleCallback {
        private NoteViewModel noteViewModel;
        private NoteAdapter noteAdapter;
        private Context context;

        public SwipeListener(NoteViewModel noteViewModel, NoteAdapter noteAdapter, Context context) {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.noteViewModel = noteViewModel;
            this.noteAdapter = noteAdapter;
            this.context = context;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            Toast.makeText(context, R.string.note_deleted, Toast.LENGTH_SHORT).show();
        }
    }
}
