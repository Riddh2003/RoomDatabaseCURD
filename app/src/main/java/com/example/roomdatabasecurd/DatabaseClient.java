package com.example.roomdatabasecurd;

import android.content.Context;
import androidx.room.Room;
public class DatabaseClient {
    private Context context;
    private static DatabaseClient databaseclient;
    private AppDatabase appDatabase;
    private DatabaseClient(Context context){
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"Mydb").build();
    }
    public static synchronized DatabaseClient getInstance(Context context){
        if(databaseclient == null){
            databaseclient = new DatabaseClient(context);
        }
        return databaseclient;
    }
    public AppDatabase appDatabase(){
        return appDatabase;
    }
}
