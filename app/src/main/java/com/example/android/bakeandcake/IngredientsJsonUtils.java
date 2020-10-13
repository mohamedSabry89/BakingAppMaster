package com.example.android.bakeandcake;

import android.content.Context;
import android.graphics.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IngredientsJsonUtils {

    public static Ingredients[] parseIngredientsJson(Context context, String json) throws JSONException {

        final String MAIN_INGREDIENT = "ingredients";
        final String QTY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENTS = "ingredient";

        JSONObject mainJsonObject = new JSONObject(json);
        JSONArray mainIngredients = mainJsonObject.getJSONArray(MAIN_INGREDIENT);
        Ingredients[] ingredients = new Ingredients[(mainIngredients.length())];

        for (int i = 0; i < ingredients.length; i++) {
            String theMeasure, theIngredient;
            int theQty;
            Ingredients ing = new Ingredients();

            theQty = mainIngredients.getJSONObject(i).optInt(QTY);
            theMeasure = mainIngredients.getJSONObject(i).optString(MEASURE);
            theIngredient = mainIngredients.getJSONObject(i).optString(INGREDIENTS);

            ing.setQuantity(theQty);
            ing.setMeasure(theMeasure);
            ing.setIngredient(theIngredient);

            ingredients[i] = ing;
        }


        return ingredients;
    }
}
