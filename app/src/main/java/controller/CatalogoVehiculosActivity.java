package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.nono.concesionariocoches.R;

import logic.MainLogic;

public class CatalogoVehiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_vehiculos);

        MainLogic.sonido(getApplicationContext());
    }
}