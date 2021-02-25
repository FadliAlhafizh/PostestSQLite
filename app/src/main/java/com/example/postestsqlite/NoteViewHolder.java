package com.example.postestsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    TextView txtTanggal;
    TextView txtJudul;
    TextView txtDeskripsi;
    private CardView ListNote;
    private Context context;
    private List<Note> notes;
    private String judul;
    private String deskripsi;

    public NoteViewHolder(View itemView){
        super(itemView);
        context = itemView.getContext();

        txtTanggal = itemView.findViewById(R.id.txttgl);
        txtJudul = itemView.findViewById(R.id.txtjudul);
        txtDeskripsi = itemView.findViewById(R.id.txtdeskripsi);
        ListNote = itemView.findViewById(R.id.card_list);

        ListNote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                alertAction(context, getAdapterPosition());
                return false;
            }
        });

    }

    private void alertAction(final Context context, final int position) {
        String[] option = {"Edit", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatabaseHelper db = new DatabaseHelper(context);
        notes = db.selectNoteData();

        builder.setTitle("Choose Option");
        builder.setItems(option, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    judul = notes.get(position).getJudul();
                    deskripsi = notes.get(position).getDeskripsi();

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("judul", judul);
                    intent.putExtra("desk", deskripsi);

                    context.startActivity(intent);

                } else {
                    DatabaseHelper db = new DatabaseHelper(context);
                    db.delete(notes.get(position).getJudul());

                    notes = db.selectNoteData();
                    MainActivity.setRecyclerView(context, notes, MainActivity.recyclerView);
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
