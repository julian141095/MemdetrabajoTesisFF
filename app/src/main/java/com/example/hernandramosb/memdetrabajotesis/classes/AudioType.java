package com.example.hernandramosb.memdetrabajotesis.classes;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

import com.example.hernandramosb.memdetrabajotesis.utils.AndroidUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
/**
 * Created by HernanRB on 4/04/18.
 */

public class AudioType {
    private int color;
    private Button button;
    private int id , optionNumber;
    private long startTimeMillis = 0L;
    private long finishTimeMillis = 0L;

    public AudioType(int optionNumber, int id, int color,  Button button) {
        this.optionNumber = optionNumber;
        this.id = id;
        this.color = color;
        this.button = button;
    }

    public AudioType(String string) {
        String[] split = string.split(", ");
        if (split.length != 3) {
            Exception exception = new Exception();
            exception.printStackTrace();
            return;
        }

        this.color = Integer.parseInt(split[0]);
        this.startTimeMillis = Long.parseLong(split[1]);
        this.finishTimeMillis = Long.parseLong(split[2]);

    }

    public Long getElapsedTime() {
        return finishTimeMillis - startTimeMillis;
    }

    public String getDisplayElapsedTime() {
        return AndroidUtils.minutesAndSecondsFormat(getElapsedTime());
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public long getFinishTimeMillis() {
        return finishTimeMillis;
    }

    public void setFinishTimeMillis() {
        this.finishTimeMillis = System.currentTimeMillis();
    }

    public void setStartTimeMillisNow() {
        this.startTimeMillis = System.currentTimeMillis();
    }

    public void changeColor() {
        button.setBackgroundColor(color);
    }

    public void resetColor() {
        button.setBackgroundColor(Color.LTGRAY);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return color + ", " + startTimeMillis + ", " + finishTimeMillis;
    }
}
