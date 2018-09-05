package com.example.hernandramosb.memdetrabajotesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hernandramosb.memdetrabajotesis.paresdepalabras.ParesDePalabras;
import com.example.hernandramosb.memdetrabajotesis.spandesimbolos.SpanDeSimbolos;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button memaudib,spansimb,pardepalab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        memaudib=(Button)findViewById(R.id.memaudi);
        spansimb=(Button)findViewById(R.id.spansim);
        pardepalab=(Button)findViewById(R.id.pardepala);

        memaudib.setOnClickListener(this);
        spansimb.setOnClickListener(this);
        pardepalab.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.memaudi:

                Intent Memaudiact = new Intent(getApplicationContext(), Niveles_ma.class);
                startActivity(Memaudiact);

                break;

            case R.id.spansim:

                Intent Spansimact = new Intent(getApplicationContext(), Niveles_ss.class);
                startActivity(Spansimact);
                break;

            case R.id.pardepala:

                Intent Pardepalaact = new Intent(getApplicationContext(), Niveles_pp.class);
                startActivity(Pardepalaact);
                break;
        }

    }
}
