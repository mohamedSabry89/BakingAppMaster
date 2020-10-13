package com.example.android.bakeandcake;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StepsJsonUtils {

    public static Steps[] parseStepsJson(Context context, String json) throws JSONException {

        final String MAIN_STEPS = "steps";
        final String STEPS_ID = "id";
        final String STEPS_SHORT_DESC = "shortDescription";
        final String STEPS_DESC = "description";
        final String STEPS_VIDEO_URL = "videoURL";
        final String STEPS_THUMBNAIL_URL = "thumbnailURL";

        JSONObject mainJsonObject = new JSONObject(json);
        JSONArray mainSeps = mainJsonObject.getJSONArray(MAIN_STEPS);
        Steps[] steps = new Steps[(mainSeps.length())];

        for (int i = 0; i < steps.length; i++) {

            int stepsId;
            String sSHortDesc, sDesc, sVideoUrl, sThumUrl;
            Steps theSteps = new Steps();

            stepsId = mainSeps.getJSONObject(i).optInt(STEPS_ID);
            sSHortDesc = mainSeps.getJSONObject(i).getString(STEPS_SHORT_DESC);
            sDesc = mainSeps.getJSONObject(i).getString(STEPS_DESC);
            sVideoUrl = mainSeps.getJSONObject(i).getString(STEPS_VIDEO_URL);
            sThumUrl = mainSeps.getJSONObject(i).getString(STEPS_THUMBNAIL_URL);

            theSteps.setStepsId(stepsId);
            theSteps.setShortDescription(sSHortDesc);
            theSteps.setDescription(sDesc);
            theSteps.setVideoURL(sVideoUrl);
            theSteps.setThumbnailURL(sThumUrl);

            steps[i] = theSteps;
        }

        return steps;
    }
}
