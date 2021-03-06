package mx.aepc.proyectoparcial1moviles;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class pantallaDatosEditar extends AppCompatActivity {
    String userid;
    String interes,interes2,interes3,interes4,interes5,interes6,interes7,interes8,interes9;


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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(userid);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    //Edad
                    interes = document.getString("Anime");

                    if (interes.equals("True")) {
                        cbAnime.setChecked(true);
                    }

                    interes2 = document.getString("Videojuegos");
                    if (interes2.equals("True")) {
                        cbGames.setChecked(true);
                    }

                    interes3 = document.getString("Literatura");
                    if (interes3.equals("True")) {
                        cbLiterature.setChecked(true);
                    }

                    interes4= document.getString("Deportes");
                    if (interes4.equals("True")) {
                        cbSports.setChecked(true);
                    }

                    interes5= document.getString("Cine");
                    if (interes5.equals("True")) {
                        cbCine.setChecked(true);
                    }

                    interes6= document.getString("Musica");
                    if (interes6.equals("True")) {
                        cbMusic.setChecked(true);
                    }

                    interes7= document.getString("Series");
                    if (interes7.equals("True")) {
                        cbSeries.setChecked(true);
                    }
                    interes8= document.getString("Arte");
                    if (interes8.equals("True")) {
                        cbArt.setChecked(true);
                    }
                    interes9= document.getString("Astrologia");
                    if (interes9.equals("True")) {
                        cbAstrology.setChecked(true);
                    }

                } else {

                }
            } else {

            }
        });
    }





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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("??Salir sin editar datos?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(getBaseContext(),pantallaMiPerfil.class);
                        intent.putExtra("userid",userid);
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

    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid",userid);
        startActivity(intent);
        editarDatos();
        finish();
    }
}