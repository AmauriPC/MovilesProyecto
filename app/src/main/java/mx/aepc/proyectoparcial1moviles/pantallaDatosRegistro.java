package mx.aepc.proyectoparcial1moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.net.Socket;
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
        if(cbGames.isChecked())user.put("Videojuegos","True");
        if(cbLiterature.isChecked())user.put("Literatura","True");
        if(cbSports.isChecked())user.put("Deportes","True");
        if(cbCine.isChecked())user.put("Cine","True");
        if(cbMusic.isChecked())user.put("Musica","True");
        if(cbSeries.isChecked())user.put("Series","True");
        if(cbArt.isChecked())user.put("Arte","True");
        if(cbAstrology.isChecked())user.put("Astrologia","True");


        db.collection("users").document(mailS).set(user);

    }

    public void getActivityLog(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
            Log.println(Log.ASSERT,"MSG","Hay un campo vacio, por favor verifica de nuevo");
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), pantallaIntereses.class);
            startActivity(intent);
            crearUsuario();
            Toast.makeText(this, "¡Usuario Creado!", Toast.LENGTH_SHORT).show();
            Log.println(Log.ASSERT,"MSG","¡Usuario Creado!");
        }


    }
}