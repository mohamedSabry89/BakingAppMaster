package com.example.android.bakeandcake.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Component implements Parcelable {


    private int id;
    private String name;
    private String ingredientsList;
    private ArrayList<Steps> stepsList;
    private String servings;
    private String image;

    public Component() {
    }

    public Component(int id, String name, String ingredientsList, ArrayList<Steps> stepsList, String servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        this.servings = servings;
        this.image = image;
    }

    protected Component(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientsList = in.readString();
        stepsList = new ArrayList<>();
        in.readList(stepsList, Steps.class.getClassLoader());
        servings = in.readString();
        image = in.readString();
    }

    public static final Creator<Component> CREATOR = new Creator<Component>() {
        @Override
        public Component createFromParcel(Parcel in) {
            return new Component(in);
        }

        @Override
        public Component[] newArray(int size) {
            return new Component[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String  getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String  ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ArrayList<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(ingredientsList);
        parcel.writeList(stepsList);
        parcel.writeString(servings);
        parcel.writeString(image);
    }
}
