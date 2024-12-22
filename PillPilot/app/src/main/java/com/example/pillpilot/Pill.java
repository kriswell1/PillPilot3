package com.example.pillpilot;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "pills")
public class Pill {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "pillId")
    private int id;  //elsődleges kulcs, auto inkrementálás
    @ColumnInfo(name="pillName")
    private String name;
    @ColumnInfo(name="pillDate")
    private String date;

    private String time;    //itt nincs oszlopjelölés mert erre nem keresünk rá

    //konstruktor
    public Pill(String name,String date,String time){
            this.name=name;
            this.date=date;
            this.time=time;
    }
    //getterek
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    //setterek
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time){
        this.time=time;
    }
}
