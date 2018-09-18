package com.example.adielpreciado.examen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {
TextView tvtRegistr;
Button btningresar;
SQLite_OpenHelper helper = new SQLite_OpenHelper(this ,"BD1",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
tvtRegistr=(TextView)findViewById(R.id.txtr);
btningresar =(Button)findViewById(R.id.btningresar);
tvtRegistr.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(getApplicationContext(),RegistroActivity.class);
        startActivity(intent);
    }
});
btningresar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        EditText txtUsuario =(EditText)findViewById(R.id.edtusuario1);
        EditText txtPassword =(EditText)findViewById(R.id.edtpass);

        Cursor cursor =helper.consultarUsPas(txtUsuario.getText().toString(),txtPassword.getText().toString());
        if(cursor.getCount()>0){
            Intent intent= new Intent(getApplicationContext(),PrincipalActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Usuario incorrecto o Password incorrecto",
                    Toast.LENGTH_SHORT).show();
            txtUsuario.setText("");
            txtPassword.setText("");
            txtUsuario.findFocus();
        }
    }
});
    }
}
