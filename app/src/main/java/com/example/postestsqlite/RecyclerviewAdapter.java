package com.example.postestsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter  extends RecyclerView.Adapter<NoteViewHolder> {
    List<Note> notes;

    public RecyclerviewAdapter(List<Note> noteList) {
        this.notes = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_row, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);

        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.txtTanggal.setText(notes.get(position).getTanggal());
        holder.txtJudul.setText(notes.get(position).getJudul());
        holder.txtDeskripsi.setText(notes.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

}
