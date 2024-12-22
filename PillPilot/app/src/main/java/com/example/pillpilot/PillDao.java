package com.example.pillpilot;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PillDao {
@Insert
    void insertPill(Pill pill);
    @Query("SELECT * FROM pills WHERE pillName = :name")
    List<Pill> findPill(String name);
    @Query("DELETE FROM pills WHERE pillName = :name")
    void deletePill(String name);
    @Query("SELECT * FROM pills")
    LiveData<List<Pill>> getAllPills();        //ez szinkronizálja a RecyclerViewt az adatbázissal
}//interface
