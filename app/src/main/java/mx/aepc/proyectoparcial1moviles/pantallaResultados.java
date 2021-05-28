package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class pantallaResultados extends AppCompatActivity {

    String anime,games,literature,sports,movie,music,series,art,astrology;
    String userid;

    ArrayList<ResultadosVo> listaResultados;
    RecyclerView recyclerResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resultados);
        userid = getIntent().getStringExtra("userid");

        anime=getIntent().getStringExtra("Anime");
        games=getIntent().getStringExtra("Videojuegos");
        literature=getIntent().getStringExtra("Literatura");
        sports=getIntent().getStringExtra("Deportes");
        movie=getIntent().getStringExtra("Cine");
        music=getIntent().getStringExtra("Musica");
        series=getIntent().getStringExtra("Series");
        art=getIntent().getStringExtra("Arte");
        astrology=getIntent().getStringExtra("Astrologia");

        Log.println(Log.ASSERT,"interes","anime: "+anime);
        Log.println(Log.ASSERT,"interes","juegos: "+games);
        Log.println(Log.ASSERT,"interes","lit: "+literature);
        Log.println(Log.ASSERT,"interes","deporte: "+sports);
        Log.println(Log.ASSERT,"interes","cine: "+movie);
        Log.println(Log.ASSERT,"interes","musica: "+music);
        Log.println(Log.ASSERT,"interes","series: "+series);
        Log.println(Log.ASSERT,"interes","art: "+art);
        Log.println(Log.ASSERT,"interes","ast: "+astrology);

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
                intent.putExtra("userid",userid);
                startActivity(intent);
                finish();
                return true;

            case R.id.miPerfil:
                intent= new Intent(this, pantallaMiPerfil.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
                finish();

                return true;
            case R.id.cerrarSesion:
                intent=  new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            /*case R.id.panas:
                intent= new Intent(this, Panas.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
                return true;*/

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

    public void getActivityPerfilRandom(View view)
    {
        Intent intent = new Intent(this, pantallaPerfilRandom.class);
        intent.putExtra("userid",userid);
        startActivity(intent);
        finish();

    }

}