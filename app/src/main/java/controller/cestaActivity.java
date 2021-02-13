package controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.PeticionHTTP;
import logic.VariablesGlobales;
import logic.VehiculoLogic;
import model.Vehiculo;
import model.busquedaVehiculo;

public class cestaActivity extends AppCompatActivity {

    private static Context context;
    RecyclerView listCesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta_activity);

        // ReclycerView
        listCesta = findViewById(R.id.cestaRecyclerView);
        listCesta.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listCesta.setLayoutManager(llm);
        context = getApplicationContext();

        setTitle(R.string.titleCesta);
        //
    }




    // GET VEHICULO
    public static void getCesta(){
        String id_user = logic.MainLogic.leerPreferenciasUsuario(cestaActivity.context);
        new getCesta_AsyncTask().execute(VariablesGlobales.url + "/api/vehiculos/getVehiculosByIdUser/"+ id_user);
    }
    private static class getCesta_AsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // CARGANDO...

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTPGET(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){

            System.out.println("XDDDDDDDDDDDDDDD" +result);
            /*
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
            }*/

        }

    }





}