package mx.aepc.proyectoparcial1moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pantallaMiPerfil extends AppCompatActivity {

    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_mi_perfil);
        userid=getIntent().getStringExtra("userid");
    }

    public void getActivityEditar(View view)
    {
        Intent intent = new Intent(this, pantallaDatosEditar.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }

    public void getActivityIntereses(View view)
    {
        Intent intent = new Intent(this, pantallaIntereses.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }
}