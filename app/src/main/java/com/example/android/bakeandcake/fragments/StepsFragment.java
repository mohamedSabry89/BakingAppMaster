package com.example.android.bakeandcake.fragments;

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
    Steps theSteps;
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


        if (savedInstanceState != null) {
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW);
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY);
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION);
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            theSteps = bundle.getParcelable("steps_key");
            position = bundle.getInt("position_key", 0);
            steps = bundle.getParcelableArrayList("array_steps_key");
        }

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mPlayerView = rootView.findViewById(R.id.player_view);
        stepDescription = rootView.findViewById(R.id.tv_description);

        Button nextButton = rootView.findViewById(R.id.next_button);
        nextButton.setOnClickListener(view -> {
            if (player != null) {
                player.stop();
            }

            if (position < steps.size() - 1) {
                position++;
            }
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
            stepDescription.setText(steps.get(position).getDescription());
            initializePlayer();
        });

        if (steps != null) {
            stepDescription.setText(steps.get(position).getDescription());
        }
        initializePlayer();

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            if (playbackPosition != 0 && player != null) {
                player.seekTo(playbackPosition);
            }
            initializePlayer();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            playbackPosition = player.getCurrentPosition();
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT < 24) {
            initializePlayer();
            if (playbackPosition != 0) {
                player.seekTo(playbackPosition);
            }
        }
    }

    private void initializePlayer() {
        videoUrl = Uri.parse(steps.get(position).getVideoURL());
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
}
