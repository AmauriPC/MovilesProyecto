package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity implements OnCompleteListener<QuerySnapshot> {

    TextView cont;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont=(TextView)findViewById(R.id.textContraseña);
        username=(TextView)findViewById(R.id.textEmail);
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
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}