package com.example.hernandramosb.memdetrabajotesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hernandramosb.memdetrabajotesis.classes.AudioType;

import java.util.LinkedList;
import java.util.List;

public class Spandesimres extends AppCompatActivity {

    AudioType[] audioOptions;

    TextView textView, textView1, textViewTime1, textViewTime2, textViewTime3, textViewTime4, textViewTime5, textViewTime6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spandesimres);
        textView = (TextView) findViewById(R.id.SSTextViewCorrectas);
        textView1 = (TextView) findViewById(R.id.SSTextViewIncorrectas);
        //textViewTime1 = (TextView) findViewById(R.id.MATextViewTiempo1);
        //textViewTime2 = (TextView) findViewById(R.id.MATextViewTiempo2);
        //textViewTime3 = (TextView) findViewById(R.id.MATextViewTiempo3);
        //textViewTime4 = (TextView) findViewById(R.id.MATextViewTiempo4);
        //textViewTime5 = (TextView) findViewById(R.id.MATextViewTiempo5);
        //textViewTime6 = (TextView) findViewById(R.id.MATextViewTiempo6);
        Bundle bundle = getIntent().getExtras();
        int correct = 0, incorrect = 0;
        List<AudioType> audioTypeList = new LinkedList<>();
        if (bundle != null) {
            correct = bundle.getInt("Correctas");
            incorrect = bundle.getInt("Incorrectas");
        }
        textView.setText("Correctas: " + String.valueOf(correct));
        textView1.setText("Incorrectas: " + String.valueOf(incorrect));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Inicio.class));
    }

}