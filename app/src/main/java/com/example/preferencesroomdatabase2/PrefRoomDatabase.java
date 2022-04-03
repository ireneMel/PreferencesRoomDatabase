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

@Database(entities = {TblPrefs.class}, version = 1, exportSchema = false)
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
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateAsync(INSTANCE).execute();
        }


    };

    private static class PopulateAsync extends AsyncTask<Void,Void,Void> {

        private PrefDao dao;
        String background = "background color";
        String text = "text color (example)";
        PopulateAsync(PrefRoomDatabase db){
           dao = db.prefDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();

            TblPrefs pr = new TblPrefs(background, text);
            dao.insert(pr);

            return null;
        }
    }
}
