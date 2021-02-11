package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import logic.PeticionHTTP;
import logic.VariablesGlobales;
import model.detallesUsuario;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    private static EditText txtEmailProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        context = getApplicationContext();
        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        setTitle(R.string.miPerfil);


        getDetalleUsuario(logic.MainLogic.leerPreferenciasUsuario(context));

    }


    // GET coche
    public static void getDetalleUsuario(String id_user){

        new ProfileActivity.getDetalleUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/detalles-usuarioById/"+id_user);
    }
    private static class getDetalleUsuario_AsyncTask extends AsyncTask<String, Void, String> {


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


            System.out.println("aaaa" + result);
            try {
               List<detallesUsuario> lstDu =  logic.detallesUsuarioLogic.JsonTodetalleUsuarios(result);

                txtEmailProfile.setText(lstDu.get(0).getApellido_1());


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}