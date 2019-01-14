package com.example.tanman.streamingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tanman.streamingapp.R;
import com.example.tanman.streamingapp.entities.SongEntity;
import com.example.tanman.streamingapp.viewmodels.AddSongViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AddSongFragment extends Fragment {
    View view;
    private AddSongViewModel addSongViewModel;

    private EditText editsongTitle;
    private EditText editsongArtist;
    private EditText editsongURL;
    private TextView textErrorMsg;
    private TextView textUrlErrorMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_song, container, false);
        setHasOptionsMenu(true);

        editsongTitle = view.findViewById(R.id.edit_text_song_title);
        editsongArtist = view.findViewById(R.id.edit_text_song_artist);
        editsongURL = view.findViewById(R.id.edit_text_song_url);
        textErrorMsg = view.findViewById(R.id.text_view_add_song_error_message);
        textUrlErrorMsg = view.findViewById(R.id.text_view_add_song_url_error_message);

        getActivity().setTitle(getString(R.string.fragment_add_song_title));

        addSongViewModel = ViewModelProviders.of(this).get(AddSongViewModel.class);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_song, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_song:
                addSong();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addSong() {
        String title = editsongTitle.getText().toString();
        String artist = editsongArtist.getText().toString();
        String url = editsongURL.getText().toString();

        if (title.trim().isEmpty() || artist.trim().isEmpty() || url.trim().isEmpty()) {
            textErrorMsg.setVisibility(View.VISIBLE);
            return;
        }

        if (!URLUtil.isValidUrl(url.trim())) {

        }
        SongEntity song = new SongEntity(title, artist, url);
        addSongViewModel.insert(song);

        getActivity().getSupportFragmentManager().popBackStack();
    }
}
