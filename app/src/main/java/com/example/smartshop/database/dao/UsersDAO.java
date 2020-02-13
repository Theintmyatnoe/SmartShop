package com.example.smartshop.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smartshop.database.model.Users;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert
    void add(Users users);

    @Query("Select * from Users")
    List<Users> getAllUsers();

    @Query("Select * from Users where phoneNo=:phone and password=:password")
    List<Users> selectUserByPhonenoAndPass(String phone,String password);
}
