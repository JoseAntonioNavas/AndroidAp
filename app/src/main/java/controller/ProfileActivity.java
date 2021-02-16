package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.Menu;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.PeticionHTTP;
import logic.ProfileLogic;
import logic.ResponseLogic;
import logic.VariablesGlobales;
import model.Roles;
import model.Usuario;
import model.detallesUsuario;

public class ProfileActivity extends AppCompatActivity {


    private static Context context;
    private static EditText txtEmailProfile;
    private static EditText txtPasswordProfile;
    private static EditText txtNickProfile;
    private static EditText txtNombreProfile;
    private static EditText txtApellido1Profile;
    private static EditText txtApellido2Profile;
    private static Button btnActualizarProfile;
    private static Button btnBorrarProfile;
    private static Button btnVerFotoPerfil;
    private static detallesUsuario dUsuario;
    private static Switch switchVerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        context = getApplicationContext();
        setTitle(R.string.miPerfil);

        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        txtPasswordProfile = findViewById(R.id.txtPasswordProfile);
        txtNickProfile = findViewById(R.id.txtNickProfile);
        txtNombreProfile = findViewById(R.id.txtNombreProfile);
        txtApellido1Profile = findViewById(R.id.txtApellido1Profile);
        txtApellido2Profile = findViewById(R.id.txtApellido2Profile);
        btnActualizarProfile = findViewById(R.id.btnActualizarProfile);
        btnBorrarProfile = findViewById(R.id.btnBorrarProfile);
        btnVerFotoPerfil = findViewById(R.id.btnVerFotoPerfil);
        switchVerPassword = findViewById(R.id.switchVerPassword);

        // get datos perfil
        getDetalleUsuario(logic.MainLogic.leerPreferenciasUsuario(context));

        // Btn Actualizar
        btnActualizarProfile.setOnClickListener(v -> {
            List<Object> lstObject = logic.ProfileLogic.getFieldsProfile(txtEmailProfile,txtPasswordProfile,txtNickProfile,txtNombreProfile,txtApellido1Profile,txtApellido2Profile,context);

            Usuario usuario = (Usuario) lstObject.get(0);
            dUsuario = (detallesUsuario) lstObject.get(1);

            updateDetallesUsuarioByIdUser(usuario.getPasswd(),dUsuario);


        });



        // SWITHC

        switchVerPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtPasswordProfile.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    txtPasswordProfile.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        //Btn Borrar

        btnBorrarProfile.setOnClickListener(v -> {
            logic.MainLogic.dialogConfirm(this);
        });

        btnVerFotoPerfil.setOnClickListener(v ->{
            Intent intent3 = new Intent(this, imgProfile.class);
            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent3);

        });

    }



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

            try {

                List<Object>  lstDu = logic.detallesUsuarioLogic.JsonTodetalleUsuarioObject(result);

                detallesUsuario du = (detallesUsuario) lstDu.get(0);
                Usuario u = (Usuario) lstDu.get(1);
                Roles r = (Roles) lstDu.get(2);

               txtEmailProfile.setText(u.getEmail());
               txtPasswordProfile.setText(u.getPasswd());
               txtNickProfile.setText(du.getNick_user());
               txtNombreProfile.setText(du.getNombre());
               txtApellido1Profile.setText(du.getApellido_1());
               txtApellido2Profile.setText(du.getApellido_2());



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


    // UPDATE PASSWORD
    public static void updateDetallesUsuarioByIdUser(String password, detallesUsuario du){
        Gson g = new Gson();
        String str = g.toJson(du);
        new updateDetallesUsuarioByIdUser_AsyncTask().execute(VariablesGlobales.url + "/api/detalles-usuario/updateByIdUser/"+password,str);
    }
    private static class updateDetallesUsuarioByIdUser_AsyncTask extends AsyncTask<String, Void, String> {


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
                List<ResponseLogic> response = ResponseLogic.JsonToErrores(result);
                if(response.get(0).getMsg().equals("OK")){

                    Toast.makeText(ProfileActivity.context, R.string.updateProfileSuccessful, Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(ProfileActivity.context, response.get(0).getMsg(), Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                Toast.makeText(ProfileActivity.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }

        }


    }
}