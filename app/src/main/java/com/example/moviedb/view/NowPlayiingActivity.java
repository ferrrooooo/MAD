package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;

public class NowPlayiingActivity extends AppCompatActivity {

    private RecyclerView rv_now_playing;
    private MovieViewModel view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playiing);
        rv_now_playing = findViewById(R.id.rv_now_playing);
        view_model = new ViewModelProvider(NowPlayiingActivity.this).get(MovieViewModel.class);
        view_model.getNowPlaying();
        view_model.getResultGetNowPlaying().observe(NowPlayiingActivity.this, showNowPlaying);

    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(NowPlayiingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayiingActivity.this);
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };
}