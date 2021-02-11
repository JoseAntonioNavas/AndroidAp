package logic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.nono.concesionariocoches.R;

import java.util.List;

import controller.LoginActivity;
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



}
