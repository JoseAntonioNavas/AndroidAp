package logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioLogic {

    public static List<Usuario> JsonToUsuarios(String response) throws JSONException {

        List<Usuario> listUsuario = new ArrayList<>();

        JSONArray jsonA = new JSONArray(response);
        for (int i = 0; i < jsonA.length(); i++) {

            JSONObject jsonO = jsonA.getJSONObject(i);
            Usuario usuario = JsonToUsuario(jsonO);

            listUsuario.add(usuario);

        }

        return listUsuario;
    }

    private static Usuario JsonToUsuario(JSONObject jsonO) throws JSONException {

        Integer id_user = jsonO.getInt("id_user");
        String email = jsonO.getString("email");
        String passwd = jsonO.getString("passwd");

        Usuario usuario = new Usuario(id_user,email,passwd);
        return usuario;
    }

}
