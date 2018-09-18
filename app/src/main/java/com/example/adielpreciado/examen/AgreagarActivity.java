package com.example.adielpreciado.examen;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import OpenHelper.SQLite_OpenHelper;

public class AgreagarActivity extends AppCompatActivity{
    private EditText usuario,password;
    private Button btnagregar;
    public void onCreate (Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_agregar);
        usuario =(EditText)findViewById(R.id.edtaguser);
        password=(EditText)findViewById(R.id.edtagpas3);
        btnagregar =(Button)findViewById(R.id.btnagr);
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
    public void agregar (View v){
        if (ComprobarCampos()) {
            String user,pass;
            user= usuario.getText().toString();
            pass=password.getText().toString();
            SQLite_OpenHelper bh = new SQLite_OpenHelper(AgreagarActivity.this,"AGREGAR",null,1);
            if(bh!=null){
                SQLiteDatabase db = bh.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre",user);
                con.put("password",pass);
                long insertado = db.insert("AGREGAR",null,con);
                if(insertado>0) {
                    Toast.makeText(AgreagarActivity.this, "Insertado exitoso", Toast.LENGTH_SHORT).show();
                  usuario.setText("");
                  password.setText("");
                }else{
                    Toast.makeText(AgreagarActivity.this, "No hay Insertado exitoso", Toast.LENGTH_SHORT).show();
                }

            }
           // Toast.makeText(AgreagarActivity.this, "No hay campos vacios", Toast.LENGTH_SHORT).show();
       // }else{
           // Toast.makeText(AgreagarActivity.this, "hay campos vacios", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean ComprobarCampos(){
        if(usuario.getText().toString().isEmpty()|| password.getText().toString().isEmpty()) {
            return false;
        }else{
            return true;

        }
    }
}
