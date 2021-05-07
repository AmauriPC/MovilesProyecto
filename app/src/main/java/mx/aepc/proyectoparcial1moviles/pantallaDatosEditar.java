package mx.aepc.proyectoparcial1moviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class pantallaDatosEditar extends AppCompatActivity {
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos_editar);
        userid=getIntent().getStringExtra("userid");

        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeCB);
        CheckBox cbGames=(CheckBox)findViewById(R.id.videojuegosCB);
        CheckBox cbLiterature=(CheckBox)findViewById(R.id.literaturaCB);
        CheckBox cbSports=(CheckBox)findViewById(R.id.deportesCB);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineCB);
        CheckBox cbMusic=(CheckBox)findViewById(R.id.musicaCB);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesCB);
        CheckBox cbArt=(CheckBox)findViewById(R.id.arteCB);
        CheckBox cbAstrology=(CheckBox)findViewById(R.id.astrologiaCB);
        /*if(isStoragePermissionGranted())
        {
            Log.println(Log.ASSERT,"OK","Permisos concedidos de la camara papu");
        }*/

    }

    /*public boolean isStoragePermissionGranted() {
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
            Log.println(Log.ASSERT,"OK","Permisos obtenidos de la camara makinola");
        }
        else
        {
            Log.println(Log.ASSERT,"NOK","No pos fue GG ya ni que hacerle con la camara campeon");
        }
    }*/

    /*static final int REQUEST_IMAGE_CAPTURE=1;

    public void tomarPic(View view)
    {
        try
        {
            int REQUEST_TAKE_PHOTO = 1;
            Intent takePictureIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager()) != null)
            {
                startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
            }
        }catch (Exception e)
        {
            Log.println(Log.ASSERT, "error", "ni pex papu error en camara");
        }
    }*/

    public void editarDatos()
    {
        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeCB);
        CheckBox cbGames=(CheckBox)findViewById(R.id.videojuegosCB);
        CheckBox cbLiterature=(CheckBox)findViewById(R.id.literaturaCB);
        CheckBox cbSports=(CheckBox)findViewById(R.id.deportesCB);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineCB);
        CheckBox cbMusic=(CheckBox)findViewById(R.id.musicaCB);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesCB);
        CheckBox cbArt=(CheckBox)findViewById(R.id.arteCB);
        CheckBox cbAstrology=(CheckBox)findViewById(R.id.astrologiaCB);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        if(cbAnime.isChecked()){
            db.collection("users").document(userid).update("Anime", "True");
        }else{
            db.collection("users").document(userid).update("Anime", "False");
        }

        if(cbGames.isChecked()){
            db.collection("users").document(userid).update("Videojuegos", "True");
        }else{
            db.collection("users").document(userid).update("Videojuegos", "False");
        }

        if(cbLiterature.isChecked()){
            db.collection("users").document(userid).update("Literatura", "True");
        }else{
            db.collection("users").document(userid).update("Literatura", "False");
        }

        if(cbSports.isChecked()){
            db.collection("users").document(userid).update("Deportes", "True");
        }else{
            db.collection("users").document(userid).update("Deportes", "False");
        }

        if(cbCine.isChecked()){
            db.collection("users").document(userid).update("Cine", "True");
        }else{
            db.collection("users").document(userid).update("Cine", "False");
        }

        if(cbMusic.isChecked()){
            db.collection("users").document(userid).update("Musica", "True");
        }else{
            db.collection("users").document(userid).update("Musica", "False");
        }

        if(cbSeries.isChecked()){
            db.collection("users").document(userid).update("Series", "True");
        }else{
            db.collection("users").document(userid).update("Series", "False");
        }

        if(cbArt.isChecked()){
            db.collection("users").document(userid).update("Arte", "True");
        }else{
            db.collection("users").document(userid).update("Arte", "False");
        }

        if(cbAstrology.isChecked()){
            db.collection("users").document(userid).update("Astrologia", "True");
        }else{
            db.collection("users").document(userid).update("Astrologia", "False");
        }
    }


    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid",userid);
        startActivity(intent);
        editarDatos();
    }
}