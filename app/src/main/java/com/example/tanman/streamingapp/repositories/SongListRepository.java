package com.example.tanman.streamingapp.repositories;

import android.app.Application;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.databases.SongDatabase;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.tasks.DeleteAllSongAsyncTask;
import com.example.tanman.streamingapp.tasks.DeleteSongAsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SongListRepository {
    private SongDao songDao;
    private LiveData<List<SongEntity>> allSongs;

    public SongListRepository(Application application) {
        SongDatabase database = SongDatabase.getInstance(application);
        songDao = database.songDao();
        allSongs = songDao.getAllSongs();
    }

    public void delete(SongEntity song) {
        new DeleteSongAsyncTask(songDao).execute(song);
    }

    public void deleteAll() {
        new DeleteAllSongAsyncTask(songDao).execute();
    }

    public LiveData<List<SongEntity>> getAllSongs() {
        return allSongs;
    }
}
