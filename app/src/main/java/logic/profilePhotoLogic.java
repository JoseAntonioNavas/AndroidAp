package logic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.nono.concesionariocoches.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import controller.MainActivity;
import model.Marca;
import model.profilePhoto;

public class profilePhotoLogic {

    public static List<profilePhoto> JsonToprofilePhoto(String response) throws JSONException {

        List<profilePhoto> lstFotoPerfil = new ArrayList<>();

        JSONArray jsonA = new JSONArray(response);
        for (int i = 0; i < jsonA.length(); i++) {

            JSONObject jsonO = jsonA.getJSONObject(i);
            profilePhoto fp = JsonToProfilePhotos(jsonO);

            lstFotoPerfil.add(fp);

        }

        return lstFotoPerfil;
    }

    private static profilePhoto JsonToProfilePhotos(JSONObject jsonO) throws JSONException {

        Integer id_user = jsonO.getInt("id_user");
        String fileData = jsonO.getString("fileData");

        return new profilePhoto(id_user,fileData);
    }

    public static void dialogConfirm(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");
        builder.setMessage(R.string.deleteImage);

        builder.setPositiveButton(R.string.btnAceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                controller.imgProfile.deleteImagen();

            }
        });

        builder.setNegativeButton(R.string.btnCancelar, null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
