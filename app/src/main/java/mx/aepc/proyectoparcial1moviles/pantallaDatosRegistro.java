package mx.aepc.proyectoparcial1moviles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class pantallaDatosRegistro extends AppCompatActivity {


    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos_registro);

    }

    public pantallaDatosRegistro(){
        context=this;


    }


    public void crearUsuario()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        TextView txtNombre= (TextView)findViewById(R.id.editTextName);
        TextView txtCorreo= (TextView)findViewById(R.id.editTextCorreo);
        TextView txtContraseña= (TextView)findViewById(R.id.editTextContrasena);
        TextView txtEdad= (TextView)findViewById(R.id.editTextEdad);
        String nameS=txtNombre.getText().toString();
        String mailS=txtCorreo.getText().toString();
        String passS=txtContraseña.getText().toString();
        String ageS= txtEdad.getText().toString();


        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeCB2);
        CheckBox cbGames=(CheckBox)findViewById(R.id.videojuegosCB2);
        CheckBox cbLiterature=(CheckBox)findViewById(R.id.literaturaCB2);
        CheckBox cbSports=(CheckBox)findViewById(R.id.deportesCB2);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineCB2);
        CheckBox cbMusic=(CheckBox)findViewById(R.id.musicaCB2);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesCB2);
        CheckBox cbArt=(CheckBox)findViewById(R.id.arteCB2);
        CheckBox cbAstrology=(CheckBox)findViewById(R.id.astrologiaCB2);








        user.put("Nombre", nameS);
        user.put("Contraseña", passS);
        user.put("Edad",ageS);

        if(cbAnime.isChecked())user.put("Anime","True");
        else{user.put("Anime","False");}
        if(cbGames.isChecked())user.put("Videojuegos","True");
        else{user.put("Videojuegos","False");}
        if(cbLiterature.isChecked())user.put("Literatura","True");
        else{user.put("Literatura","False");}
        if(cbSports.isChecked())user.put("Deportes","True");
        else{user.put("Deportes","False");}
        if(cbCine.isChecked())user.put("Cine","True");
        else{user.put("Cine","False");}
        if(cbMusic.isChecked())user.put("Musica","True");
        else{user.put("Musica","False");}
        if(cbSeries.isChecked())user.put("Series","True");
        else{user.put("Series","False");}
        if(cbArt.isChecked())user.put("Arte","True");
        else{user.put("Arte","False");}
        if(cbAstrology.isChecked())user.put("Astrologia","True");
        else{user.put("Astrologia","False");}


        db.collection("users").document(mailS).set(user);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void getActivityIntereses(View view)
    {
        TextView txtNombre= (TextView)findViewById(R.id.editTextName);
        TextView txtCorreo= (TextView)findViewById(R.id.editTextCorreo);
        TextView txtContraseña= (TextView)findViewById(R.id.editTextContrasena);
        TextView txtEdad= (TextView)findViewById(R.id.editTextEdad);
        String nameS=txtNombre.getText().toString();
        String mailS=txtCorreo.getText().toString();
        String passS=txtContraseña.getText().toString();
        String ageS= txtEdad.getText().toString();

        if(nameS.matches("") || mailS.matches("") || passS.matches("") || ageS.matches(""))
        {
            Toast.makeText(this, "Hay un campo vacio, por favor verifica de nuevo", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), pantallaIntereses.class);
            intent.putExtra("userid",txtCorreo.getText().toString());
            startActivity(intent);
            crearUsuario();

            Toast.makeText(this, "¡Usuario Creado!", Toast.LENGTH_SHORT).show();
            finish();

        }


    }
}