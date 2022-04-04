package com.example.preferencesroomdatabase2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {TblPrefs.class}, version = 2, exportSchema = false)
public abstract class PrefRoomDatabase extends RoomDatabase {

    public abstract PrefDao prefDao();

    private static PrefRoomDatabase INSTANCE;

    public static PrefRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (PrefRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PrefRoomDatabase.class, "my_database")
                            .fallbackToDestructiveMigration()
//                            .addCallback(callback) //to clean db
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsync(INSTANCE).execute();
        }

//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db){
//            super.onOpen(db);
//            new PopulateAsync(INSTANCE).execute();
//        }

    };

    public static class PopulateAsync extends AsyncTask<Void,Void,Void> {

        private PrefDao dao;
        PopulateAsync(PrefRoomDatabase db){
           dao = db.prefDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
