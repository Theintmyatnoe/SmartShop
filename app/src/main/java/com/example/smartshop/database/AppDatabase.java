package com.example.smartshop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smartshop.database.dao.UsersDAO;
import com.example.smartshop.database.model.Users;

@Database(entities = {Users.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersDAO getUsersDAO();
}
