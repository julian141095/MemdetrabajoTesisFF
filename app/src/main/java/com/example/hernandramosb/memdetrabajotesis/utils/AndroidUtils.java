package com.example.hernandramosb.memdetrabajotesis.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class AndroidUtils {
    public static void sendToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("DefaultLocale")
    public static String minutesAndSecondsFormat(Long millis) {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}
