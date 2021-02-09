package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.nono.concesionariocoches.R;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    Button btnLogin;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Titulo
        setTitle(R.string.titleMainActivity);

        context = getApplicationContext();
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Si hay usuario en preferencias
        if(logic.MainLogic.leerPreferenciasUsuario(MainActivity.context) != ""){

            Intent intent = new Intent(context, CatalogoVehiculosActivity.class);
            startActivity(intent);
            finish();

        }else{

            // Botón Iniciar
            btnLogin.setOnClickListener(v -> {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            });

            // Botón Registrar
            btnRegistrar.setOnClickListener(v-> {
                Intent intent = new Intent(this, RegistrarActivity.class);
                startActivity(intent);
            });


        }


    }
}