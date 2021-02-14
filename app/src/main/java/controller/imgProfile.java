package controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.nono.concesionariocoches.R;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import logic.PeticionHTTP;
import logic.ResponseLogic;
import logic.VariablesGlobales;
import model.profilePhoto;

public class imgProfile extends AppCompatActivity {

    private EditText txtFilename;

    private static Context context;
    private static ImageView imgFoto;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_profile);

        context = getApplicationContext();
        imgFoto = findViewById(R.id.imgFoto);

        // Descargamos img
        downloadImagen();

        imgFoto.setOnClickListener(v ->
                selectFromGallery()
        );


        findViewById(R.id.btnBorrarFotoPerfil).setOnClickListener(v->{ logic.profilePhotoLogic.dialogConfirm(this); });
        findViewById(R.id.btnGaleria).setOnClickListener(v->selectFromGallery());
        findViewById(R.id.btnUpload).setOnClickListener(v->upLoad());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) try {

            Uri path = data.getData();
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(path), null, null);
            imgFoto.setImageBitmap(bitmap);

        }catch (NullPointerException | FileNotFoundException e){

        }
    }

    private void selectFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione una aplicación"),0);
    }


    private void upLoad() {

        String strUrl = VariablesGlobales.url+"/api/photo";
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo", "Espere por favor...", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl,
                s-> {

                    //subirImagen(new profilePhoto(Integer.parseInt(logic.MainLogic.leerPreferenciasUsuario(imgProfile.context)),getStringImage(bitmap)));
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Subida con éxito", Toast.LENGTH_SHORT).show();

                },
                volleyError -> {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Seleccione una imagen de la galeria", Toast.LENGTH_SHORT).show();
                    System.out.println( volleyError.getMessage());
                }
        ){


            @Override
            public Map<String,String> getParams(){
                Hashtable<String,String> params = new Hashtable<String,String>();
                params.put("fileData", getStringImage(bitmap));
                params.put("id_user",logic.MainLogic.leerPreferenciasUsuario(imgProfile.context));


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  imagesBytes = baos.toByteArray();
        return Base64.encodeToString(imagesBytes, Base64.DEFAULT);

    }


    // PETICIONES
    public static void downloadImagen(){

        String id_user = logic.MainLogic.leerPreferenciasUsuario(imgProfile.context);
        new downloadImagen_AsyncTask().execute(VariablesGlobales.url + "/api/photo/DownloadImageByIdUser/"+id_user);
    }
    private static class downloadImagen_AsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTPGET(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){
            try {
                List<profilePhoto> pf = logic.profilePhotoLogic.JsonToprofilePhoto(result);
                if(pf.size() > 0){
                    String url= VariablesGlobales.url+"/images/"+pf.get(0).getFileData()+".jpg";
                    Glide
                            .with(imgProfile.context)
                            .load(url)
                            .apply(RequestOptions.circleCropTransform())
                            .into(imgFoto);

                }else{

                    imgFoto.setImageResource(R.drawable.notprofile);

                }
            } catch (JSONException e) {

                Toast.makeText(imgProfile.context, R.string.catchError, Toast.LENGTH_LONG).show();
            }


        }

    }

    // Borrar Imagen
    // PETICIONES
    public static void deleteImagen(){
        String id_user = logic.MainLogic.leerPreferenciasUsuario(imgProfile.context);
        new downloadImagen_AsyncTask().execute(VariablesGlobales.url + "/api/photo/DeleteImageByIdUser/"+id_user);
    }
    private static class deleteImagen_AsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String result= null;

            try {
                result =  PeticionHTTP.peticionHTTPGET(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){

            imgFoto.setImageResource(R.drawable.notprofile);


        }

    }

}