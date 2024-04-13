package com.example.roomdatabasecurd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class UpdateData extends AppCompatActivity {
    EditText tx1,tx2,tx3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        tx1 = findViewById(R.id.tx1);
        tx2 = findViewById(R.id.tx2);
        tx3 = findViewById(R.id.tx3);
        btn = findViewById(R.id.button);
        Intent i = getIntent();
        int id = i.getIntExtra("id",0);
        String firstname = i.getStringExtra("firstname");
        String lastname = i.getStringExtra("lastname");
        String password = i.getStringExtra("password");
        tx1.setText(firstname);
        tx2.setText(lastname);
        tx3.setText(password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = tx1.getText().toString();
                String lastname = tx2.getText().toString();
                String password = tx3.getText().toString();
                Task task = new Task();
                task.setId(id);
                task.setFisrtname(firstname);
                task.setLastname(lastname);
                task.setPassword(password);
                update(task);
            }
        });
    }
    void update(Task task){
        class Myclass extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(UpdateData.this).appDatabase().taskDao().update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(UpdateData.this,"Data Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateData.this,ViewData.class);
                startActivity(i);
            }
        }
        new Myclass().execute();
    }
}