package com.example.e_voting_mobile.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.e_voting_mobile.database.model.UserDetails;

import java.util.Optional;

@Dao
public interface UserDetailsDao {
    @Query("Select * from userdetails where email=:email")
    Optional<UserDetails> getUserByEmail(String email);

    @Insert
    void insertNewUser(UserDetails user);

    @Update
    void updateUser(UserDetails user);

    @Delete
    void deleteUser(UserDetails user);
}
