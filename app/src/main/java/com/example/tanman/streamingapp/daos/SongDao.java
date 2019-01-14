package com.example.tanman.streamingapp.daos;

import com.example.tanman.streamingapp.entities.SongEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SongDao {
    @Insert
    void insert(SongEntity song);

    @Update
    void update(SongEntity song);

    @Delete
    void delete(SongEntity song);

    @Query("DELETE FROM song_table")
    void deleteAllSongs();

    @Query("SELECT * FROM song_table ORDER BY id DESC")
    LiveData<List<SongEntity>> getAllSongs();

}
