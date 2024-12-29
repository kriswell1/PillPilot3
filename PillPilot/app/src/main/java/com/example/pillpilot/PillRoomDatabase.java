package com.example.pillpilot;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Pill.class},/*exportSchema = false,*/ version = 1)
public abstract class PillRoomDatabase extends RoomDatabase {
    public abstract PillDao pillDao();
    private static PillRoomDatabase INSTANCE;

    static PillRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
        synchronized (PillRoomDatabase.class) {
            if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PillRoomDatabase.class,
                            "pill_database").build();
             }
        }
    }
        return INSTANCE;
    }


}//class
