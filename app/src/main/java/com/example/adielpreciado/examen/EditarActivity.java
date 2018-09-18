package com.example.adielpreciado.examen;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class EditarActivity extends AppCompatActivity {
    private int usuarioEditar;
    private EditText nombre,password;
    private Button btneditar;

    public void onCreate (Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_editar);
        btneditar = (Button)findViewById(R.id.btneditar);
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MostrarActivity.class);
                startActivity(intent);
            }
        });
        Bundle extras = this.getIntent().getExtras();
        if(extras!=null){
            usuarioEditar = extras.getInt("id");
        }
        nombre = (EditText)findViewById(R.id.edteditaruser);
        password = (EditText)findViewById(R.id.edteditarpass);

        reflejarCampos();
    }
    public void reflejarCampos(){
        SQLite_OpenHelper bh = new SQLite_OpenHelper(EditarActivity.this,"AGREGAR",null,1);
        if (bh!=null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM AGREGAR Where idsusuarios="+usuarioEditar,null);
            try{
                if (c.moveToNext()){
                    nombre.setText(c.getString(1));
                    password.setText(c.getString(2));
                }
            }finally{
            }
        }
    }
    public void editar(View v){
        SQLite_OpenHelper bh = new SQLite_OpenHelper(EditarActivity.this, "AGREGAR", null, 1);
        if (bh!= null) {
            SQLiteDatabase db = bh.getReadableDatabase();
            ContentValues val= new ContentValues();
            val.put("nombre",nombre.getText().toString());
            long response = db.update("AGREGAR",val,"idusuario="+usuarioEditar,null);
            Toast.makeText(EditarActivity.this,"Editado exitoso",Toast.LENGTH_SHORT).show();
            nombre.setText("");
            password.setText("");

        }else{
            Toast.makeText(EditarActivity.this,"ERROR",Toast.LENGTH_SHORT).toString();
        }
    }
}
