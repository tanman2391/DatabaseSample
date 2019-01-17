package com.example.tanman.streamingapp.viewmodels;

import android.app.Application;

import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.repositories.AddSongRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddSongViewModel extends AndroidViewModel {
    private AddSongRepository addRepo;

    public AddSongViewModel(@NonNull Application application) {
        super(application);
        addRepo = new AddSongRepository(application);
    }

    public void insert(SongEntity song) {
        addRepo.insert(song);
    }

}
