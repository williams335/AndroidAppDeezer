package com.example.androiddeezer2020;

import android.media.MediaPlayer;

public class MediaPlayerSingleton {

        private static MediaPlayer singleton;

        private MediaPlayerSingleton() {

        }

        public static MediaPlayer getInstance() {
            if (singleton == null) {
                singleton = new MediaPlayer();
            }
            return singleton;
        }
    }


