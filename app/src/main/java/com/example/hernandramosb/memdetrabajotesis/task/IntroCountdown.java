package com.example.hernandramosb.memdetrabajotesis.task;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.hernandramosb.memdetrabajotesis.classes.AudioType;

import java.util.concurrent.TimeUnit;

/**
 * Created by HernanRB on 14/03/18.
 */

public class IntroCountdown extends CountDownTimer {
    private AudioType audio;

    public IntroCountdown(AudioType audio) {
        super(1000, TimeUnit.SECONDS.toMillis(1));
        this.audio = audio;
        audio.changeColor();
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        audio.resetColor();
    }
}
