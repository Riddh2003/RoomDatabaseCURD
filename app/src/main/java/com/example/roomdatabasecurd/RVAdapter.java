package com.example.roomdatabasecurd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Myclass> {
    Context context;
    ArrayList<Task> arrayList;
    public RVAdapter(Context context,ArrayList<Task> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycustom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.Myclass holder, @SuppressLint("RecyclerView") int position) {
        holder.tx1.setText(arrayList.get(position).getFisrtname());
        holder.tx2.setText(arrayList.get(position).getLastname());
        holder.tx3.setText(arrayList.get(position).getPassword());
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem m1 = menu.add(0,0,0,"UPDATE");
                MenuItem m2 = menu.add(0,1,0,"DELETE");
                m1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        Intent intent = new Intent(context, UpdateData.class);
                        intent.putExtra("id",arrayList.get(position).getId());
                        intent.putExtra("firstname",arrayList.get(position).getFisrtname());
                        intent.putExtra("lastname",arrayList.get(position).getLastname());
                        intent.putExtra("password",arrayList.get(position).getPassword());
                        context.startActivity(intent);
                        return false;
                    }
                });
                m2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        Task task = new Task();
                        task.setId(arrayList.get(position).getId());
                        delete(task);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class Myclass extends RecyclerView.ViewHolder{
        TextView tx1,tx2,tx3;
        public Myclass(@NonNull View itemView){
            super(itemView);
            tx1 = itemView.findViewById(R.id.textView1);
            tx2 = itemView.findViewById(R.id.textView2);
            tx3 = itemView.findViewById(R.id.textView3);
        }
    }
    void delete(Task task){
        class Myclass extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context).appDatabase().taskDao().delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(context,"Data Delete",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,ViewData.class);
                context.startActivity(i);
            }
        }
        new Myclass().execute();
    }
}
