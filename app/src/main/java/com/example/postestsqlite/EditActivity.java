package com.example.postestsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class EditActivity extends AppCompatActivity {

    private TextInputLayout edtJudul, edtDeskripsi;
    private Button btnEdit;
    private DatabaseHelper db;
    private Bundle intentData;
    private Note note;
    private String judul;
    private String deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtJudul = findViewById(R.id.layoutinputjudul);
        edtDeskripsi = findViewById(R.id.layoutinputdeskripsi);
        btnEdit = findViewById(R.id.btnUpdate);
        db = new DatabaseHelper(this);

        intentData = getIntent().getExtras();
        edtJudul.getEditText().setText(intentData.getString("judul"));
        edtDeskripsi.getEditText().setText(intentData.getString("deskripsi"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = edtJudul.getEditText().getText().toString();
                deskripsi = edtDeskripsi.getEditText().getText().toString();

                if (judul.isEmpty() || deskripsi.isEmpty()){

                } else {
                    note = new Note();
                    note.setJudul(judul);
                    note.setDeskripsi(deskripsi);
                    db.update(note);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("status", "edit");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hapus, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btndelete) {
            db = new DatabaseHelper(this);
            db.delete(intentData.getString("judul"));

            Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("status", "delete");
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
