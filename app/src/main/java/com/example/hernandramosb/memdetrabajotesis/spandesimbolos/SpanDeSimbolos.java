package com.example.hernandramosb.memdetrabajotesis.spandesimbolos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.hernandramosb.memdetrabajotesis.Memauditivares;
import com.example.hernandramosb.memdetrabajotesis.R;
import com.example.hernandramosb.memdetrabajotesis.Spandesimres;
import com.example.hernandramosb.memdetrabajotesis.classes.AudioType;
import com.example.hernandramosb.memdetrabajotesis.utils.AndroidUtils;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SpanDeSimbolos extends AppCompatActivity {
    private ImageView referenceImageView;
    private ImageButton btnOption1, btnOption2, btnOption3, btnOption4, btnOption5;
    private ImageButton[] buttons;
    private SSOption[] imagesArray;
    Timer timerAsync = new Timer();
    private int counter = 0, userSelection = 1,
    correct = 0, incorrect = 0;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage(" ¿Estás seguro que deseas salir de Span de Simbolos?");
        myBuild.setTitle("Mensaje");
        myBuild.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_de_simbolos2);
        referenceImageView = (ImageView) findViewById(R.id.mainImagen);
        btnOption1 = (ImageButton) findViewById(R.id.opcion1);
        btnOption2 = (ImageButton) findViewById(R.id.opcion2);
        btnOption3 = (ImageButton) findViewById(R.id.opcion3);
        btnOption4 = (ImageButton) findViewById(R.id.opcion4);
        btnOption5 = (ImageButton) findViewById(R.id.opcion5);
        buttons = new ImageButton[]{btnOption1, btnOption2, btnOption3, btnOption4, btnOption5};
        imagesArray = new SSOption[]{
                new SSOption(R.drawable.baseline_spellcheck_black_18dp, btnOption1),
                new SSOption(R.drawable.baseline_cached_black_18dp2, btnOption2),
                new SSOption(R.drawable.baseline_check_circle_outline_black_18dp, btnOption3),
                new SSOption(R.drawable.baseline_check_circle_black_18dp, btnOption4),
                new SSOption(R.drawable.baseline_schedule_black_18dp, btnOption5)};
    }

    private void start() {
        TimerTask timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(internalTaskShowOpts);
            }
        };
        timerAsync.schedule(timerTaskAsync, 0, 3000);
    }

    private void cambiarImagen() {
        referenceImageView.setImageResource(getRandomImage().getImageId());//ArregloImagenes[posicion]);
        if (counter >= imagesArray.length) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    referenceImageView.setVisibility(View.INVISIBLE);
                    showButtons();
                    timerAsync.cancel();
                }
            }, 1000 * 2);
        }
    }

    public SSOption getRandomImage() {
        Random numeros = new Random();
        SSOption option = null;
        do {
            option = imagesArray[numeros.nextInt(imagesArray.length)];
            if (option.wasShowed()) {
                option = null;
            } else {
                option.setShowed(true);
                option.setPosition(counter + 1);
                counter++;
            }
        } while (option == null || counter > imagesArray.length);
        return option;
    }

    public void hideButtons() {
        for (ImageButton button : buttons) button.setVisibility(View.INVISIBLE);
    }

    public void showButtons() {
        for (ImageButton button : buttons) button.setVisibility(View.VISIBLE);
    }

    public SSOption getOptionByPosition(ImageButton button) {
        for (SSOption option : imagesArray) {
            if (option.getButton().getId() == button.getId()) {
                return option;
            }
        }
        return null;
    }

    public void onStartClickSpan(View view) {
        start();
        view.setEnabled(false);
    }

    public void onSSOptionClick(View view) {
        SSOption option = getOptionByPosition((ImageButton) view);
        referenceImageView.setImageResource(option.getImageId());
        if (option != null) {
            if (option.getPosition() == userSelection) {
                AndroidUtils.sendToast(this, "Respuesta Correcta");
                correct++;
                option.getButton().setEnabled(false);
                option.getButton().setBackgroundColor(Color.GREEN);
            } else {
                AndroidUtils.sendToast(this, "Respuesta Incorrecta");
                incorrect++;
            }
            if (userSelection >= imagesArray.length) {
                finishGame();
            }
            userSelection++;
        }
    }

    private Runnable internalTaskShowOpts = new Runnable() {
        @Override
        public void run() {
            cambiarImagen();
        }
    };

    private void finishGame() {
        for (SSOption ssOption : imagesArray) {
            ssOption.getButton().setEnabled(false);
        }
        Intent results = new Intent(this, Spandesimres.class); //Cambiar Memauditivares.class por la clase que creen
        results.putExtra("Correctas", correct);
        results.putExtra("Incorrectas", incorrect);
        startActivity(results);
        AndroidUtils.sendToast(this, "Fin del juego!");
    }

    /**
     * Task que muestra las imagenes guia
     */
    private Runnable showGuideTask = new Runnable() {
        @Override
        public void run() {
        }
    };

}
