package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class pantallaResultados extends AppCompatActivity {

    /*private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");
    private DocumentReference userRef = db.document("userid");*/

    ArrayList<ResultadosVo> listaResultados;
    RecyclerView recyclerResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resultados);

        listaResultados = new ArrayList<>();
        recyclerResultados = (RecyclerView) findViewById(R.id.recyclerId);
        recyclerResultados.setLayoutManager(new LinearLayoutManager(this));

        llenarResultados();

        AdapterResultados adapter = new AdapterResultados(listaResultados);
        recyclerResultados.setAdapter(adapter);
    }

    private void llenarResultados() {



        //asynchronously retrieve all documents
// future.get() blocks on response
        /*List<QueryDocumentSnapshot> documents = ref.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(City.class));
        }

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
        });*/

        int edad = 20;
        listaResultados.add(new ResultadosVo("Random 1","Edad: " + edad, R.drawable.animeicon));
        listaResultados.add(new ResultadosVo("Random 2","30", R.drawable.articon));
        listaResultados.add(new ResultadosVo("Random 3","25", R.drawable.booksicon));
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

                startActivity(intent);
                return true;

            case R.id.miPerfil:
                intent= new Intent(this, pantallaMiPerfil.class);

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

    public void getActivityPerfilRandom(View view)
    {
        Intent intent = new Intent(this, pantallaPerfilRandom.class);
        startActivity(intent);
    }
    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        startActivity(intent);
    }
}