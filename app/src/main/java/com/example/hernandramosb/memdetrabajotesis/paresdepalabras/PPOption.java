package com.example.hernandramosb.memdetrabajotesis.paresdepalabras;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class PPOption {
    private int idSound1;
    private int idSound2;
    private String textSound1;
    private String textSound2;
    private Button buttonAnswer;
    private UUID uniqueId;



    public PPOption(int idSound1, int idSound2, String textSound1, String textSound2, Button buttonAnswer) {
        this.idSound1 = idSound1;
        this.idSound2 = idSound2;
        this.textSound1 = textSound1;
        this.textSound2 = textSound2;
        this.buttonAnswer = buttonAnswer;
        this.uniqueId = UUID.randomUUID();
    }

    public int getIdSound1() {
        return idSound1;
    }

    public int getIdSound2() {
        return idSound2;
    }

    public String getTextSound1() {
        return textSound1;
    }

    public String getTextSound2() {
        return textSound2;
    }

    public Button getButtonAnswer() {
        return buttonAnswer;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public boolean isCorrectAnswer(String selected) {
        if (textSound1.equalsIgnoreCase(selected)) {
            return true;
        }
        return false;
    }
    public void playAllWithPlaceholders(Context context, TextView textView1stWord, TextView textView2ndWord) {
        //MediaPlayer h;
        MediaPlayer audio = MediaPlayer.create(context, idSound1);
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        textView1stWord.setText(textSound1);
        delay500();

        audio = MediaPlayer.create(context, idSound2);
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        textView2ndWord.setText(textSound2);
    }

    private void delay500() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.print(e);
            System.out.print("Error ocurrido");
        }
    }

    private void delay2000() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.print(e);
            System.out.print("Error ocurrido");
        }
    }

}
