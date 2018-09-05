package com.example.hernandramosb.memdetrabajotesis.paresdepalabras;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hernandramosb.memdetrabajotesis.Memauditivares;
import com.example.hernandramosb.memdetrabajotesis.Paresdepalres;
import com.example.hernandramosb.memdetrabajotesis.R;
import com.example.hernandramosb.memdetrabajotesis.classes.AudioType;
import com.example.hernandramosb.memdetrabajotesis.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class ParesDePalabras extends Activity  {
    Timer timerAsync = new Timer();
    PPOption[] ppOptions;
    TextView firstWord, secondWord, guideWord;
    List<UUID> usedOptions = new ArrayList<>();
    Button btnComenzar;
    Button instrucciones;
    private int correct = 0, incorrect = 0;







    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage(" ¿Estás seguro que deseas salir de Pares de Palabras?");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pares_de_palabras);





        Button button1 = (Button) findViewById(R.id.PPBt1), button2 = (Button) findViewById(R.id.PPBt2),
                button3 = (Button) findViewById(R.id.PPBt3), button4 = (Button) findViewById(R.id.PPBt4),
                button5 = (Button) findViewById(R.id.PPBt5), button6 = (Button) findViewById(R.id.PPBt6),
                button7 = (Button) findViewById(R.id.PPBt7), button8 = (Button) findViewById(R.id.PPBt8),
                button9 = (Button) findViewById(R.id.PPBt9), button10 = (Button) findViewById(R.id.PPBt10);
        btnComenzar = (Button) findViewById(R.id.PPComenzar);
        firstWord = (TextView) findViewById(R.id.PPPalabra1);
        secondWord = (TextView) findViewById(R.id.PPPalabra2);
        guideWord = (TextView) findViewById(R.id.PPPalabGuia);
        ppOptions = new PPOption[] {
                new PPOption(R.raw.pppal1, R.raw.pppal2, "Cuadro", "Risa", button3),
                new PPOption(R.raw.pppal3, R.raw.pppal4, "Mesa", "Silla", button1),
                new PPOption(R.raw.pppal5, R.raw.pppal6, "Leche", "Vaca", button4),
                new PPOption(R.raw.pppal7, R.raw.pppal8, "Bosque", "Árbol", button5),
                new PPOption(R.raw.pppal9, R.raw.pppal11, "Ruta", "Gafas", button6),
                new PPOption(R.raw.pppal10, R.raw.pppal12, "Carro", "Pelo", button2),
                new PPOption(R.raw.pppal13, R.raw.pppal14, "Cama", "Nube", button10),
                new PPOption(R.raw.pppal15, R.raw.pppal16, "Tierra", "Niña", button7),
                new PPOption(R.raw.pppal17, R.raw.pppal18, "Plato", "Suerte", button9),
                new PPOption(R.raw.pppal19, R.raw.pppal20, "Puerta", "Globo", button8)
        };

        //instrucciones = (Button) findViewById(R.id.inst_pp);
        //instrucciones.setOnClickListener(new View.OnClickListener() {
            //@Override

            //public void onClick(View v){
                //AlertDialog.Builder builder = new AlertDialog.Builder(ParesDePalabras.this);

                //builder.setIcon(R.mipmap.ic_launcher).setTitle("Instrucciones").setMessage("Aca van las instrucciones").
                        //setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            //@Override
                            //public void onClick(DialogInterface dialogInterface, int which) {
                                //dialogInterface.dismiss();

                            //}
                        //});

                //AlertDialog alertDialog=builder.create();
                //alertDialog.show();

            //}
        //});

    }








    public boolean isOptionUsed(UUID uniqueId) {
        return usedOptions.contains(uniqueId);
    }

    public void onClick(View view) {
        btnComenzar.setEnabled(false);
        startTasks();
    }

    void startTasks() {
        TimerTask timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(internalTaskShowOpts);
            }
        };
        //AQUIU SE DECLARA EL TASK CON PERIODO PARA 3s
        timerAsync.schedule(timerTaskAsync, 0, 3000);
    }

    private Runnable internalTaskShowOpts = new Runnable() {
        int index = 0;
        AudioType type;
        @Override
        public void run() {
            if (index >= ppOptions.length && index == ppOptions.length) {
                runGuideTask();
            } else {
                PPOption ppOption = ppOptions[index];
                ppOption.playAllWithPlaceholders(getApplicationContext(), firstWord, secondWord);
                index++;
            }
        }
    };

    private void runGuideTask() {
        TimerTask timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(showGuideTask);
            }
        };
        timerAsync.schedule(timerTaskAsync, 500);
    }

    private Runnable showGuideTask = new Runnable() {
        @Override
        public void run() {
            for (PPOption ppOption : ppOptions) {
                ppOption.getButtonAnswer().setEnabled(true);
            }
            guideWord.setText(selectNewWord());
            firstWord.setText("");
            secondWord.setText("");
            timerAsync.cancel();
        }
    };

    public void onButtonClickParesDePalabras(final View view)  {
        PPOption ppOption = null;
        Button button = (Button) view;
        String correctAsString = guideWord.getText().toString();
        for(PPOption ppOption1 : ppOptions) {
            if(ppOption1.getTextSound2().equalsIgnoreCase(correctAsString)) {
                ppOption = ppOption1;
            }
        }
        if(ppOption != null) {
            String answer = button.getText().toString();
            if (ppOption.isCorrectAnswer(answer)) {
                button.setEnabled(false);
                correct++;
                AndroidUtils.sendToast(this, "Respuesta Correcta ");
            } else {
                incorrect++;
                AndroidUtils.sendToast(this, "Respuesta Incorrecta");
            }
            usedOptions.add(ppOption.getUniqueId());
            String newWord = selectNewWord();
            if (newWord != null)
                guideWord.setText(newWord);
            else {
                onFinish();
            }

        } else {
            AndroidUtils.sendToast(this, "Respuesta no encontrada.");
        }
    }

    private void onFinish() {
        guideWord.setText("Fin del juego");
        Intent results = new Intent(this, Paresdepalres.class); //Cambiar Memauditivares.class por la clase que creen
        results.putExtra("Correctas", correct);
        results.putExtra("Incorrectas", incorrect);
        startActivity(results);
    }

    public String selectNewWord() {
        PPOption ppOption;
        int i = 1;
        while (true) {
            //AndroidUtils.sendToast(this, "Iteracion #" + i);
            int numero = (int) (Math.random() * ppOptions.length);
            if (numero >= 0 && ppOptions.length != usedOptions.size()) {
                ppOption = ppOptions[numero];
                if (ppOption != null && !isOptionUsed(ppOption.getUniqueId())) {
                    return ppOption.getTextSound2();
                }
                i++;
            } else {
                AndroidUtils.sendToast(this, "Opciones usadas");
                return null;
            }
        }
    }

    private void stopRepeatingTask() {
        timerAsync.cancel();
    }
    /*

    public void contar(int nmostrar) {
        if (nmostrar == 0) {
            cont0++;
        } else if (nmostrar == 1) {
            cont1++;
        } else if (nmostrar == 2) {
            cont2++;
        } else if (nmostrar == 3) {
            cont3++;
        } else if (nmostrar == 4) {
            cont4++;
        } else if (nmostrar == 5) {
            cont5++;
        } else if (nmostrar == 6) {
            cont6++;
        }
    }

    public int finish(int i) {
        boolean a = false;
        boolean b;

        while (a == false) {
            if (i == 0 && cont0 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 1 && cont1 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 2 & cont2 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 3 & cont3 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 4 & cont4 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 5 & cont5 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else if (i == 6 & cont6 == 10) {
                i = (int) (7 * Math.random());
                b = true;
            } else {
                b = false;
            }
            if (b == false) {
                a = true;
            }
        }
        return (i);
    }
    */


}
