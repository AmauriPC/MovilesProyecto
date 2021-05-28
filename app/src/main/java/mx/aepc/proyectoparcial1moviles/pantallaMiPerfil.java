package mx.aepc.proyectoparcial1moviles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class pantallaMiPerfil extends AppCompatActivity {

    String userid;
    String useredad, usernombre, interes,interes2,interes3,interes4,interes5,interes6,interes7,interes8,interes9;
    String text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_mi_perfil);
        userid=getIntent().getStringExtra("userid");
        getDatos();
        ImageView pic = (ImageView) findViewById(R.id.imagenPerfil);
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://platonicdb-6cdc7.appspot.com/");
        StorageReference storageRef = storage.getReference(userid+".jpg");
        try {
            final File localFile=File.createTempFile("ProfilePic",".jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                   Bitmap imgbitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    pic.setImageBitmap(imgbitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    static final int REQUEST_IMAGE_CAPTURE=1;


    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {

        }
    }



    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            data.getData();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            ImageView pic = (ImageView) findViewById(R.id.imagenPerfil);
            pic.setImageBitmap(imgBitmap);
            FirebaseStorage storage = FirebaseStorage.getInstance("gs://platonicdb-6cdc7.appspot.com/");
            StorageReference storageRef = storage.getReference(userid+".jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] databyte = baos.toByteArray();
            UploadTask uploadTask = storageRef.putBytes(databyte);

            //String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            //String name = "IMG_"+dateTime+"_";
            //saveMe(name, imgBitmap);


        }
    }


    public void getDatos(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(userid);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Edad
                        useredad = document.getString("Edad");

                        text = "Edad: " + useredad;
                        TextView textView = (TextView) findViewById(R.id.textViewEdad);
                        textView.setText(text);

                        //Nombre
                        usernombre = document.getString("Nombre");
                        textView = (TextView) findViewById(R.id.textViewNombre);
                        textView.setText(usernombre);

                        interes = document.getString("Anime");

                        if (interes.equals("True")) {
                            TextView textView2=(TextView)findViewById(R.id.textViewIntereses);
                            textView2.setText("Anime");
                        }

                        interes2 = document.getString("Videojuegos");
                        if (interes2.equals("True")) {
                            TextView textView3=(TextView)findViewById(R.id.textViewIntereses2);
                            textView3.setText("Videojuegos");
                        }

                        interes3 = document.getString("Literatura");
                        if (interes3.equals("True")) {
                            TextView textView4=(TextView)findViewById(R.id.textViewIntereses3);
                            textView4.setText("Literatura");
                        }

                        interes4= document.getString("Deportes");
                        if (interes4.equals("True")) {
                            TextView textView5=(TextView)findViewById(R.id.textViewIntereses4);
                            textView5.setText("Deportes");
                        }

                        interes5= document.getString("Cine");
                        if (interes5.equals("True")) {
                            TextView textView6=(TextView)findViewById(R.id.textViewIntereses5);
                            textView6.setText("Cine");
                        }

                        interes6= document.getString("Musica");
                        if (interes6.equals("True")) {
                            TextView textView7=(TextView)findViewById(R.id.textViewIntereses7);
                            textView7.setText("Musica");
                        }

                        interes7= document.getString("Series");
                        if (interes7.equals("True")) {
                            TextView textView8=(TextView)findViewById(R.id.textViewIntereses8);
                            textView8.setText("Series");
                        }
                        interes8= document.getString("Arte");
                        if (interes8.equals("True")) {
                            TextView textView9=(TextView)findViewById(R.id.textViewIntereses9);
                            textView9.setText("Arte");
                        }
                        interes9= document.getString("Astrologia");
                        if (interes9.equals("True")) {
                            TextView textView10=(TextView)findViewById(R.id.textViewIntereses10);
                            textView10.setText("Astrologia");
                        }

                    } else {

                    }
                } else {

                }
            }
        });
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
                intent=  new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

                return true;

            case R.id.panas:
                intent= new Intent(this, Panas.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent= new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
        finish();


    }

    public void getActivityEditar(View view)
    {
        Intent intent = new Intent(this, pantallaDatosEditar.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
        finish();

    }


}