package com.example.tanman.streamingapp.repositories;

import android.app.Application;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.databases.SongDatabase;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.tasks.InsertSongAsyncTask;

public class AddSongRepository {
    private SongDao songDao;

    public AddSongRepository(Application application) {
        SongDatabase database = SongDatabase.getInstance(application);
        songDao = database.songDao();
    }

    public void insert(SongEntity song) {
        new InsertSongAsyncTask(songDao).execute(song);
    }

}
