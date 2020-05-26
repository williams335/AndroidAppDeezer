package com.example.androiddeezer2020;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.androiddeezer2020.adapter.AlbumAdapter;
import com.example.androiddeezer2020.adapter.TrackAdapter;
import com.example.androiddeezer2020.service.DeezerService;
import com.example.androiddeezer2020.service.data.DataAlbum;
import com.example.androiddeezer2020.service.data.DataTrack;
import com.example.androiddeezer2020.service.data.Track;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class ListTrack extends AppCompatActivity {

    private static final String TAG = "ListTrackActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_track);
        Toolbar toolbar = findViewById(R.id.toolbar_2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_circular_2);


        recyclerView = (RecyclerView) findViewById(R.id.track_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final String album_id = getIntent().getStringExtra("album_id");
        Log.d(TAG, "query = "+album_id);
        getSupportActionBar().setTitle(album_id);

        Response.Listener<DataTrack> rep = new Response.Listener<DataTrack>() {

            @Override
            public void onResponse(DataTrack response) {
                Log.d(TAG, "searchTrack Found " + response.getNbTracks() + " Tracks");
                TrackAdapter mAdapter = new TrackAdapter((List<Track>) response.getTracks());
                recyclerView.setAdapter(mAdapter);
                hideProgress();
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "searchTrack onErrorResponse: " + error.getMessage());
                hideProgress();
            }
        };

        showProgress();
        DeezerService.getInstance().searchTrack(album_id, rep, error, this);
    }




    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
