package com.example.preferencesroomdatabase2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_database")
public class TblPrefs {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "keyP")
    private String prefKey;

    @NonNull
    @ColumnInfo(name = "value")
    private String prefValue;

    public TblPrefs(@NonNull String prefKey, @NonNull String prefValue) {
        this.prefKey = prefKey;
        this.prefValue = prefValue;
    }

    @NonNull
    public String getPrefKey() {
        return prefKey;
    }

    @NonNull
    public String getPrefValue() {
        return prefValue;
    }
}
