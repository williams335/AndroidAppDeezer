package com.example.androiddeezer2020;

import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.androiddeezer2020.adapter.AlbumAdapter;

import com.example.androiddeezer2020.service.DeezerService;
import com.example.androiddeezer2020.service.data.DataAlbum;
import com.example.androiddeezer2020.service.data.Album;
import com.example.androiddeezer2020.service.data.DataSearchArtist;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class ListAlbumsActivity extends AppCompatActivity {

    private static final String TAG = "ListAlbumsActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_albums);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_circular1);


        recyclerView = (RecyclerView) findViewById(R.id.album_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final String artist = getIntent().getStringExtra("artist");
        Log.d(TAG, "query = "+artist);
        getSupportActionBar().setTitle(artist);

        Response.Listener<DataAlbum> rep = new Response.Listener<DataAlbum>() {
            @Override
            public void onResponse(DataAlbum response) {
                Log.d(TAG, "searchAlbum Found " + response.getTotal() + " Albums");
                AlbumAdapter mAdapter = new AlbumAdapter(response.getData());
                recyclerView.setAdapter(mAdapter);
                hideProgress();
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "searchAlbum onErrorResponse: " + error.getMessage());
                hideProgress();
            }
        };

        showProgress();
        DeezerService.searchAlbum(artist, rep, error, this);
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
