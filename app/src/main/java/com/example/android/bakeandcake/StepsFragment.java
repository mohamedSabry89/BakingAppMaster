package com.example.android.bakeandcake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StepsFragment extends Fragment {

    protected PlayerView mPlayerView;
    private SimpleExoPlayer player;

    public StepsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.steps_fragment, container, false);

        mPlayerView = rootView.findViewById(R.id.player_view);

        Button nextButton = rootView.findViewById(R.id.next_button);
        Button previousButton = rootView.findViewById(R.id.previous_button);

        return rootView;
    }
}
