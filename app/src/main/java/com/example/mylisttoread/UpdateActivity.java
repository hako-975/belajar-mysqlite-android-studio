package com.example.mylisttoread;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, reason_input, last_read_pages_input;
    Button update_button, delete_button;
    String id, title, reason, last_read_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input_update);
        reason_input = findViewById(R.id.reason_input_update);
        last_read_pages_input = findViewById(R.id.last_read_pages_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // first call this
        getAndSetIntentData();

        // set action bar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                // second call this
                title = title_input.getText().toString().trim();
                reason =reason_input.getText().toString().trim();
                last_read_pages = last_read_pages_input.getText().toString().trim();
                myDB.updateData(id, title, reason, last_read_pages);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData()
    {
        if (getIntent().hasExtra("id") &&
            getIntent().hasExtra("title") &&
            getIntent().hasExtra("reason") &&
            getIntent().hasExtra("last_read_pages"))
        {
            // getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            reason = getIntent().getStringExtra("reason");
            last_read_pages = getIntent().getStringExtra("last_read_pages");

            // setting intent data
            title_input.setText(title);
            reason_input.setText(reason);
            last_read_pages_input.setText(last_read_pages);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure want to delete "  + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}