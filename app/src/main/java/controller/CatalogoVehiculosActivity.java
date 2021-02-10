package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.nono.concesionariocoches.R;

import java.util.ArrayList;

import logic.MainLogic;
import model.Usuario;

public class CatalogoVehiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_vehiculos);





    }
    @Override public void onBackPressed() {
        moveTaskToBack(true);
    }

}