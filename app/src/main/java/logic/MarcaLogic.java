package logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Marca;

public class MarcaLogic {

    public static List<Marca> JsonToMarcas(String response) throws JSONException {

        List<Marca> listMarcas = new ArrayList<>();

        JSONArray jsonA = new JSONArray(response);
        for (int i = 0; i < jsonA.length(); i++) {

            JSONObject jsonO = jsonA.getJSONObject(i);
            Marca marca = JsonToMarcas(jsonO);

            listMarcas.add(marca);

        }

        return listMarcas;
    }

    private static Marca JsonToMarcas(JSONObject jsonO) throws JSONException {

        Integer id_user = jsonO.getInt("id_marca");
        String nombre_marca = jsonO.getString("nombre_marca");
        int visible = jsonO.getInt("visible");

        Marca marca = new Marca(id_user,nombre_marca,visible);
        return marca;
    }

}
