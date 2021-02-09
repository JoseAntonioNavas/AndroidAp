package logic;

import android.widget.EditText;

import model.Usuario;

public class LoginLogic {

    public static Usuario getFields(EditText txtEmail, EditText txtPassword) {
        String email = String.valueOf(txtEmail.getText());
        String password = String.valueOf(txtPassword.getText());

        return  new Usuario(email,password);

    }
}
