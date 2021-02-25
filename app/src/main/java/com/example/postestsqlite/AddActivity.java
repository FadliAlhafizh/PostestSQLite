package com.example.postestsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout edtJudul, edtDeskripsi;
    private Button btnTambah;
    private DatabaseHelper db;
    private Note note;
    private String judul;
    private String deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtJudul = findViewById(R.id.layoutinputjudul);
        edtDeskripsi = findViewById(R.id.layoutinputdeskripsi);
        btnTambah = findViewById(R.id.btnTambah);
        db = new DatabaseHelper(this);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = edtJudul.getEditText().getText().toString();
                deskripsi = edtDeskripsi.getEditText().getText().toString();

                if (judul.isEmpty() || deskripsi.isEmpty()) {

                } else {
                    note = new Note();
                    note.setJudul(judul);
                    note.setDeskripsi(deskripsi);
                    db.insert(note);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("status", "add");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
