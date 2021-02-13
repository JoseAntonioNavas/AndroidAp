package controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.browse.MediaBrowser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import model.detallesUsuario;

public class CatalogoVehiculosActivity extends AppCompatActivity {

    private static RecyclerView listCoches;
    private static Context context;
    private static TextView txtmsgError;
    private static List<detallesUsuario> lstDu;

    private static Menu menu;

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

        //Obtenemos detalles del usurio para saber el rol


        // Obtenemos los coches del catalogo
        getVehiculosCatalogo(new busquedaVehiculo(""));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);

        String id_user = MainLogic.leerPreferenciasUsuario(this);
        getDetalleUsuario(id_user,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu4:
                Intent intent1 = new Intent(this, ProfileActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

            break;

            case R.id.menu3:

                Intent intent3 = new Intent(this, cestaActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);


                break;
            case R.id.menu2:

                Intent intent2 = new Intent(this, cestaActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);

                break;
            case R.id.menu1:
                MainLogic.borrarPreferencia(this);

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

        }
        return true;
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


    // GET coche
    public static void getDetalleUsuario(String id_user,Menu menu){

        new CatalogoVehiculosActivity.getDetalleUsuario_AsyncTask(menu).execute(VariablesGlobales.url + "/api/detalles-usuarioById/"+id_user);
    }
    private static class getDetalleUsuario_AsyncTask extends AsyncTask<String, Void, String> {

        private final Menu menu;

        private getDetalleUsuario_AsyncTask(Menu menu) {
            this.menu = menu;
        }
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


            try {

                CatalogoVehiculosActivity.lstDu =  logic.detallesUsuarioLogic.JsonTodetalleUsuarios(result);


                if(CatalogoVehiculosActivity.lstDu.get(0).getId_rol() == 1){
                    menu.getItem(2).setVisible(false);
                }


            } catch (JSONException e) {
                Toast.makeText(LoginActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }



        }

    }
}