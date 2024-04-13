package com.example.roomdatabasecurd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsForm extends AppCompatActivity {
    EditText tx1,tx2,tx3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsform);
        tx1 = findViewById(R.id.tx1);
        tx2 = findViewById(R.id.tx2);
        tx3 = findViewById(R.id.tx3);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname  = tx1.getText().toString();
                String lastname = tx2.getText().toString();
                String password = tx3.getText().toString();
                Task task = new Task();
                task.setFisrtname(firstname);
                task.setLastname(lastname);
                task.setPassword(password);
                insert(task);
                Toast.makeText(DetailsForm.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsForm.this,ViewData.class);
                startActivity(intent);
            }
        });

    }
    void insert(Task task){
        class Myclass extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(DetailsForm.this).appDatabase().taskDao().insert(task);
                return null;
            }
        }
        new Myclass().execute();
    }
}