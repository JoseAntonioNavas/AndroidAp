package controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.nono.concesionariocoches.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;

import logic.VariablesGlobales;

public class imgProfile extends AppCompatActivity {

    private EditText txtFilename;

    private ImageView imgFoto;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_profile);

        txtFilename = findViewById(R.id.filename);
        imgFoto = findViewById(R.id.imgFoto);

        imgFoto.setOnClickListener(v ->
                selectFromGallery()
        );

        //findViewById(R.id.btnSelectFromGallery).setOnClickListener(v->selectFromGallery());
        findViewById(R.id.btnDownload).setOnClickListener(v->downLoad());
        findViewById(R.id.btnUpload).setOnClickListener(v->upLoad());


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

    private void downLoad() {

        String url="https://www.40defiebre.com/wp-content/uploads/2015/10/imagenes.png";
        //imgFoto.setImageDrawable();
        Glide
                .with(getApplicationContext())
                .load(url)
                .apply(RequestOptions.centerCropTransform())

                .into(imgFoto);

        Toast.makeText(getApplicationContext(), "imagen descargada", Toast.LENGTH_SHORT).show();
    }


    private void upLoad() {
        if(txtFilename.getText().toString().length() ==0 ){
            Toast.makeText(getApplicationContext(),"falta el nombre del archivo", Toast.LENGTH_SHORT).show();
            return ;
        }
        String strUrl = VariablesGlobales.url+"/api/photo";
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo", "Espere por favor...", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl,
                s-> {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Subida con éxito", Toast.LENGTH_SHORT).show();

                },
                volleyError -> {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println( volleyError.getMessage());
                }
        ){


            @Override
            public Map<String,String> getParams(){
                Hashtable<String,String> params = new Hashtable<String,String>();
                params.put("imgData", getStringImage(bitmap));
                params.put("imgName",txtFilename.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("////////////////////////////////////////////////////////////////////////////////// "+ bmp);
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  imagesBytes = baos.toByteArray();
        return Base64.encodeToString(imagesBytes, Base64.DEFAULT);

    }

}