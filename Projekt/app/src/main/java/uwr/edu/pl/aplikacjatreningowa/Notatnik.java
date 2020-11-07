package uwr.edu.pl.aplikacjatreningowa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uwr.edu.pl.aplikacjatreningowa.adapters.NotesAdapter;
import uwr.edu.pl.aplikacjatreningowa.callbacks.NoteEventListener;
import uwr.edu.pl.aplikacjatreningowa.db.NotesDB;
import uwr.edu.pl.aplikacjatreningowa.db.NotesDao;
import uwr.edu.pl.aplikacjatreningowa.model.Note;
import static uwr.edu.pl.aplikacjatreningowa.EditeNoteActivity.NOTE_EXTRA_Key;

public class Notatnik extends AppCompatActivity implements NoteEventListener {
    private static final String TAG = "Notatnik";
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notetnik);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        
        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onaddNewNote();
            }
        });
        dao = NotesDB.getInstance(this).notesDao();

    }

    private void loadNotes() {
        this.notes = new ArrayList<>();
        List<Note> list = dao.getNotes();

        this.notes.addAll(list);
        this.adapter = new NotesAdapter(this ,notes);

        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);


    }

    private void onaddNewNote() {
        startActivity(new Intent(this,EditeNoteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onResume(){
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
        Intent edit = new Intent(this, EditeNoteActivity.class);
        edit.putExtra(NOTE_EXTRA_Key,note.getId());
        startActivity(edit);

    }

    @Override
    public void onNoteLongClick(final Note note) {
        new AlertDialog.Builder(this)
                .setTitle("Usunąć notatkę??")
                .setNegativeButton("Wróć", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton("Usuń", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.deleteNote(note);
                loadNotes();
            }
        }).create().show();
    }
}
