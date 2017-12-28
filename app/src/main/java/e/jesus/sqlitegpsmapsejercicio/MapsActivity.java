package e.jesus.sqlitegpsmapsejercicio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ListView listView;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ListAdapter adapter;
    boolean mapaListo = false;
    double latitud;
    double longitud;
    double latitud2;
    double longitud2;
    double latitud3;
    double longitud3;
    double latitud4;
    double longitud4;
    double latitud5;
    double longitud5;
    ArrayList<Double> latitudes;
    ArrayList<Double> longitudes;
    ArrayList<String> estacionesLineaNueve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listView = findViewById(R.id.lista);
        latitudes = new ArrayList<>();
        longitudes = new ArrayList<>();
        estacionesLineaNueve = new ArrayList<>();

        db = new DBHelper(this).getWritableDatabase();
        cursor = db.rawQuery("SELECT _id, estacion, latitud, longitud FROM lineanueve", null);

        adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, new  String[]{"estacion", "latitud", "longitud"}, new int[]{R.id.estacion, R.id.latitud, R.id.longitud}, 0);
        listView.setAdapter(adapter);


    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.40961826, -99.16942731);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Na-at Technologies"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.40961826, -99.16942731),16.0f));

        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(MapsActivity.this, R.raw.style_maps)
        );

        mapaListo = true;


        ponerLinea();
    }

    public void ponerLinea() {

        estacionesLineaNueve.add("Tacubaya");
        estacionesLineaNueve.add("Patriotismo");
        estacionesLineaNueve.add("Chilpancingo");
        estacionesLineaNueve.add("Centro Medico");
        estacionesLineaNueve.add("Lazaro Cardenas");

        cursor = db.rawQuery("SELECT _id, latitud from lineanueve WHERE estacion like 'Tacubaya'", null);
        if (cursor.moveToFirst()){latitud = cursor.getDouble( cursor.getColumnIndex("latitud"));}
        cursor = db.rawQuery("SELECT _id, longitud from lineanueve WHERE estacion like 'Tacubaya'", null);
        if (cursor.moveToFirst()){longitud = cursor.getDouble( cursor.getColumnIndex("longitud"));}
        cursor = db.rawQuery("SELECT _id, latitud from lineanueve WHERE estacion like 'Patriotismo'", null);
        if (cursor.moveToFirst()){latitud2 = cursor.getDouble( cursor.getColumnIndex("latitud"));}
        cursor = db.rawQuery("SELECT _id, longitud from lineanueve WHERE estacion like 'Patriotismo'", null);
        if (cursor.moveToFirst()){longitud2 = cursor.getDouble( cursor.getColumnIndex("longitud"));}
        cursor = db.rawQuery("SELECT _id, latitud from lineanueve WHERE estacion like 'Chilpancingo'", null);
        if (cursor.moveToFirst()){latitud3 = cursor.getDouble( cursor.getColumnIndex("latitud"));}
        cursor = db.rawQuery("SELECT _id, longitud from lineanueve WHERE estacion like 'Chilpancingo'", null);
        if (cursor.moveToFirst()){longitud3 = cursor.getDouble( cursor.getColumnIndex("longitud"));}
        cursor = db.rawQuery("SELECT _id, latitud from lineanueve WHERE estacion like 'Centro Medico'", null);
        if (cursor.moveToFirst()){latitud4 = cursor.getDouble( cursor.getColumnIndex("latitud"));}
        cursor = db.rawQuery("SELECT _id, longitud from lineanueve WHERE estacion like 'Centro Medico'", null);
        if (cursor.moveToFirst()){longitud4 = cursor.getDouble( cursor.getColumnIndex("longitud"));}
        cursor = db.rawQuery("SELECT _id, latitud from lineanueve WHERE estacion like 'Lazaro Cardenas'", null);
        if (cursor.moveToFirst()){latitud5 = cursor.getDouble( cursor.getColumnIndex("latitud"));}
        cursor = db.rawQuery("SELECT _id, longitud from lineanueve WHERE estacion like 'Lazaro Cardenas'", null);
        if (cursor.moveToFirst()){longitud5 = cursor.getDouble( cursor.getColumnIndex("longitud"));}

        if (mapaListo) {
            PolylineOptions options = new PolylineOptions()
                    .add(new LatLng( latitud, longitud))
                    .add(new LatLng(latitud2, longitud2))
                    .add(new LatLng(latitud3, longitud3))
                    .add(new LatLng(latitud4, longitud4))
                    .add(new LatLng(latitud5, longitud5))
                    .width(10)
                    .color(Color.GREEN);
            Polyline polyline = mMap.addPolyline(options);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.422447, -99.1692277), 14.0f));


        }
    }
}
