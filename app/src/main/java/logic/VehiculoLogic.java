package logic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Color;
import model.Marca;
import model.Modelo;
import model.Vehiculo;

public class VehiculoLogic {


    public static List<Vehiculo> JsonToVehiculo(String response) throws JSONException {

        List<Vehiculo> listVehiculos = new ArrayList<>();

        JSONArray jsonA = new JSONArray(response);
        for (int i = 0; i < jsonA.length(); i++) {

            JSONObject jsonO = jsonA.getJSONObject(i);
            Vehiculo v = JsonToVehiculos(jsonO);

            listVehiculos.add(v);

        }

        return listVehiculos;
    }

    private static Vehiculo JsonToVehiculos(JSONObject jsonO) throws JSONException {


        int id_vehiculo = jsonO.getInt("id_vehiculo");
        String matricula = jsonO.getString("matricula");

        int id_marca = jsonO.getJSONObject("marca").getInt("id_marca");
        String nombre_marca = jsonO.getJSONObject("marca").getString("nombre_marca");

        int id_modelo = jsonO.getJSONObject("modelo").getInt("id_modelo");
        String nombre_modelo = jsonO.getJSONObject("modelo").getString("nombre_modelo");
        int potencia = jsonO.getJSONObject("modelo").getInt("potencia");
        float precio = Float.parseFloat(jsonO.getJSONObject("modelo").getString("precio"));

        int id_color = jsonO.getJSONObject("color").getInt("id_color");
        String nombre_color = jsonO.getJSONObject("color").getString("nombre_color");
        String rgbcolor = jsonO.getJSONObject("color").getString("rgbcolor");

        String fileData = jsonO.getString("fileData");

        return new Vehiculo(id_vehiculo,new Marca(id_marca,nombre_marca),new Modelo(id_modelo,nombre_modelo,potencia,precio),matricula,new Color(id_color,nombre_color,rgbcolor),fileData);

    }

}
