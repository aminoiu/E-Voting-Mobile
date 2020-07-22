package com.example.e_voting_mobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.e_voting_mobile.database.dao.UserDetailsDao;
import com.example.e_voting_mobile.database.model.UserDetails;

@Database(entities = UserDetails.class, exportSchema = false, version = 1)
public abstract class UserDetailsDatabase extends RoomDatabase {
    private static final String DB_NAME = "user_details_db";
    private static UserDetailsDatabase instance;

    public static synchronized UserDetailsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDetailsDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDetailsDao userDetailsDao();
}
