package com.example.pillpilot;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PillRepository {
    //ez az osztály biztosítja az kapcsolatot az adatbázis és a ViewModel között
    private final MutableLiveData<List<Pill>> searchResults = new MutableLiveData<>();
    private List<Pill> results;
    private final LiveData<List<Pill>> allPills;
    private final PillDao pillDao;
    public PillRepository(Application application) {
        PillRoomDatabase db;
        db = PillRoomDatabase.getDatabase(application);
        pillDao = db.pillDao();
        allPills = pillDao.getAllPills();
    }
  //Interface függvények
  public void insertPill(Pill newpill) {
      ExecutorService executor = Executors.newSingleThreadExecutor();
      executor.submit(() -> {
          pillDao.insertPill(newpill);
      });
      executor.shutdown();
  }
    public void deletePill(String s) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            pillDao.deletePill(s);
        });
        executor.shutdown();
    }
    public void findPill(String s) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            results = pillDao.findPill(s);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public LiveData<List<Pill>> getAllPills() {
        return allPills;
    }
    public MutableLiveData<List<Pill>> getSearchResults() {
        return searchResults;
    }


    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
    public void handleMessage(Message msg) {
        searchResults.setValue(results);
        }
    };



}//class
