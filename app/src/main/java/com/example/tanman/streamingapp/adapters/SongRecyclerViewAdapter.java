package com.example.tanman.streamingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanman.streamingapp.R;
import com.example.tanman.streamingapp.entities.SongEntity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SongRecyclerViewAdapter extends ListAdapter<SongEntity, SongRecyclerViewAdapter.SongHolder> {
    private final OnItemClickLIstener listener;

    public SongRecyclerViewAdapter(OnItemClickLIstener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<SongEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<SongEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull SongEntity oldSong, @NonNull SongEntity newSong) {
            return oldSong.getId() == newSong.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SongEntity oldSong, @NonNull SongEntity newSong) {
            return oldSong.getTitle().equals(newSong.getTitle()) &&
                    oldSong.getArtist().equals(newSong.getArtist()) &&
                    oldSong.getUrl().equals(newSong.getUrl());
        }
    };

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_song_item, viewGroup, false);
        return new SongHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder, int position) {
        SongEntity currentSong = getItem(position);
        songHolder.textViewTitle.setText(currentSong.getTitle());
        songHolder.textViewArtist.setText(currentSong.getArtist());
        songHolder.textViewUrl.setText(currentSong.getUrl());
    }

    public SongEntity getSongAt(int position) {
        return getItem(position);
    }

    class SongHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewArtist;
        private TextView textViewUrl;

        public SongHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_song_title);
            textViewArtist = itemView.findViewById(R.id.text_view_song_artist);
            textViewUrl = itemView.findViewById(R.id.text_view_song_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickLIstener {
        void onItemClick(SongEntity song);
    }
}
