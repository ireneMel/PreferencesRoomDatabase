package com.example.preferencesroomdatabase2;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PrefViewModel extends AndroidViewModel {

    private PrefRepository mRepo;
    private LiveData<List<TblPrefs>> mAllPrefs;

    public PrefViewModel(@NonNull Application application) {
        super(application);
        mRepo = new PrefRepository(application);
        mAllPrefs = mRepo.getAllPrefs();
    }

    LiveData<List<TblPrefs>> getAllPrefs(){
        return mAllPrefs;
    }

    public void insert(TblPrefs pref) {
        mRepo.insert(pref);
    }

}
