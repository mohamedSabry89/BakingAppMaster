package com.example.android.bakeandcake.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakeandcake.R;
import com.example.android.bakeandcake.models.Component;
import com.example.android.bakeandcake.models.Steps;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

public class StepsFragment extends Fragment {

    private SimpleExoPlayer player;
    protected PlayerView mPlayerView;
    TextView stepDescription;
    Component component;
    Steps theSteps;
    int position;
    Uri videoUrl;

    private boolean playWhenReady;
    private int currentWindow = 0;
    private long playbackPosition = 0;


    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            component = bundle.getParcelable("component_key");
            theSteps = bundle.getParcelable("steps_key");
            position = bundle.getInt("position_key", 3);
        }
        theSteps = component.getStepsList().get(position);

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mPlayerView = rootView.findViewById(R.id.player_view);
        Button nextButton = rootView.findViewById(R.id.next_button);
        Button previousButton = rootView.findViewById(R.id.previous_button);
        stepDescription = rootView.findViewById(R.id.tv_description);

        videoUrl = Uri.parse(component.getStepsList().get(position).getVideoURL());
        Log.d("LOG", "the vedio URL : " + videoUrl);
        stepDescription.setText(component.getStepsList().get(position).getDescription());
        initializePlayer();

        return rootView;

    }

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
        player = new SimpleExoPlayer.Builder(getActivity()).build();
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
