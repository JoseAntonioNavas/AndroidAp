package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.nono.concesionariocoches.R;

import org.apache.http.impl.cookie.PublicSuffixFilter;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

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

        logic.MainLogic.sonido(getApplicationContext());





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