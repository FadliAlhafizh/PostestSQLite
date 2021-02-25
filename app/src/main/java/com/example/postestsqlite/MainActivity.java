package com.example.postestsqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();

        try {
            Intent intent = getIntent();
            String status = intent.getExtras().getString("status");

            if (status.equals("add")) {

            } else if (status.equals("edit")) {
            } else if (status.equals("delete")) {
            }
        } catch (Exception e) { }

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

    }

    static void setRecyclerView(Context context, List<Note> notes, RecyclerView recyclerView) {
        DatabaseHelper db = new DatabaseHelper(context);
        notes = db.selectNoteData();

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(notes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this);
        notes = db.selectNoteData();

        adapter = new RecyclerviewAdapter(notes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}