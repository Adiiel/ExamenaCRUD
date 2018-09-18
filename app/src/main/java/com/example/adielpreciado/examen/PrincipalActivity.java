package com.example.adielpreciado.examen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class PrincipalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
public void agregar(View v){
        //primero es la actidad en la que estoy (intent cambiar la actividad)
    Intent agregaractivity = new Intent(PrincipalActivity.this,AgreagarActivity.class);
    startActivity(agregaractivity);
}
public  void mostrar(View v){
        Intent MostrarActivity = new Intent(PrincipalActivity.this,MostrarActivity.class);
        startActivity(MostrarActivity);
}
}
