package com.example.tanman.streamingapp.viewmodels;

import android.app.Application;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.databases.SongDatabase;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.tasks.DeleteAllSongAsyncTask;
import com.example.tanman.streamingapp.tasks.DeleteSongAsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SongViewModel extends AndroidViewModel {
    private SongDao songDao;
    private LiveData<List<SongEntity>> allSongs;

    public SongViewModel(@NonNull Application application) {
        super(application);
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
