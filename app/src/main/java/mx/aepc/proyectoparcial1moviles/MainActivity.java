package mx.aepc.proyectoparcial1moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "elbisho");
        user.put("last", "llanaSeLaCome");
        user.put("born", 2000);
        db.collection("users").document("elbisho@gmail.com").set(user);

        //comentario de prueba de mawi a ver que pdo 2:11 pm 6 mayo 2021
    }

    public void getActivityDatos(View view)
    {
        Intent intent = new Intent(this, pantallaDatosRegistro.class);
        startActivity(intent);
    }

    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        startActivity(intent);
    }


}