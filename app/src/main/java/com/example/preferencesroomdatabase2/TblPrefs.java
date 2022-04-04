package com.example.preferencesroomdatabase2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_database")
public class TblPrefs {

    @ColumnInfo(name = "keyP")
    private String prefKey;

    @NonNull
    @ColumnInfo(name = "value")
    private String prefValue;

    public void setMainKey(@NonNull String mainKey) {
        this.mainKey = mainKey;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "mainKey")
    private String mainKey;

    public TblPrefs(@NonNull String prefKey, @NonNull String prefValue) {
        this.prefKey = prefKey;
        this.prefValue = prefValue;
        mainKey = prefKey + prefValue;
    }

    @NonNull
    public String getPrefKey() {
        return prefKey;
    }

    @NonNull
    public String getPrefValue() {
        return prefValue;
    }

    @NonNull
    public String getMainKey() {
        return mainKey;
    }
}
