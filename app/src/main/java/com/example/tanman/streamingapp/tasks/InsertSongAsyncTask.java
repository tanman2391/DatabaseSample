package com.example.tanman.streamingapp.tasks;

import android.os.AsyncTask;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.entities.SongEntity;

public class InsertSongAsyncTask extends AsyncTask<SongEntity, Void, Void> {
    private SongDao songDao;

    public InsertSongAsyncTask(SongDao songDao) {
        this.songDao = songDao;
    }

    @Override
    protected Void doInBackground(SongEntity... songs) {
        songDao.insert(songs[0]);
        return null;
    }
}
