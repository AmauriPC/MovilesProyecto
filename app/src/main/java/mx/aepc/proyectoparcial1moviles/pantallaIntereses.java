package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class pantallaIntereses extends AppCompatActivity {

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_intereses);
        userid=getIntent().getStringExtra("userid");
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
                intent= new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.panas:
                intent= new Intent(this, Panas.class);
                startActivity(intent);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void getActivityResultados(View view)
    {
        CheckBox cbAnime=(CheckBox)findViewById(R.id.animeICB);
        CheckBox cbGames=(CheckBox)findViewById(R.id.videojuegosICB);
        CheckBox cbLiterature=(CheckBox)findViewById(R.id.literaturaICB);
        CheckBox cbSports=(CheckBox)findViewById(R.id.deportesICB);
        CheckBox cbCine=(CheckBox)findViewById(R.id.cineICB);
        CheckBox cbMusic=(CheckBox)findViewById(R.id.musicaICB);
        CheckBox cbSeries=(CheckBox)findViewById(R.id.seriesICB);
        CheckBox cbArt=(CheckBox)findViewById(R.id.arteICB);
        CheckBox cbAstrology=(CheckBox)findViewById(R.id.astrologiaICB);

        Intent intent = new Intent(this, pantallaResultados.class);
        intent.putExtra("userid", userid);
        if(cbAnime.isChecked())intent.putExtra("Anime","True");
        if(cbGames.isChecked())intent.putExtra("Videojuegos","True");
        if(cbLiterature.isChecked())intent.putExtra("Literatura","True");
        if(cbSports.isChecked())intent.putExtra("Deportes","True");
        if(cbCine.isChecked())intent.putExtra("Cine","True");
        if(cbMusic.isChecked())intent.putExtra("Musica","True");
        if(cbSeries.isChecked())intent.putExtra("Series","True");
        if(cbArt.isChecked())intent.putExtra("Arte","True");
        if(cbAstrology.isChecked())intent.putExtra("Astrologia","True");
        startActivity(intent);
    }


}