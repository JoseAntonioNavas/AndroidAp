package logic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.preference.PreferenceManager;


import com.nono.concesionariocoches.R;
import java.io.IOException;
import java.util.List;

import controller.MainActivity;

import model.Usuario;


public class MainLogic {

    public static String leerPreferenciasUsuario(Context context){

        // Leer desde las Preferencias
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String id_user = prefs.getString("id_user","");
        String email = prefs.getString("email","");
        String passwd = prefs.getString("passwd","");

        return id_user;
    }


    public static void escribirPreferenciasUsuario(List<Usuario> Usuario,Context context){
        // LOGEAMOS
        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);

        // Escribir en las preferencias
        SharedPreferences.Editor editorPrefs = prefs.edit();
        editorPrefs.putString("id_user", "" + Usuario.get(0).getId_user());
        editorPrefs.putString("email", ""+ Usuario.get(0).getEmail());
        editorPrefs.putString("passwd", "" + Usuario.get(0).getPasswd());
        editorPrefs.apply();
    }

    public static void sonido(Context context){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.notification);
        mediaPlayer.start();

    }

    public static Color StringtoRGB(String color){
        Color mColor = new Color();

        String[] colour = color.substring(4,color.length()-1).split(",");
        mColor.red(Integer.parseInt(colour[0]));
        mColor.green(Integer.parseInt(colour[1]));
        mColor.blue(Integer.parseInt(colour[2]));
        return mColor;
    }

    public static  void borrarPreferencia(Context context){
        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editorPrefs = prefs.edit();
        editorPrefs.putString("id_user", "" );
        editorPrefs.putString("email", "");
        editorPrefs.putString("passwd", "");
        editorPrefs.apply();
    }

    public static void dialogConfirm(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");
        builder.setMessage("¿Estás seguro que deseas eliminar tu cuenta?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String id_user = logic.MainLogic.leerPreferenciasUsuario(context);

                deleteUsuarioById(id_user);

                MainLogic.borrarPreferencia(context);

                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }
        });

        builder.setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static void dialogConfirmLogOut(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");
        builder.setMessage(R.string.logOutConfirm);

        builder.setPositiveButton(R.string.btnAceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                MainLogic.borrarPreferencia(context);
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        builder.setNegativeButton(R.string.btnCancelar, null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // GET coche
    public static void deleteUsuarioById(String id_user){

        new deleteUsuario_AsyncTask().execute(VariablesGlobales.url + "/api/usuario/deleteById/"+id_user);
    }

    private static class deleteUsuario_AsyncTask extends AsyncTask<String, Void, String> {



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

        }


    }


}
