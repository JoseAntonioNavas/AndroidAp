package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.PeticionHTTP;
import logic.ResponseLogic;
import logic.UsuarioLogic;
import logic.VariablesGlobales;
import logic.detallesUsuarioLogic;
import model.Usuario;
import model.detallesUsuario;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    EditText txtNick;
    EditText txtNombre;
    EditText txtApellido1;
    EditText txtApellido2;
    public static Context context;
    Button BtnRegistrarRegistrar;
    private static List<Object> objetosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        setTitle(R.string.Registrar);
        context = getApplicationContext();
        txtEmail = findViewById(R.id.txtEmailRegistrar);
        txtPassword = findViewById(R.id.txtPasswordRegistrar);
        txtNick = findViewById(R.id.txtNickRegistrar);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido1 = findViewById(R.id.txtApellido1Registrar);
        txtApellido2 = findViewById(R.id.txtApellido2Registrar);
        BtnRegistrarRegistrar = findViewById(R.id.BtnRegistrarRegistrar);

        BtnRegistrarRegistrar.setOnClickListener(v -> {

            objetosUsuario = detallesUsuarioLogic.getFieldsRegistrar(txtEmail, txtPassword, txtNick, txtNombre, txtApellido1, txtApellido2);

            newUsuario((Usuario) objetosUsuario.get(0));
        });

    }




    // PETICIONES
    public static void newUsuario(Usuario usuario){
        Gson g = new Gson();
        String str = g.toJson(usuario);

        new RegistrarActivity.newUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/usuario/new", str);
    }
    private static class newUsuario_AsyncTask extends AsyncTask<String, Void, String> {

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

            try {
                List<ResponseLogic> res = ResponseLogic.JsonToErrores(result);

                if (res.get(0).getMsg().equalsIgnoreCase("OK")){

                    // Insertamos los demás datos de usuario

                    RegistrarActivity.newDetallesUsuario((detallesUsuario) RegistrarActivity.objetosUsuario.get(1));


                }else{


                    Toast.makeText(RegistrarActivity.context, res.get(0).getMsg(), Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(RegistrarActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }
        }

    }



    // PETICIONES
    public static void newDetallesUsuario(detallesUsuario du){
        Gson g = new Gson();
        String str = g.toJson(du);

        new RegistrarActivity.newDetallesUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/detalles-usuario1/new", str);
    }
    private static class newDetallesUsuario_AsyncTask extends AsyncTask<String, Void, String> {

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


            try {
                List<ResponseLogic> res = ResponseLogic.JsonToErrores(result);

                if (res.get(0).getMsg().equalsIgnoreCase("OK")){


                    List<Usuario> u = new ArrayList<Usuario>();
                    u.add((Usuario) RegistrarActivity.objetosUsuario.get(0));
                    logic.MainLogic.escribirPreferenciasUsuario(u,RegistrarActivity.context);


                    Intent intent1 = new Intent(context,CatalogoVehiculosActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);


                }else{

                    Toast.makeText(RegistrarActivity.context, res.get(0).getMsg(), Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                Toast.makeText(RegistrarActivity.context, R.string.catchError , Toast.LENGTH_LONG).show();
            }
        }

    }





    // PETICIONES
    public static void deleteUsuario(String id_usuario){

        new deleteUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/usuario/deleteById/"+id_usuario);
    }
    private static class deleteUsuario_AsyncTask extends AsyncTask<String, Void, String> {

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


        }

    }


}

