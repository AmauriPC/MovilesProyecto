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

public class Panas extends AppCompatActivity {

    String anime,games,literature,sports,movie,music,series,art,astrology;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panas);
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

            case R.id.panas:
                intent= new Intent(this, Panas.class);
                intent.putExtra("userid", userid);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getActivityPerfilRandom(View view)
    {
        Intent intent = new Intent(this, pantallaPerfilRandom.class);
        intent.putExtra("userid", userid);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        Intent intent= new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid", userid);
        startActivity(intent);


    }

}