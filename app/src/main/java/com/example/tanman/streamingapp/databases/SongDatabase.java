package com.example.tanman.streamingapp.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.entities.SongEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SongEntity.class}, version = 1)
public abstract class SongDatabase extends RoomDatabase {

    private static SongDatabase instance;

    public abstract SongDao songDao();

    public static synchronized SongDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SongDatabase.class, "song_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDatabaseAsyncTask(instance).execute();
        }
    };

    // Used for pre populating the database for testing
    private static class populateDatabaseAsyncTask extends AsyncTask {
        private SongDao songDao;

        private populateDatabaseAsyncTask(SongDatabase database) {
            songDao = database.songDao();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            songDao.insert(new SongEntity("In The End", "Linkin Park", "https://www.youtube.com/watch?v=eVTXPUF4Oz4"));
            songDao.insert(new SongEntity("The Sound Of Silence", "Disturbed", "https://www.youtube.com/watch?v=u9Dg-g7t2l4"));
            songDao.insert(new SongEntity("In Between", "Beartooth", "https://soundcloud.com/red-bull-records/in-between-1"));
            songDao.insert(new SongEntity("Zombie", "Bad Wolves", "https://soundcloud.com/badwolves-music/zombie-1"));
            return null;
        }
    }

}
