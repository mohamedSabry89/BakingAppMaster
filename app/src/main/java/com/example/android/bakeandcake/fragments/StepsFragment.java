package com.example.android.bakeandcake.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakeandcake.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class StepsFragment extends Fragment {

    private SimpleExoPlayer player;
    protected PlayerView mPlayerView;
    TextView stepDescription;

    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mPlayerView = rootView.findViewById(R.id.player_view);
        Button nextButton = rootView.findViewById(R.id.next_button);
        Button previousButton = rootView.findViewById(R.id.previous_button);
        stepDescription = rootView.findViewById(R.id.tv_description);

        return rootView;
    }
}
