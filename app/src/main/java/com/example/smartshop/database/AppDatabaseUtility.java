package com.example.smartshop.database;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseUtility {
    public static AppDatabase getAppDatabase(Context context){
        AppDatabase appDatabase= Room.databaseBuilder(context,AppDatabase.class,"db-SmartShop")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        return appDatabase;
    }
}
