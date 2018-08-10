package com.example.android.bakingapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeActivity;
import com.example.android.bakingapp.clickHandlers.StepNavigationOnClickHandler;
import com.example.android.bakingapp.models.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;;import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepFragment extends Fragment {

    public static final String STEP_OBJECT = "step_object";
    public static final String LAST_STEP_INDEX = "last_step_index";
    public static final String STEP_INDEX = "step_index";
    public static final String PLAYER_CURRENT_POSITION = "player_current_position";
    public static final String PLAYER_CURRENT_WINDOW_INDEX = "player_current_window_index";
    public static final String PLAYER_PLAY_WHEN_READY = "player_play_when_ready";

    // Tag for logging
    private static final String TAG = RecipeFragment.class.getSimpleName();
    private static int FIRST_STEP_INDEX = 0;

    private Step mStep;
    private int mLastStepIndex;
    private int mStepIndex;
    private boolean mTwoPane;
    private StepNavigationOnClickHandler mStepNavigationOnClickHandler;
    private SimpleExoPlayer mExoPlayer;
    @BindView(R.id.step_player_view)
    PlayerView mPlayerView;
    private long mPlayerCurrentPosition;
    private int mPlayerCurrentWindowIndex;
    private boolean mPlayerPlayWhenReady;

    public RecipeStepFragment() {
        mTwoPane = false;
        mLastStepIndex = 0;
        mStepIndex = 0;
        mPlayerPlayWhenReady = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(STEP_OBJECT);
            mLastStepIndex = savedInstanceState.getInt(LAST_STEP_INDEX);
            mStepIndex = savedInstanceState.getInt(STEP_INDEX);
            mPlayerCurrentPosition = savedInstanceState.getLong(PLAYER_CURRENT_POSITION);
            mPlayerCurrentWindowIndex = savedInstanceState.getInt(PLAYER_CURRENT_WINDOW_INDEX);
            mPlayerPlayWhenReady = savedInstanceState.getBoolean(PLAYER_PLAY_WHEN_READY);
        }

        View rootView = new View(inflater.getContext());

        if (mStep != null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

            ButterKnife.bind(this, rootView);

            if (mStep.getThumbnailUrl().isEmpty()) {
                mPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.art_cake));
            } else {
                Picasso.get()
                        .load(mStep.getThumbnailUrl())
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                mPlayerView.setDefaultArtwork(bitmap);
                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
            }

            TextView textView = rootView.findViewById(R.id.recipe_detail_description_text_view);
            textView.setText(mStep.getDescription());

            Button nextButton = rootView.findViewById(R.id.next_step_button);
            int nextStep = mStepIndex + 1;
            String nextStepText = getResources().getString(R.string.step_button_text, nextStep);
            nextButton.setText(nextStepText);

            Button previousButton = rootView.findViewById(R.id.previous_step_button);
            int previousStep = mStepIndex - 1;
            String previousStepText = getResources().getString(R.string.step_button_text, previousStep);
            previousButton.setText(previousStepText);

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
                        mStepNavigationOnClickHandler.onPreviousClick(v);
                    }
                });
                int orientation = getActivity().getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    mPlayerView.setLayoutParams(layoutParams);
                }
            }

        } else {
            Log.v(TAG, "This fragment has a null Step");
        }


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //hideSystemUi();
        if ((Util.SDK_INT <= Build.VERSION_CODES.M || mExoPlayer == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mPlayerCurrentPosition = mExoPlayer.getCurrentPosition();
            mPlayerCurrentWindowIndex = mExoPlayer.getCurrentWindowIndex();
            mPlayerPlayWhenReady = mExoPlayer.getPlayWhenReady();
        }
        if (Util.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

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
        outState.putLong(PLAYER_CURRENT_POSITION, mPlayerCurrentPosition);
        outState.putBoolean(PLAYER_PLAY_WHEN_READY, mPlayerPlayWhenReady);
        outState.putInt(PLAYER_CURRENT_WINDOW_INDEX, mPlayerCurrentWindowIndex);
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

    private void initializePlayer() {
        if (mExoPlayer == null && getContext() != null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            DefaultRenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(getContext());

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(defaultRenderersFactory, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.setPlayWhenReady(mPlayerPlayWhenReady);
            mExoPlayer.seekTo(mPlayerCurrentWindowIndex, mPlayerCurrentPosition);
        }

        mExoPlayer.prepare(buildMediaSource(mStep.getVideoUrl()), false, true);
    }

    private MediaSource buildMediaSource(String videoUrl) {
        Uri videoUri = Uri.parse(videoUrl);
        String userAgent = com.google.android.exoplayer2.util.Util.getUserAgent(getContext(), getString(R.string.app_name));
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(getContext(), userAgent);
        MediaSource mediaSource;
        mediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory)
                .createMediaSource(videoUri);

        return mediaSource;
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
