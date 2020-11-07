package uwr.edu.pl.aplikacjatreningowa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uwr.edu.pl.aplikacjatreningowa.R;
import uwr.edu.pl.aplikacjatreningowa.callbacks.NoteEventListener;
import uwr.edu.pl.aplikacjatreningowa.model.Note;
import uwr.edu.pl.aplikacjatreningowa.utils.NoteUtils;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder>{
    private Context context;
    private ArrayList<Note> notes;
    private String tytul;
    private NoteEventListener listener;
    public NotesAdapter(Context context,ArrayList<Note> notes)
    {
        this.context = context;
        this.notes = notes;
    }
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notelayout,parent,false);

        return new NoteHolder(v);
    }
    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        final Note note = getNote(position);
        if (note != null){
            holder.noteText.setText(tytul_fun(note));
            holder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNoteClick(note);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onNoteLongClick(note);
                    return false;
                }
            });
        }
    }

    private String tytul_fun(Note note) {
        note.getNoteText();
        tytul = note.getNoteText();
        String lines[] = tytul.split("\\r?\\n");
        String a = lines[0].trim();
        return a;
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private  Note getNote(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView noteText,noteDate;

        public NoteHolder(View itemView){
            super(itemView);
            noteDate = itemView.findViewById(R.id.note_date);
            noteText = itemView.findViewById(R.id.note_text);
        }
    }

    public void setListener(NoteEventListener listener){
        this.listener = listener;
    }
}
