package mx.aepc.proyectoparcial1moviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void getActivityResultados(View view)
    {
        Intent intent = new Intent(this, pantallaResultados.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }


}