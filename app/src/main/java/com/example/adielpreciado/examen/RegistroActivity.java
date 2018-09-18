package com.example.adielpreciado.examen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class RegistroActivity extends Activity {

Button btngrabaruser;
EditText txtCorr,txtpass;

SQLite_OpenHelper helper=new  SQLite_OpenHelper(this, "BD1",null ,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
       btngrabaruser =(Button) findViewById(R.id.btnreg2);

       txtCorr =(EditText)findViewById(R.id.edtGma);

       txtpass =(EditText)findViewById(R.id.edtpass2);

       btngrabaruser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               helper.abrir();
               helper.insertarRegi(String.valueOf(txtCorr.getText()),
                               String.valueOf(txtpass.getText()));
               helper.cerrar();

               Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();

               Intent intent= new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
           }
       });


    }

}
