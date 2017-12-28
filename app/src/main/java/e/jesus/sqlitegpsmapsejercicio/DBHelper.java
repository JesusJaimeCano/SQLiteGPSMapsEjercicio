package e.jesus.sqlitegpsmapsejercicio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jesus on 21/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "metro";
    public static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String linea9 = "CREATE TABLE lineanueve(_id INTEGER PRIMARY KEY AUTOINCREMENT, estacion TEXT, latitud REAL, longitud REAL)";
        db.execSQL(linea9);

        ContentValues cv = new ContentValues();
        cv.put("estacion", "Tacubaya");
        cv.put("latitud", 19.4024);
        cv.put("longitud", -99.1883);
        db.insert("lineanueve", "nombre", cv);

        cv.put("estacion", "Patriotismo");
        cv.put("latitud", 19.405798);
        cv.put("longitud", -99.1790046);
        db.insert("lineanueve", "nombre", cv);

        cv.put("estacion", "Chilpancingo");
        cv.put("latitud", 19.4059898);
        cv.put("longitud", -99.1703915);
        db.insert("lineanueve", "nombre", cv);

        cv.put("estacion", "Centro Medico");
        cv.put("latitud", 19.4066625);
        cv.put("longitud", -99.1549425);
        db.insert("lineanueve", "nombre", cv);

        cv.put("estacion", "Lazaro Cardenas");
        cv.put("latitud", 19.407021);
        cv.put("longitud", -99.1443372);
        db.insert("lineanueve", "nombre", cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lineanueve");
        onCreate(db);
    }
}
