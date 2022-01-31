package com.example.calculadoraiphone;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class easterEggController extends AppCompatActivity{

    private Button btnBack;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easteregg);

        // DESACTIVAMOS LA ROTACIÓN
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // BOTON PARA VOLVER A LA CALCULADORA
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CambiarVentana(this);

            }
        });

        // INICIA EL SONIDO DE LA MÚSICA
        music = MediaPlayer.create(this,R.raw.shootingstars);
        music.start();

    }

    public void  CambiarVentana (View.OnClickListener view){

        music.stop();

        /*Creamos una instancia de tipo Intent, los parámetros son el contexto actual, ke en este caso es This, y enseguida el nombre de la ventana a la que iremos Ventana2MainActivity */

        Intent CambiarVentana = new Intent (this, MainActivity.class);

        /*Iniciamos el metodo con la opción StartActivity*/

        startActivity(CambiarVentana);

        finish();

    }

}
