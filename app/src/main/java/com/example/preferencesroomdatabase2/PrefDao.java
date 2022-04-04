package com.example.preferencesroomdatabase2;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PrefDao {

    @Insert(onConflict = REPLACE)
    void insert(TblPrefs prefs);

    @Query("DELETE FROM my_database")
    void deleteAll();

    @Query("SELECT * from my_database ORDER BY keyP ASC")
    LiveData<List<TblPrefs>> getAllPrefs();

    @Query("SELECT * from my_database ORDER BY keyP ASC")
    List<TblPrefs> getPrefs();

}
