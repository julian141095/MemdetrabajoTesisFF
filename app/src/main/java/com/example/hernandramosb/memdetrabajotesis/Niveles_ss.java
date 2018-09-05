package com.example.hernandramosb.memdetrabajotesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hernandramosb.memdetrabajotesis.spandesimbolos.SpanDeSimbolos;

public class Niveles_ss extends AppCompatActivity implements View.OnClickListener {

    Button nivel1ss, nivel2ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_ss);

        nivel1ss = (Button) findViewById(R.id.lvl1ss);
        nivel2ss = (Button) findViewById(R.id.lvl2ss);

        nivel1ss.setOnClickListener(this);
        nivel2ss.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lvl1ss:

                Intent Memaudiact = new Intent(getApplicationContext(), SpanDeSimbolos1.class);
                startActivity(Memaudiact);

                break;

            case R.id.lvl2ss:

                Intent Spansimact = new Intent(getApplicationContext(), SpanDeSimbolos.class);
                startActivity(Spansimact);
                break;

        }

    }

}

