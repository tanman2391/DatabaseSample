package com.example.tanman.streamingapp.viewmodels;

import android.app.Application;

import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.repositories.SongListRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SongViewModel extends AndroidViewModel {
    private SongListRepository songRepo;
    private LiveData<List<SongEntity>> allSongs;

    public SongViewModel(@NonNull Application application) {
        super(application);
        songRepo = new SongListRepository(application);
        allSongs = songRepo.getAllSongs();
    }

    public void delete(SongEntity song) {
        songRepo.delete(song);
    }

    public void deleteAll() {
        songRepo.deleteAll();
    }

    public LiveData<List<SongEntity>> getAllSongs() {
        return allSongs;
    }
}
