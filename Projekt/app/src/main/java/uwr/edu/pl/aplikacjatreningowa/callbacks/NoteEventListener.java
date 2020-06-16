package uwr.edu.pl.aplikacjatreningowa.callbacks;

import uwr.edu.pl.aplikacjatreningowa.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);

    void onNoteLongClick(Note note);
}