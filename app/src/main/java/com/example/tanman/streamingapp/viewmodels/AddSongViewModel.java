package com.example.tanman.streamingapp.viewmodels;

import android.app.Application;

import com.example.tanman.streamingapp.daos.SongDao;
import com.example.tanman.streamingapp.databases.SongDatabase;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.tasks.InsertSongAsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddSongViewModel extends AndroidViewModel {
    private SongDao songDao;

    public AddSongViewModel(@NonNull Application application) {
        super(application);
        SongDatabase database = SongDatabase.getInstance(application);
        songDao = database.songDao();
    }

    public void insert(SongEntity song) {
        new InsertSongAsyncTask(songDao).execute(song);
    }

}
