package com.example.adielpreciado.examen;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.SQLite_OpenHelper;
import OpenHelper.Usuarios;

public class MostrarActivity extends AppCompatActivity {
    private ArrayList<Usuarios> usuarios = new ArrayList<>();
    private ListView lista;
    private int usuarioselecionado = -1;
    private Object nActionModel;
    public void onCreate(Bundle b){
        super.onCreate(b);
    setContentView(R.layout.activity_mostrar);
    lista =(ListView)findViewById(R.id.lvusuarios);
    llamarlista();
    onClick();
    }
    public void onResume() {
        super.onResume();
        usuarios.removeAll(usuarios);
        llamarlista();
    }
    public void onClick (){
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioselecionado = position;
                nActionModel= MostrarActivity.this.startActionMode(amc);
                view.setSelected(true);
                return true;
            }
        });
    }
    private ActionMode.Callback amc = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
           getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
           if (item.getItemId()== R.id.EliminarItem){
               eliminarUsuario();
               mode.finish();
           }else if (item.getItemId()== R.id.EditarItem){
               Usuarios usu = usuarios.get(usuarioselecionado);
               Intent in = new Intent(MostrarActivity.this,EditarActivity.class);
               in.putExtra("id",usu.getIdusuario());
               startActivity(in);
               mode.finish();
           }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };
    public void llamarlista(){
        SQLite_OpenHelper bh = new SQLite_OpenHelper(MostrarActivity.this,"AGREGAR",null,1);
        if (bh!= null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM AGREGAR",null);
            if (c.moveToFirst()) {
                do{
                    usuarios.add(new Usuarios(c.getInt(0),c.getString(1),c.getString(2)));
                }while(c.moveToNext());{

                }
            }
            String [] arreglo = new String [usuarios.size()];
            for (int i =0;i<arreglo.length;i++){
                arreglo[i]= usuarios.get(i).getUsuario();
            }
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(MostrarActivity.this,android.R.layout.simple_list_item_1,arreglo);
            lista.setAdapter(adapter);
        }
    }
    public void eliminarUsuario(){
        SQLite_OpenHelper bh = new SQLite_OpenHelper(MostrarActivity.this, "AGREGAR", null, 1);
        if (bh!= null) {
            SQLiteDatabase db = bh.getReadableDatabase();
            Usuarios usu = usuarios.get(usuarioselecionado);
            long response = db.delete("AGREGAR", "idusuario=" + usu.getIdusuario(), null);
            Toast.makeText(MostrarActivity.this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
            usuarios.remove(usuarios);
            llamarlista();
        }else{
            Toast.makeText(MostrarActivity.this,"Fallo",Toast.LENGTH_SHORT).show();
        }
    }
}
