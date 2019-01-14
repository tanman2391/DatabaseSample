package com.example.tanman.streamingapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanman.streamingapp.R;
import com.example.tanman.streamingapp.adapters.SongRecyclerViewAdapter;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.viewmodels.SongViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteSongsListFragment extends Fragment implements View.OnClickListener, SongRecyclerViewAdapter.OnItemClickLIstener {
    View view;
    private SongViewModel songViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite_song_list, container, false);
        setHasOptionsMenu(true);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        final SongRecyclerViewAdapter adapter = new SongRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_to_list);
        fab.setOnClickListener(this);

        songViewModel = ViewModelProviders.of(this).get(SongViewModel.class);
        songViewModel.getAllSongs().observe(this, new Observer<List<SongEntity>>() {
            @Override
            public void onChanged(List<SongEntity> songEntities) {
                adapter.submitList(songEntities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                songViewModel.delete(adapter.getSongAt(viewHolder.getAdapterPosition()));
                Snackbar snackbar = Snackbar.make(view.findViewById(R.id.recycler_view), R.string.delete_message, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.app_name));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite_song_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_songs:
                songViewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_to_list:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new AddSongFragment());
                ft.addToBackStack(null).commit();
        }
    }

    @Override
    public void onItemClick(SongEntity song) {
        try {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getUrl()));
            startActivity(browse);
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(view.findViewById(R.id.recycler_view), R.string.url_load_error_message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }
}
