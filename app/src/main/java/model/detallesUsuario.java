package model;

public class detallesUsuario {

    private int id_detalle_usuario;
    private int id_user;
    private String nick_user;
    private int id_rol;
    private String nombre;
    private String apellido_1;
    private String apellido_2;


    public detallesUsuario(int id_detalle_usuario, int id_usuario, String nick_user, int id_rol, String nombre, String apellido_1, String apellido_2) {
        this.id_detalle_usuario = id_detalle_usuario;
        this.id_user = id_usuario;
        this.nick_user = nick_user;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
    }


    public detallesUsuario(int id_usuario, String nick_user, int id_rol, String nombre, String apellido_1, String apellido_2) {
        this.id_user = id_usuario;
        this.nick_user = nick_user;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
    }

    public detallesUsuario( String nick_user, int id_rol, String nombre, String apellido_1, String apellido_2) {

        this.nick_user = nick_user;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
    }




    public int getId_detalle_usuario() {
        return id_detalle_usuario;
    }

    public void setId_detalle_usuario(int id_detalle_usuario) {
        this.id_detalle_usuario = id_detalle_usuario;
    }

    public int getId_usuario() {
        return id_user;
    }

    public void setId_usuario(int id_usuario) {
        this.id_user = id_usuario;
    }

    public String getNick_user() {
        return nick_user;
    }

    public void setNick_user(String nick_user) {
        this.nick_user = nick_user;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_1() {
        return apellido_1;
    }

    public void setApellido_1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido_2() {
        return apellido_2;
    }

    public void setApellido_2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    @Override
    public String toString() {
        return "detallesUsuario{" +
                "id_detalle_usuario=" + id_detalle_usuario +
                ", id_usuario=" + id_user +
                ", nick_user='" + nick_user + '\'' +
                ", id_rol=" + id_rol +
                ", nombre='" + nombre + '\'' +
                ", apellido_1='" + apellido_1 + '\'' +
                ", apellido_2='" + apellido_2 + '\'' +
                '}';
    }
}

