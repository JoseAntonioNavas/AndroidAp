package controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.PeticionHTTP;
import logic.VariablesGlobales;
import logic.VehiculoLogic;
import model.Vehiculo;

public class cestaActivity extends AppCompatActivity {

    private static Context context;
    public static RecyclerView listCesta;
    public static TextView txtmsgCesta;
    private static  ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta_activity);


        context = getApplicationContext();
        txtmsgCesta = findViewById(R.id.txtmsgCesta);
        // ReclycerView
        listCesta = findViewById(R.id.cestaRecyclerView);
        listCesta.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listCesta.setLayoutManager(llm);
        setTitle(R.string.titleCesta);


        getCesta();

    }


    @Override public void onBackPressed() {


        Intent intent1 = new Intent(context,CatalogoVehiculosActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

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


            try {
                List<Vehiculo> lstVehiculos = VehiculoLogic.JsonToVehiculo(result);

                if(lstVehiculos.size() == 0){

                    txtmsgCesta.setVisibility(View.VISIBLE);

                }else{
                    txtmsgCesta.setVisibility(View.INVISIBLE);
                    AdaptadorCoche adaptador = new AdaptadorCoche((ArrayList<Vehiculo>) lstVehiculos,context,"Cesta");
                    listCesta.setAdapter(adaptador);
                    adaptador.refrescar();

                }
            } catch (JSONException e) {
                Toast.makeText(cestaActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }

        }

    }





}