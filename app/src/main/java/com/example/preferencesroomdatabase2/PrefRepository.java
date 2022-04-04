package com.example.preferencesroomdatabase2;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PrefRepository {

    private PrefDao mDao;
    private LiveData<List<TblPrefs>> mAllPrefs;

    PrefRepository(Application application) {
        PrefRoomDatabase db = PrefRoomDatabase.getINSTANCE(application);
        mDao = db.prefDao();
        mAllPrefs = mDao.getAllPrefs();
    }

    public void insert(TblPrefs pref) {
        new InsertAsync(mDao).execute(pref);
    }

    LiveData<List<TblPrefs>> getAllPrefs() {
        return mAllPrefs;
    }

    private static class InsertAsync extends AsyncTask<TblPrefs, Void, Void> {

        private final PrefDao mDao;

        InsertAsync(PrefDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(TblPrefs... tblPrefs) {
            mDao.insert(tblPrefs[0]);
            return null;
        }
    }
}
