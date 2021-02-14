package logic;

import android.content.Context;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.detallesUsuario;

public class ProfileLogic {


    public static List<Object> getFieldsProfile(EditText txtEmailProfile, EditText txtPasswordProfile, EditText txtNickProfile, EditText txtNombreProfile, EditText txtApellido1Profile, EditText txtApellido2Profile, Context context) {

            String email = String.valueOf(txtEmailProfile.getText());
            String passwd = String.valueOf(txtPasswordProfile.getText());
            String nick = String.valueOf(txtNickProfile.getText());
            String nombre = String.valueOf(txtNombreProfile.getText());
            String apellido1 = String.valueOf(txtApellido1Profile.getText());
            String apellido2 = String.valueOf(txtApellido2Profile.getText());

            int id_user = Integer.parseInt(logic.MainLogic.leerPreferenciasUsuario(context));

            List<Object> lstObject = new ArrayList<Object>();
            lstObject.add(new Usuario(id_user,email,passwd));
            lstObject.add(new detallesUsuario(id_user,nick,1,nombre,apellido1,apellido2));

            return lstObject;

    }
}
