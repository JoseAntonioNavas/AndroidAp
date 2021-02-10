package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import logic.PeticionHTTP;
import logic.UsuarioLogic;
import logic.VariablesGlobales;
import logic.detallesUsuarioLogic;
import model.Usuario;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    EditText txtNick;
    EditText txtNombre;
    EditText txtApellido1;
    EditText txtApellido2;
    public static Context context;
    Button BtnRegistrarRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        context = getApplicationContext();
        txtEmail = findViewById(R.id.txtEmailRegistrar);
        txtPassword = findViewById(R.id.txtPasswordRegistrar);
        txtNick = findViewById(R.id.txtNickRegistrar);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido1 = findViewById(R.id.txtApellido1Registrar);
        txtApellido2 = findViewById(R.id.txtApellido2Registrar);
        BtnRegistrarRegistrar = findViewById(R.id.BtnRegistrarRegistrar);

        BtnRegistrarRegistrar.setOnClickListener(v -> {

            List<Object> lstObject = detallesUsuarioLogic.getFieldsRegistrar(txtEmail, txtPassword, txtNick, txtNombre, txtApellido1, txtApellido2);

            System.out.println(lstObject);

        });

    }




    // PETICIONES
    public static void newUsuario(Usuario usuario){
        Gson g = new Gson();
        String str = g.toJson(usuario);

        new RegistrarActivity.newUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/usuario/login", str);
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
                List<Usuario> Usuario = UsuarioLogic.JsonToUsuarios(result);

                if(Usuario.size() == 0){

                    Toast.makeText(LoginActivity.context, R.string.credenciales_no_validas, Toast.LENGTH_LONG).show();

                }else{

                    logic.MainLogic.escribirPreferenciasUsuario(Usuario,context);

                    Intent intent1 = new Intent(context,CatalogoVehiculosActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);

                }

            } catch (JSONException e) {
                Toast.makeText(LoginActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }
        }

    }


}