package com.example.android.bakingapp.models;

import com.example.android.bakingapp.recipeApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName(ApiUtilities.STEP_ID)
    private int mId;

    @SerializedName(ApiUtilities.STEP_SHORT_DESCRIPTION)
    private String mShortDescription;

    @SerializedName(ApiUtilities.STEP_DESCRIPTION)
    private String mDescription;

    @SerializedName(ApiUtilities.STEP_VIDEO_URL)
    private String mVideoUrl;

    @SerializedName(ApiUtilities.STEP_THUMBNAIL_URL)
    private String mThumbnailUrl;


    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.mId = id;
        this.mShortDescription = shortDescription;
        this.mDescription = description;
        this.mVideoUrl = videoUrl;
        this.mThumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.mThumbnailUrl = thumbnailUrl;
    }
}
