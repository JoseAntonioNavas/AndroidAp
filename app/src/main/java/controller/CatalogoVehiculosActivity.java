package controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
import logic.VehiculoLogic;
import model.Usuario;
import model.Vehiculo;
import model.busquedaVehiculo;

public class CatalogoVehiculosActivity extends AppCompatActivity {

    private static RecyclerView listCoches;
    private static Context context;
    private static TextView txtmsgError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_vehiculos);

        // ReclycerView
        listCoches = findViewById(R.id.listCochesRecycler);
        listCoches.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listCoches.setLayoutManager(llm);

        txtmsgError = findViewById(R.id.txtmsgError);
        //Title
        setTitle(R.string.catalogoCoches);

        // Obtenemos los coches del catalogo
        getVehiculosCatalogo(new busquedaVehiculo(""));

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
            // CARGANDO...

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

            try {
               List<Vehiculo> lstVehiculos = VehiculoLogic.JsonToVehiculo(result);

                    if(lstVehiculos.size() == 0){

                            txtmsgError.setVisibility(View.VISIBLE);
                    }else{
                            txtmsgError.setVisibility(View.INVISIBLE);
                        AdaptadorCoche adaptador = new AdaptadorCoche((ArrayList<Vehiculo>) lstVehiculos,context);
                        CatalogoVehiculosActivity.listCoches.setAdapter(adaptador);
                        adaptador.refrescar();
                    }
            } catch (JSONException e) {
                Toast.makeText(LoginActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }

        }

    }
}