package logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.preference.PreferenceManager;

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
        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(LoginActivity.context);

        // Escribir en las preferencias
        SharedPreferences.Editor editorPrefs = prefs.edit();
        editorPrefs.putString("id_user", "" + Usuario.get(0).getId_user());
        editorPrefs.putString("email", ""+ Usuario.get(0).getEmail());
        editorPrefs.putString("passwd", "" + Usuario.get(0).getPasswd());
        editorPrefs.apply();
    }

    public static void sonido(Context context){
        SoundPool soundPool = new SoundPool.Builder().setMaxStreams(10).build();
        int idSoundPool = soundPool.load(context, R.raw.soundlogin, 1);
        soundPool.play(idSoundPool,1,1,1,0,1);
    }
}
