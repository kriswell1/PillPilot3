package com.example.pillpilot.ui.main;
import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pillpilot.Pill;
import com.example.pillpilot.PillRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private PillRepository repository;
    private LiveData<List<Pill>> allPills;
    private MutableLiveData<List<Pill>> searchResults;

    public MainViewModel (Application application) {
        super(application);
        repository = new PillRepository(application);
        allPills = repository.getAllPills();
        searchResults = repository.getSearchResults();
    }
    MutableLiveData<List<Pill>> getSearchResults() {
        return searchResults;
    }
    LiveData<List<Pill>> getAllProducts() {
        return allPills;
    }
    public void insertPill(Pill pill) {
        repository.insertPill(pill);
    }
    public void findPill(String name) {
        repository.findPill(name);
    }
    public void deletePill(String name) {
        repository.deletePill(name);
    }


}
