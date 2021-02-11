package logic;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Color;
import model.Marca;
import model.Modelo;
import model.Usuario;
import model.Vehiculo;
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

        detallesUsuario du = new detallesUsuario(-1,nick,1,nombre,apellido1,apellido2);


        List<Object> lstObject = new ArrayList<Object>();
        lstObject.add(usuario);
        lstObject.add(du);

        return lstObject;

    }

    public static List<detallesUsuario> JsonTodetalleUsuarios(String response) throws JSONException {

        List<detallesUsuario> listdetallesUsuario = new ArrayList<>();

        JSONArray jsonA = new JSONArray(response);
        for (int i = 0; i < jsonA.length(); i++) {

            JSONObject jsonO = jsonA.getJSONObject(i);
            detallesUsuario v = JsonTodetalleUsuario(jsonO);

            listdetallesUsuario.add(v);

        }

        return listdetallesUsuario;
    }

    private static detallesUsuario JsonTodetalleUsuario(JSONObject jsonO) throws JSONException {
        int id_detalle_usuario = jsonO.getInt("id_detalle_usuario");
        String nick_user = jsonO.getString("nick_user");
        String nombre = jsonO.getString("nombre");
        String apellido_1 = jsonO.getString("apellido_1");
        String apellido_2 = jsonO.getString("apellido_2");


        // Usuario
        int id_user = jsonO.getJSONObject("usuario").getInt("id_user");
        String email = jsonO.getJSONObject("usuario").getString("email");
        String passwd = jsonO.getJSONObject("usuario").getString("passwd");


        //Rol
        int id_rol = jsonO.getJSONObject("rol").getInt("id_rol");
        String nombre_rol = jsonO.getJSONObject("rol").getString("nombre_rol");

        return new detallesUsuario(id_detalle_usuario,id_user,nick_user,id_rol,nombre,apellido_1,apellido_2);

    }
}
