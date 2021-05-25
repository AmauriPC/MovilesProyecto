package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

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
                        Log.println(Log.ASSERT,"MSG", "No such document");
                    }
                } else {
                    Log.println(Log.ASSERT,"MSG", "get failed with "+task.getException());
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
                return true;

            case R.id.miPerfil:
                intent= new Intent(this, pantallaMiPerfil.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
                return true;
            case R.id.cerrarSesion:
                intent=  new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getActivityEditar(View view)
    {
        Intent intent = new Intent(this, pantallaDatosEditar.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }


}