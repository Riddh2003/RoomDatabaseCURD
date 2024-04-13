package com.example.roomdatabasecurd;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    static RecyclerView rv;
    static ArrayList<Task> arrayList;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        context = this;
        rv = findViewById(R.id.rv);
        arrayList = new ArrayList<Task>();
        show();
    }
    public static void show(){
        class Myclass extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                arrayList = (ArrayList<Task>) DatabaseClient.getInstance(context).appDatabase().taskDao().show();
                return null;
            }
            @Override
            protected void onPostExecute(Void unused) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                rv.setLayoutManager(linearLayoutManager);
                super.onPostExecute(unused);
                RVAdapter adapter = new RVAdapter(context,arrayList);
                rv.setAdapter(adapter);
            }
        }
        new Myclass().execute();
    }
}