package com.example.e_voting_mobile.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Getter;

@Entity(tableName = "UserDetails")
@Getter
public class UserDetails {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "work_place")
    private String workPlace;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "street")
    private String street;
    @ColumnInfo(name = "birth_date")
    private String birthDate;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    public UserDetails(int id, String name, String email, String workPlace, String country, String street, String birthDate, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.workPlace = workPlace;
        this.country = country;
        this.street = street;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    @Ignore
    public UserDetails(String name, String email, String workPlace, String country, String street, String birthDate, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.workPlace = workPlace;
        this.country = country;
        this.street = street;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
}
