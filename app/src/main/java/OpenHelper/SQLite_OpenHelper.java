package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_OpenHelper extends SQLiteOpenHelper{
    public String AGREGAR = "CREATE TABLE agregar(idusuarios INTEGER PRIMARY KEY AUTOINCREMENT,nombre text,password text)";
    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AGREGAR);
        String query ="create table usuarios(_ID integer primary key autoincrement,Nombre text,Password text);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//Metodo para abrir la bd
public void abrir(){
    this.getWritableDatabase();
}

//Metodo para cerrar la bd
public void cerrar(){
    this.close();
    }
    //Metodo para insertar registro bd
    public void insertarRegi(String cor,String pass){
        ContentValues valores = new ContentValues();
        valores.put("Correo",cor);
        valores.put("Password",pass);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }
    //Metodo que permite validar si el usaurio existe
    public Cursor consultarUsPas(String cor, String pass)throws SQLException{
        Cursor mcursor=null;
        mcursor = this.getWritableDatabase().query("usuarios", new String[]{"_ID","Correo","Password"},
                "Correo like'"+cor+"' and Password like'"+pass+"'",null,null,null,null);
        return mcursor;
        
    }
}
