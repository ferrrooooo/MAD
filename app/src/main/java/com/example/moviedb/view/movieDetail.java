package com.example.moviedb.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class movieDetail extends AppCompatActivity {

    private TextView lbl_movie_title,lbl_movie_overview,lbl_movie_rating;
    private String movie_id = "";
    private MovieViewModel viewModel;
    private ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdetail);
        viewModel = new ViewModelProvider(movieDetail.this).get(MovieViewModel.class);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");

        img_poster = findViewById(R.id.img_poster_detail);
        lbl_movie_title = findViewById(R.id.lbl_title_detail);
        lbl_movie_overview = findViewById(R.id.lbl_overview_detail);
        lbl_movie_rating = findViewById(R.id.lbl_rd_detail);

        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(movieDetail.this, showResultMovie);

    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = Const.IMG_URL + movies.getPoster_path().toString();
            Glide.with(movieDetail.this).load(img_path).into(img_poster);
            lbl_movie_title.setText(movies.getTitle());
            lbl_movie_overview.setText(movies.getOverview());
            lbl_movie_rating.setText(""+movies.getVote_average());
        }
    };


    @Override
    public void onBackPressed() {
        finish();
    }
}
