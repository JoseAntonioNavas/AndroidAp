package logic;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.detallesUsuario;

public class detallesUsuarioLogic {


    public static List<Object> getFieldsRegistrar(EditText txtEmail, EditText txtPassword, EditText txtNick, EditText txtNombre, EditText txtApellido1, EditText txtApellido2) {

        String email = String.valueOf(txtEmail.getText());
        String password = String.valueOf(txtPassword.getText());

        String nick = String.valueOf(txtNick.getText());
        String nombre = String.valueOf(txtNombre.getText());
        String apellido1 = String.valueOf(txtApellido1.getText());
        String apellido2 = String.valueOf(txtApellido2.getText());

        Usuario usuario = new Usuario(email,password);

        detallesUsuario du = new detallesUsuario(nick,1,nombre,apellido1,apellido2);

         List<Object> lstObject = new ArrayList<Object>();
         lstObject.add(usuario);
         lstObject.add(du);

        return lstObject;

    }
}
