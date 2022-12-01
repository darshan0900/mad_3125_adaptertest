package com.darshan09200.featuretest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textInput;
    Button insertBtn;
    ListView listView;
    FloatingActionButton fab;

    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.textInput);
        insertBtn = findViewById(R.id.insertBtn);
        listView = findViewById(R.id.listView);
        fab = findViewById(R.id.fab);

        names = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names);


        listView.setAdapter(adapter);

        insertBtn.setOnClickListener(view -> {
            String name = textInput.getText().toString().trim();
            if (name.isEmpty()) {
                showToast("Enter name first");
            } else if (names.indexOf(name) > -1) {
                showToast("Already exists");
            } else {
                names.add(name);
                adapter.notifyDataSetChanged();
            }
        });

        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}