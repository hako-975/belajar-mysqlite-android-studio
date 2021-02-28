package com.example.mylisttoread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, reason_input, last_read_pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input             = findViewById(R.id.title_input);
        reason_input            = findViewById(R.id.reason_input);
        last_read_pages_input   = findViewById(R.id.last_read_pages_input);
        add_button              = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper MyDB = new MyDatabaseHelper(AddActivity.this);
                MyDB.addList(title_input.getText().toString().trim(), reason_input.getText().toString().trim(), Integer.parseInt(last_read_pages_input.getText().toString().trim()));
            }
        });
    }
}