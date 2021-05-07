package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            Log.println(Log.ASSERT,"OK","Permisos concedidos");
        }*/

    }


    /*
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

    static final int REQUEST_IMAGE_CAPTURE=1;

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap= (Bitmap) extras.get("data");
            ImageView pic= (ImageView)findViewById(R.id.fotoPersona);
            pic.setImageBitmap(imgBitmap);


            String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String name = "IMG_"+dateTime+"_";
            saveMe(name, imgBitmap);


        }
    }

    private void saveMe(String nombre, Bitmap img) {
        try{
            //final String relativePath = Enviroment.DIRECTORY_PICTURES + File.separator + "Your directory";
            String fileName = nombre;
            //String mimType = "image/'": // Mine Types define here

            Bitmap bitmap = img;

            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            //contentValues.put(MediaStore.MediaColumns.TITLE, "");

            ContentResolver resolver = getApplicationContext().getContentResolver();

            OutputStream stream = null;
            Uri uri = null;

            try{
                final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                uri = resolver.insert(contentUri, contentValues);

                stream = resolver.openOutputStream(uri);

                boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);

            }catch (IOException e){
                if (uri != null){
                    resolver.delete(uri, null, null);
                }

            }finally {
                if(stream != null){
                    try{
                        stream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }

        }catch(Exception e){
            Log.println(Log.ASSERT,"ERROR en cursor", e.toString());
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