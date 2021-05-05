package mx.aepc.proyectoparcial1moviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class pantallaIntereses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_intereses);

    }

    public void getActivityResultados(View view)
    {
        Intent intent = new Intent(this, pantallaResultados.class);
        startActivity(intent);
    }

    public void getActivityMiPerfil(View view)
    {
        Intent intent = new Intent(this, pantallaMiPerfil.class);
        startActivity(intent);
    }

    public void getActivityLogIN(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}