package com.example.androiddeezer2020.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddeezer2020.ListAlbumsActivity;
import com.example.androiddeezer2020.ListTrack;
import com.example.androiddeezer2020.R;
import com.example.androiddeezer2020.service.data.Album;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private static final String TAG = "AlbumAdapter";

    private List<Album> listAlbum;

    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textAlbumName;
        private ImageView imageView;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            //c'est ici que l'on fait nos findView
            textAlbumName = (TextView) itemView.findViewById(R.id.textAlbumName);
            imageView = (ImageView) itemView.findViewById(R.id.imageAlbum);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlbumAdapter(List<Album> listAlbum) {
        this.listAlbum = listAlbum;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_album, parent, false);
        AlbumAdapter.ViewHolder vh = new AlbumAdapter.ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AlbumAdapter.ViewHolder holder, int position) {
        final Album album = listAlbum.get(position);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textAlbumName.setText(album.getTitle());

//        Picasso.with(holder.itemView.getContext())
//                .load(album.getPictureMedium())
//                .centerCrop().fit().into(holder.imageView);
        Picasso.get().load(album.getCoverMedium()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click on <" + album.getTitle()+ ">");
                Intent intent = new Intent(context, ListTrack.class);
                intent.putExtra("album_id", album.getId());
                context. startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listAlbum.size();
    }


}
