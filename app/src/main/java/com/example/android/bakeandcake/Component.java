package com.example.android.bakeandcake;

import android.os.Parcel;
import android.os.Parcelable;

public class Component implements Parcelable {

    private int id;
    private String name;
    private String servings;
    private String image;

    public Component() {
    }

    public Component(int id, String name, String servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    protected Component(Parcel in) {
        id = in.readInt();
        name = in.readString();
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(servings);
        parcel.writeString(image);
    }
}
