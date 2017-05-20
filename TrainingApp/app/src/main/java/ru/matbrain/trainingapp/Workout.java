package ru.matbrain.trainingapp;

/**
 * Created by danilzdanov on 20.05.17.
 */

public class Workout {
    int title;
    int description;
    int imageResId;

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public Workout(int title, int description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }
}
