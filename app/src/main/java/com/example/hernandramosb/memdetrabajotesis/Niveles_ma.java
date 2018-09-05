package com.example.hernandramosb.memdetrabajotesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hernandramosb.memdetrabajotesis.spandesimbolos.SpanDeSimbolos;

public class Niveles_ma extends AppCompatActivity implements View.OnClickListener {

    Button nivel1, nivel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_ma);

        nivel1 = (Button) findViewById(R.id.lvl1);
        nivel2 = (Button) findViewById(R.id.lvl2);

        nivel1.setOnClickListener(this);
        nivel2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lvl1:

                Intent Lvl1 = new Intent(getApplicationContext(), MemAuditiva1.class);
                startActivity(Lvl1);

                break;

            case R.id.lvl2:

                Intent Lvl2 = new Intent(getApplicationContext(), Memauditiva.class);
                startActivity(Lvl2);
                break;

        }

    }

}
