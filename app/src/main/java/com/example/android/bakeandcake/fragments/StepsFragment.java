package com.example.android.bakeandcake.fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.android.bakeandcake.MainActivity;
import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Steps;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.Objects;

public class StepsFragment extends Fragment {

    private SimpleExoPlayer player;
    protected PlayerView mPlayerView;
    TextView stepDescription;
    int position;
    Uri videoUrl;
    ArrayList<Steps> steps;

    private boolean playWhenReady;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    public static final String CURRENT_WINDOW = "current_window";
    public static final String PLAY_WHEN_READY = "ready";
    public static final String PLAYBACK_POSITION = "position";

    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        if (savedInstanceState != null) {
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW);
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY);
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION);
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt(MainActivity.B_POSITION_STEPS_KEY, 0);
            steps = bundle.getParcelableArrayList(MainActivity.B_ARRAY_STEPS_KEY);
        }

        Log.d("LOG", "what the steps is" + position + "\n" + steps);

        mPlayerView = rootView.findViewById(R.id.player_view);
        stepDescription = rootView.findViewById(R.id.tv_description);

        videoUrl = Uri.parse(steps.get(position).getVideoURL());

        if (steps != null) {
            stepDescription.setText(steps.get(position).getDescription());
        }
        initializePlayer();

        Button nextButton = rootView.findViewById(R.id.next_button);
        nextButton.setOnClickListener(view -> {
            if (player != null) {
                player.stop();
            }

            if (position < steps.size() - 1) {
                position++;
            }
            videoUrl = Uri.parse(steps.get(position).getVideoURL());
            stepDescription.setText(steps.get(position).getDescription());
            initializePlayer();
        });

        Button previousButton = rootView.findViewById(R.id.previous_button);
        previousButton.setOnClickListener(view -> {
            if (player != null) {
                player.stop();
            }
            if (position != 0) {
                position--;
            }
            videoUrl = Uri.parse(steps.get(position).getVideoURL());
            stepDescription.setText(steps.get(position).getDescription());
            initializePlayer();
        });


        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            // initialize player
            if (playbackPosition != 0 && player != null) {
                player.seekTo(playbackPosition);
            }
            initializePlayer();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            if (playbackPosition != 0 && player != null) {
                player.seekTo(playbackPosition);
            }
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            player.stop();
            playbackPosition = player.getCurrentPosition();
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 24) {
            player.stop();
            playbackPosition = player.getCurrentPosition();
            releasePlayer();
        }
    }

    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(Objects.requireNonNull(getActivity())).build();
        mPlayerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt(CURRENT_WINDOW, currentWindow);
        currentState.putBoolean(PLAY_WHEN_READY, playWhenReady);
        currentState.putLong(PLAYBACK_POSITION, playbackPosition);
    }
}
