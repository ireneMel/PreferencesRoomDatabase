package com.example.preferencesroomdatabase2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PrefDao {

    @Insert
    void insert(TblPrefs prefs);

    @Query("DELETE FROM my_database")
    void deleteAll();

    @Query("SELECT * from my_database ORDER BY keyP ASC")
    LiveData<List<TblPrefs>> getAllPrefs();

}
