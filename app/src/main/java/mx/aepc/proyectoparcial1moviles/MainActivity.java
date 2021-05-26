package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    TextView cont;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont=(TextView)findViewById(R.id.textContraseña);
        username=(TextView)findViewById(R.id.textEmail);

        if(isStoragePermissionGranted())
        {
            Log.println(Log.ASSERT,"OK","Permisos concedidos");
        }

    }


    public boolean isStoragePermissionGranted() {
        final int STORAGE_PERMISSION = 100;
        int ACCESS_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ((ACCESS_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final int STORAGE_PERMISSION = 100;
        if (requestCode == STORAGE_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.println(Log.ASSERT,"OK","Permisos obtenidos");
        }
        else
        {
            Log.println(Log.ASSERT,"NOK","No pos fue GG ya ni que hacerle");
        }
    }


    public void getActivityDatos(View view)
    {
        Intent intent = new Intent(this, pantallaDatosRegistro.class);
        startActivity(intent);
    }

    public void getActivityIntereses(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");
        users.whereEqualTo("Contraseña", cont.getText().toString()).get().addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        Log.i("test","testeando");
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                if(document.getId().equals(username.getText().toString())) {
                    Intent intent = new Intent(this, pantallaIntereses.class);
                    intent.putExtra("userid", username.getText().toString());
                    startActivity(intent);
                }
            }
        } else {
            Log.println(Log.ASSERT,"Nel","Contraseña o usuario incorrecto");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getBaseContext(), "Usuario o contraseña incorrectos", duration);
            toast.show();

            //Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        //Log.println(Log.ASSERT,"prueba","prueba"+task.isSuccessful());
    }
}