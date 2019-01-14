package com.example.tanman.streamingapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "song_table")
public class SongEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String artist;
    private String url;

    public SongEntity(String title, String artist, String url) {
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }
}
