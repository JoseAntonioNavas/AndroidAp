package controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import logic.MainLogic;
import logic.PeticionHTTP;
import logic.UsuarioLogic;
import logic.VariablesGlobales;
import model.Usuario;

public class LoginActivity extends AppCompatActivity{

    public static Context context;
    private static EditText txtEmail;
    private static EditText txtPassword;
    Button btnLogin;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogIn_LoginActivity);
        btnRegistrar = findViewById(R.id.btnRegistrar_LoginActivity);

        btnLogin.setOnClickListener(v -> {
            Usuario u = logic.LoginLogic.getFields(txtEmail,txtPassword);
            login(u);
        });

        btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(context,RegistrarActivity.class);
            startActivity(intent);
        });


    }



    // PETICIONES LOGIN
    public static void login(Usuario usuario){
        Gson g = new Gson();
        String str = g.toJson(usuario);

        new login_AsyncTask().execute(VariablesGlobales.url + "/api/usuario/login", str);
    }
    private static class login_AsyncTask extends AsyncTask<String, Void, String> {

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