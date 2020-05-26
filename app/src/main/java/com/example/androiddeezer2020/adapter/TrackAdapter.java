package com.example.androiddeezer2020.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddeezer2020.MediaPlayerSingleton;
import com.example.androiddeezer2020.R;
import com.example.androiddeezer2020.service.data.Track;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private static final String TAG = "TrackAdapter";

    private List<Track> listTrack;

    public Context context;

    private MediaPlayer mediaPlayer = MediaPlayerSingleton.getInstance();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTrackName;
        private ImageView imageView;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            //c'est ici que l'on fait nos findView
            textTrackName = (TextView) itemView.findViewById(R.id.textTrackName);
            imageView = (ImageView) itemView.findViewById(R.id.imageTrack);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackAdapter(List<Track> listTrack) {
        this.listTrack = listTrack;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_track, parent, false);
        TrackAdapter.ViewHolder vh = new TrackAdapter.ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TrackAdapter.ViewHolder holder, int position) {
        final Track track = listTrack.get(position);



        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textTrackName.setText(track.getTitle());

//        Picasso.with(holder.itemView.getContext())
//                .load(track.getPictureMedium())
//                .centerCrop().fit().into(holder.imageView);
       // Picasso.get().load(track.get).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click on <" + track.getTitle()+ ">");
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.release();
                }
                MediaPlayer mediaPlayer = MediaPlayerSingleton.getInstance();
                mediaPlayer.reset();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(track.getPreview());
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listTrack.size();
    }


}
