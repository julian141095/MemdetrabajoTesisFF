package com.example.hernandramosb.memdetrabajotesis;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hernandramosb.memdetrabajotesis.classes.AudioType;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MemAuditiva1 extends AppCompatActivity implements View.OnClickListener {
    //Random rand = new Random();
    private Handler mHandler;


    Timer timerAsync = new Timer();
    ImageView MAIncorrecto, MACorrecto, MAC;
    Button MANota1, MANota2, MANota3, MANota4, MAComenzar;
    TextView textViewTest;
    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage(" ¿Estás seguro que deseas salir de Memoria Auditiva?");
        myBuild.setTitle("Mensaje");
        myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener(){
            @Override

            public void onClick(DialogInterface dialog, int which){
                finish();
            }
        });

        myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

    AudioType[] MAaudio;
    int u=0,cantidad=4,nasd,MAcountcor;
    int MAControl[]= new int[cantidad];
    int MACorreccion[]= new int[cantidad];


    Integer n = 3;

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_auditiva1);

        MAComenzar = (Button) findViewById(R.id.MAComenzar);
        MANota1 = (Button) findViewById(R.id.Audio1);
        MANota2 = (Button) findViewById(R.id.Audio2);
        MANota3 = (Button) findViewById(R.id.Audio3);
        MANota4 = (Button) findViewById(R.id.Audio4);

        mHandler = new Handler();
        MANota1.setEnabled(false);
        MANota2.setEnabled(false);
        MANota3.setEnabled(false);
        MANota4.setEnabled(false);

        MACorrecto = (ImageView) findViewById(R.id.MACorrecto);
        MAIncorrecto = (ImageView) findViewById(R.id.MAIncorrecto);
        MACorrecto.setVisibility(View.INVISIBLE);
        MAIncorrecto.setVisibility(View.INVISIBLE);
        MAaudio = new AudioType[]{
                new AudioType(1,R.raw.notaa, Color.BLUE, MANota1),
                new AudioType(2,R.raw.notab, Color.RED, MANota2),
                new AudioType(3,R.raw.notac, Color.CYAN, MANota3),
                new AudioType(4,R.raw.notad, Color.GREEN, MANota4),


        };

    }

    private void delay1000() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.print("Error ocurrido");
        }
    }
    private void delay5() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }
    }


    public void onClick(View view) {


        MAComenzar.setEnabled(false);
        MAControl[u]=(int)(Math.random()*4);
        for(u=1;u<cantidad;u++){
            MAControl[u]=(int)(Math.random()*4);
            for(int r=0;r<u;r++){
                if(MAControl[u]==MAControl[r]){
                    u--;
                }

            }
        }

        startRepeatingTask();



    }

    void startRepeatingTask() {
        TimerTask timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(internalTask);
            }
        };
        //AQUIU SE DECLARA EL TASK CON PERIODO PARA 1s
        timerAsync.schedule(timerTaskAsync, 0, 1000);
    }

    private void stopRepeatingTask(){
        //mHandler.removeCallbacks(mStatusChecker);
        mHandler.removeCallbacks(internalTask);
        timerAsync.cancel();
        MANota1.setEnabled(true);
        MANota2.setEnabled(true);
        MANota3.setEnabled(true);
        MANota4.setEnabled(true);

    }

    private Runnable internalTask = new Runnable() {
        int index = 0;
        AudioType type;
        @Override
        public void run() {
            if (type != null) type.resetColor();
            if (index >= MAaudio.length) {

                stopRepeatingTask();

            } else {
                type = MAaudio[MAControl[index]];
                type.changeColor();
                audio = MediaPlayer.create(getApplicationContext(), type.getId());
                audio.start();
                audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
            index++;
        }
    };

    public void onClickBtn1(View view){
        AudioType audioType = MAaudio[0];
        audio = MediaPlayer.create(getApplicationContext(), audioType.getId());
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });

        delay1000();
        MACorreccion[MAcountcor]=0;
        if (MAControl.length >= MACorreccion.length) {
            getNextAnswerAndSetStartTime();
            if (MACorreccion[MAcountcor] == MAControl[MAcountcor]) {
                MANota1.setEnabled(false);
                MANota1.setBackgroundColor(audioType.getColor());
                audioType.setFinishTimeMillis();
            }
        }else{
            Intent Memaudres = new Intent(getApplicationContext(), Memauditivares.class);
            startActivity(Memaudres);
        }
        if (hasFinished()) {
            onTestFinished();
        }if (audioType != null)
            audioType.setFinishTimeMillis();
        MAcountcor++;
    }

    public void onClickBtn2(View view){
        AudioType audioType = MAaudio[1];
        audio = MediaPlayer.create(getApplicationContext(), audioType.getId());
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });

        delay1000();

        MACorreccion[MAcountcor]=1;
        if (MAControl.length >= MACorreccion.length) {
            getNextAnswerAndSetStartTime();
            if (MACorreccion[MAcountcor] == MAControl[MAcountcor]) {
                MANota2.setEnabled(false);
                MANota2.setBackgroundColor(audioType.getColor());
                audioType.setFinishTimeMillis();
            }
        }
        if (MACorreccion.length > MAControl.length){
            Intent Memaudres = new Intent(getApplicationContext(), Memauditivares.class);
            startActivity(Memaudres);
        }if (audioType != null)
            audioType.setFinishTimeMillis();
        MAcountcor++;
        if (hasFinished()) {
            onTestFinished();
        }
    }

    public void onClickBtn3(View view){
        AudioType audioType = MAaudio[2];
        audio = MediaPlayer.create(getApplicationContext(), audioType.getId());
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        //IMANota3.setVisibility(View.VISIBLE);
        delay1000();
        //IMANota3.setVisibility(View.INVISIBLE);
        MACorreccion[MAcountcor]=2;
        if (MAControl.length >= MACorreccion.length) {
            getNextAnswerAndSetStartTime();
            if (MACorreccion[MAcountcor] == MAControl[MAcountcor]) {
                MANota3.setEnabled(false);
                MANota3.setBackgroundColor(audioType.getColor());
                audioType.setFinishTimeMillis();
            }
        }else{
            Intent Memaudres = new Intent(getApplicationContext(), Memauditivares.class);
            startActivity(Memaudres);
        }if (audioType != null)
            audioType.setFinishTimeMillis();
        MAcountcor++;
        if (hasFinished()) {
            onTestFinished();
        }
    }

    public void onClickBtn4(View view){
        AudioType audioType = MAaudio[3];
        audio = MediaPlayer.create(getApplicationContext(), audioType.getId());
        audio.start();
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });

        delay1000();

        MACorreccion[MAcountcor]=3;
        if (MAControl.length >= MACorreccion.length) {
            getNextAnswerAndSetStartTime();
            if (MACorreccion[MAcountcor] == MAControl[MAcountcor]) {
                MANota4.setEnabled(false);
                MANota4.setBackgroundColor(audioType.getColor());
                audioType.setFinishTimeMillis();
            }
        }else{
            Intent Memaudres = new Intent(getApplicationContext(), Memauditivares.class);
            startActivity(Memaudres);
        }if (audioType != null)
            audioType.setFinishTimeMillis();
        MAcountcor++;
        if (hasFinished()) {
            onTestFinished();
        }
    }



    public boolean hasFinished() {
        if (MAcountcor == MAControl.length){
            return true;
        }
        return false;
    }

    public AudioType getNextAnswerAndSetStartTime() {
        int index = MAcountcor+1;
        if (index >= MAaudio.length) return null;
        AudioType audioType = MAaudio[MAControl[index]];
        if (audioType != null) {
            //textViewTest.setBackgroundColor(audioType.getColor());
            audioType.setStartTimeMillisNow();
            return audioType;
        }
        return null;
    }

    public AudioType getCurrentCorrectAnswer(int buttonNumber) {
        AudioType audioType = MAaudio[buttonNumber];
        if (MACorreccion[MAcountcor] == MAControl[MAcountcor]) {
            return audioType;
        }
        return null;
    }

    public void onTestFinished() {
        Intent results = new Intent(this, Memauditivares.class);
        int MACorrect = 0, MAIncorrect = 0;
        for (int i = 0; i < MAControl.length; i++) {
            if (MACorreccion[i] == MAControl[i]) {
                MACorrect++;
            } else {
                MAIncorrect++;
            }
        }
        results.putExtra("Correctas", MACorrect);
        results.putExtra("Incorrectas", MAIncorrect);
        List<String> stringList = new LinkedList<>();
        for (AudioType audioType : MAaudio){
            stringList.add(audioType.toString());
        }

        results.putExtra("Options", stringList.toArray(new String [0]));
        startActivity(results);
        finish();
    }


}