package controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.MainLogic;
import logic.PeticionHTTP;
import logic.UsuarioLogic;
import logic.VariablesGlobales;
import model.Usuario;
import model.busquedaVehiculo;

public class CatalogoVehiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_vehiculos);


        getVehiculosCatalogo(new busquedaVehiculo(""));
/*
        ArrayList<Vehiculo> elementos;
        if(Logica.elementos.size() > 0){

            elementos = Logica.elementos;
        }else{
            elementos = Logica.getElements();
        }

        RecyclerView listProductos = findViewById(R.id.listProductos);
        listProductos.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listProductos.setLayoutManager(llm);

        AdaptadorProducto adaptador = new AdaptadorProducto(elementos,this);
        listProductos.setAdapter(adaptador);
        adaptador.refrescar();

*/
    }
    @Override public void onBackPressed() {
        moveTaskToBack(true);
    }


    // GET VEHICULO
    public static void getVehiculosCatalogo(busquedaVehiculo busquedaVehiculo){
        Gson g = new Gson();
        String str = g.toJson(busquedaVehiculo);

        new CatalogoVehiculosActivity.getVehiculosCatalogo_AsyncTask().execute(VariablesGlobales.url + "/api/vehiculos/getVehiculosBBDD", str);
    }
    private static class getVehiculosCatalogo_AsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTP(params,"POST");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){

            System.out.println(result);
         /*   try {

            } catch (JSONException e) {
                Toast.makeText(LoginActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }*/
        }

    }
}