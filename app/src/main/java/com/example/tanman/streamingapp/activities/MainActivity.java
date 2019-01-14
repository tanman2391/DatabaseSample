package com.example.tanman.streamingapp.activities;

import android.os.Bundle;

import com.example.tanman.streamingapp.R;
import com.example.tanman.streamingapp.fragments.FavoriteSongsListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, new FavoriteSongsListFragment());
        ft.commit();
    }
}
