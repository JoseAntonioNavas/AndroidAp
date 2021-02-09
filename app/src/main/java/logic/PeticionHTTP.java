package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PeticionHTTP {
    public static String peticionHTTPGET(String[] params) throws IOException {
        String result= null;

        String uri = params[0];

        URL url = new URL(uri);

        BufferedReader in= new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuffer sb= new StringBuffer("");
        String linea="";
        while ((linea=in.readLine())!= null){
            sb.append(linea);
        }
        in.close();
        result= sb.toString();

        return result;
    }

    public static String peticionHTTP(String[] params, String Method) throws IOException {
        String result= null;

        String uri = params[0];

        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //DEFINIR PARAMETROS DE CONEXION
        urlConnection.setReadTimeout(15000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        urlConnection.setRequestMethod(Method);// se puede cambiar por delete ,put ,etc
        urlConnection.setDoOutput(true);


        //OBTENER EL RESULTADO DEL REQUEST
        if(params.length > 1){ // SI SE LE PASA UN OBJETO
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String parametros = params[1];
            writer.write(parametros);
            writer.flush();
            writer.close();
            os.close();
        }

        int responseCode=urlConnection.getResponseCode();// conexion OK?
        if(responseCode== HttpURLConnection.HTTP_OK){
            BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuffer sb= new StringBuffer("");
            String linea="";
            while ((linea=in.readLine())!= null){
                sb.append(linea);
            }
            in.close();
            result= sb.toString();

        }
        else{
            result= new String("Error: "+ responseCode);
        }

        return result;
    }
}
