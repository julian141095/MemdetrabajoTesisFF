package com.example.hernandramosb.memdetrabajotesis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button ingresarb, registrarb, practicarb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ingresarb = (Button) findViewById(R.id.ingresar);
        registrarb = (Button) findViewById(R.id.registrar);
        practicarb = (Button) findViewById(R.id.practicar);

        ingresarb.setOnClickListener(this);
        registrarb.setOnClickListener(this);
        practicarb.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage(" ¿Estás seguro que deseas salir de la aplicación?");
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ingresar:

                break;

            case R.id.registrar:

                Intent Registroact = new Intent(getApplicationContext(), Registro.class);
                startActivity(Registroact);
                break;

            case R.id.practicar:

                Intent Menuact = new Intent(getApplicationContext(), Menu.class);
                startActivity(Menuact);
                break;
        }

    }

    //@Override

    //public boolean onCreateOptionsMenu(Menu menu) {

    //getMenuInflater().inflate(R.menu.menu, menu);

    //return true;
}

    /*@Override

   //// public boolean OnoptionsItemSelected(MenuItem opcion_menu) {
        //int id = opcion_menu.getItemId();
        ////if (id == R.id.inst) {
            return true;
        }

        if (id== R.id.config){
            return true;
        }

        return super.onContextItemSelected(opcion_menu);

    }*/






