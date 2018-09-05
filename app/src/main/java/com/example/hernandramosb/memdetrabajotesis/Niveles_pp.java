package com.example.hernandramosb.memdetrabajotesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hernandramosb.memdetrabajotesis.paresdepalabras.ParesDePalabras;

public class Niveles_pp extends AppCompatActivity implements View.OnClickListener {

    Button nivel1pp, nivel2pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_pp);

        nivel1pp = (Button) findViewById(R.id.lvl1pp);
        nivel2pp = (Button) findViewById(R.id.lvl2pp);

        nivel1pp.setOnClickListener(this);
        nivel2pp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lvl1pp:

                Intent Lvl1pp = new Intent(getApplicationContext(), ParesDePalabras1.class);
                startActivity(Lvl1pp);

                break;

            case R.id.lvl2pp:

                Intent Lvl2pp = new Intent(getApplicationContext(), ParesDePalabras.class);
                startActivity(Lvl2pp);
                break;

        }

    }

}

