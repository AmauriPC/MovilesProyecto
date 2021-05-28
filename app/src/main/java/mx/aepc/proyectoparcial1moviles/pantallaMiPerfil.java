package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.Source;

import java.util.Map;


public class pantallaMiPerfil extends AppCompatActivity {

    String userid;
    String useredad, usernombre, interes;
    String message;
    String text, textinteres;
    Map<String, Object> userinfo;
    private String Edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_mi_perfil);
        userid=getIntent().getStringExtra("userid");
        getDatos();
    }

    public void getDatos(){
        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeCB);
        CheckBox cbVideojuegos=(CheckBox)findViewById(R.id.videojuegosCB);
        CheckBox cbLiteratura=(CheckBox)findViewById(R.id.literaturaCB);
        CheckBox cbDeportes=(CheckBox)findViewById(R.id.deportesCB);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineCB);
        CheckBox cbMusica=(CheckBox)findViewById(R.id.musicaCB);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesCB);
        CheckBox cbArte=(CheckBox)findViewById(R.id.arteCB);
        CheckBox cbAstrologia=(CheckBox)findViewById(R.id.astrologiaCB);

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
                        //Log.i(message,"Edad: " + useredad);
                        text = "Edad: " + useredad;
                        TextView textView = (TextView) findViewById(R.id.textViewEdad);
                        textView.setText(text);

                        //Nombre
                        usernombre = document.getString("Nombre");
                        textView = (TextView) findViewById(R.id.textViewNombre);
                        textView.setText(usernombre);

                        interes = document.getString("Anime");
                        //Log.d(message,interes);
                        if (interes == "True") {
                            textinteres += "Anime \n";
                        }

                        //Log.d(message,textinteres);

                        textView = (TextView) findViewById(R.id.textViewIntereses);
                        textView.setText(textinteres);

                    } else {
                        Log.d(message, "No such document");
                    }
                } else {
                    Log.d(message, "get failed with ", task.getException());
                }
            }
        });
    }

    public void getActivityEditar(View view)
    {
        Intent intent = new Intent(this, pantallaDatosEditar.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }

    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }

}