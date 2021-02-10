package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.nono.concesionariocoches.R;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    EditText txtNick;
    EditText txtNombre;
    EditText txtApellido1;
    EditText txtApellido2;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        context = getApplicationContext();
        txtEmail = findViewById(R.id.txtEmailRegistrar);
        txtPassword = findViewById(R.id.txtPasswordRegistrar);
        txtNick = findViewById(R.id.txtNickRegistrar);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido1 = findViewById(R.id.txtApellido1Registrar);
        txtApellido2 = findViewById(R.id.txtApellido2Registrar);



    }
}