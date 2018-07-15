package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.clickHandlers.StepNavigationOnClickHandler;
import com.example.android.bakingapp.models.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class RecipeStepFragment extends Fragment {

    public static final String STEP_OBJECT = "step_object";
    public static final String LAST_STEP_INDEX = "last_step_index";
    public static final String STEP_INDEX = "step_index";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();
    private static int FIRST_STEP_INDEX = 0;

    private Step mStep;
    private int mLastStepIndex;
    private int mStepIndex;
    private boolean mTwoPane;
    private StepNavigationOnClickHandler mStepNavigationOnClickHandler;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mSimpleExoPlayerView;

    public RecipeStepFragment() {
        mTwoPane = false;
        mLastStepIndex = 0;
        mStepIndex = 0;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_OBJECT);
            mLastStepIndex = savedInstanceState.getInt(LAST_STEP_INDEX);
            mStepIndex = savedInstanceState.getInt(STEP_INDEX);
        }

        View rootView = new View(inflater.getContext());

        if (mStep != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

            mSimpleExoPlayerView = rootView.findViewById(R.id.step_player_view);
            mSimpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.art_cake));
            initializePlayer(mStep.getVideoUrl());

            TextView textView = rootView.findViewById(R.id.recipe_detail_description_text_view);
            textView.setText(mStep.getDescription());

            Button nextButton = rootView.findViewById(R.id.next_step_button);
            int nextStep = mStepIndex + 1;
            nextButton.setText("Step " + nextStep);
            Button previousButton = rootView.findViewById(R.id.previous_step_button);
            int previousStep = mStepIndex - 1;
            previousButton.setText("Step " + previousStep);

            if (mTwoPane) {
                nextButton.setVisibility(View.GONE);
                previousButton.setVisibility(View.GONE);
                View navigationContainer = rootView.findViewById(R.id.step_navigation_container);
                navigationContainer.setVisibility(View.GONE);
            } else {
                if (mStepIndex == mLastStepIndex) {
                    nextButton.setVisibility(View.GONE);
                }
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mStepNavigationOnClickHandler.onNextClick(v);
                    }
                });

                if (mStepIndex == FIRST_STEP_INDEX) {
                    previousButton.setVisibility(View.GONE);
                }
                previousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mStepNavigationOnClickHandler.onPreciousClick(v);
                    }
                });
            }

        } else {
            Log.v(TAG, "This fragment has a null Step");
        }


        return rootView;
    }

    private void initializePlayer(String videoUrl) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mSimpleExoPlayerView.setPlayer(mExoPlayer);

            Uri myUri = Uri.parse(videoUrl);
            String userAgent = "Step Player";
            MediaSource mediaSource = new ExtractorMediaSource(myUri, new DefaultDataSourceFactory(getContext(),
                    userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        releasePlayer();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        releasePlayer();
//    }

    public void setStep(Step step) {
        mStep = step;
    }

    public void setLastStepIndex(int stepListSize) {
        mLastStepIndex = stepListSize - 1;
    }

    public void setStepIndex(int stepIndex) {
        mStepIndex = stepIndex;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP_OBJECT, mStep);
        outState.putInt(LAST_STEP_INDEX, mLastStepIndex);
        outState.putInt(STEP_INDEX, mStepIndex);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mTwoPane = RecipeActivity.getTwoPane();
        if (!mTwoPane) {
            try {
                mStepNavigationOnClickHandler = (StepNavigationOnClickHandler) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement StepNavigationOnClickHandler");
            }
        }

    }
}
