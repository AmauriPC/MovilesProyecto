package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Locale;

public class pantallaIntereses extends AppCompatActivity {

    String userid;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates = true;
    double latitud;
    double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_intereses);
        userid = getIntent().getStringExtra("userid");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (isGeoPermissionGranted()) {
            Log.println(Log.ASSERT, "OK", "Permisos necesarios de mapas");
            createLocationRequest();
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    latitud=location.getLatitude();
                    longitud=location.getLongitude();

                    Log.println(Log.ASSERT, "Coordenadas", "Latitudzz: " + location.getLatitude() + ", " + "Longitud: "+location.getLongitude());
                }
            }
        };



    }


    private boolean isGeoPermissionGranted() {
        final int STORAGE_PERMISSION = 100;
        int MAPS_ACCESS = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int MAPS_ACCESSFINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if ((MAPS_ACCESS != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, STORAGE_PERMISSION);
            return false;
        }
        if ((MAPS_ACCESSFINE != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, STORAGE_PERMISSION);
            return false;
        }
        return true;
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final int MAPPS_PERMISSION = 100;
        if (requestCode == MAPPS_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.println(Log.ASSERT, "OK", "Permisos obtenidos");
        } else {
            Log.println(Log.ASSERT, "NOK", "No pos fue GG ya ni que hacerle");
        }
    }





    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        ((Task) task).addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Log.println(Log.ASSERT, "OK", "Localizacion actibvada");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu_datos,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent=null;
        switch(item.getItemId()){
            case R.id.menuPrincipal:
                intent= new Intent(this, pantallaIntereses.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
                finish();

                return true;

            case R.id.miPerfil:
                intent= new Intent(this, pantallaMiPerfil.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
                finish();

                return true;
            case R.id.cerrarSesion:
                intent= new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            /*case R.id.panas:
                intent= new Intent(this, Panas.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

                return true;*/

            default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void getActivityResultados(View view)
    {
        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeICB);
        CheckBox cbGames=(CheckBox)findViewById(R.id.videojuegosICB);
        CheckBox cbLiterature=(CheckBox)findViewById(R.id.literaturaICB);
        CheckBox cbSports=(CheckBox)findViewById(R.id.deportesICB);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineICB);
        CheckBox cbMusic=(CheckBox)findViewById(R.id.musicaICB);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesICB);
        CheckBox cbArt=(CheckBox)findViewById(R.id.arteICB);
        CheckBox cbAstrology=(CheckBox)findViewById(R.id.astrologiaICB);

        Intent intent = new Intent(this, pantallaResultados.class);
        intent.putExtra("userid", userid);
        if(cbAnime.isChecked())intent.putExtra("Anime","True");
        if(cbGames.isChecked())intent.putExtra("Videojuegos","True");
        if(cbLiterature.isChecked())intent.putExtra("Literatura","True");
        if(cbSports.isChecked())intent.putExtra("Deportes","True");
        if(cbCine.isChecked())intent.putExtra("Cine","True");
        if(cbMusic.isChecked())intent.putExtra("Musica","True");
        if(cbSeries.isChecked())intent.putExtra("Series","True");
        if(cbArt.isChecked())intent.putExtra("Arte","True");
        if(cbAstrology.isChecked())intent.putExtra("Astrologia","True");
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("¿Finalizar sesión?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


}