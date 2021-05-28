package mx.aepc.proyectoparcial1moviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pantallaResultados extends AppCompatActivity {

    String anime,games,literature,sports,movie,music,series,art,astrology;
    String userid;

    private RecyclerView.Adapter mAdapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();

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


        listaResultados = new ArrayList<>();
        recyclerResultados = (RecyclerView) findViewById(R.id.recyclerId);
        recyclerResultados.setHasFixedSize(true);
        recyclerResultados.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        llenarResultados();
        mAdapter= new AdapterResultados(listaResultados);
        recyclerResultados.setAdapter(mAdapter);


        AdapterResultados adapter = new AdapterResultados(listaResultados);
        recyclerResultados.setAdapter(adapter);

    }

    private void llenarResultados() {

        listaResultados.add(new ResultadosVo("Samantha","21", R.drawable.animeicon));
        listaResultados.add(new ResultadosVo("Jesus","20", R.drawable.animeicon));
        listaResultados.add(new ResultadosVo("Emiliano","20", R.drawable.animeicon));
        listaResultados.add(new ResultadosVo("Lazca","20", R.drawable.animeicon));
        listaResultados.add(new ResultadosVo("Mawi","21", R.drawable.animeicon));

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

            case R.id.panas:
                intent= new Intent(this, Panas.class);
                intent.putExtra("userid",userid);
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

    public void getActivityPerfilRandom(View view)
    {
        Intent intent = new Intent(this, pantallaPerfilRandom.class);
        intent.putExtra("userid",userid);
        startActivity(intent);
        finish();

    }

}