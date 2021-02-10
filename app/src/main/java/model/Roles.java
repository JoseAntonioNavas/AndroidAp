package model;

public class Roles {

    private Integer id_rol;
    private String nombre_rol;

    public Roles(Integer id_rol, String nombre_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
    }

    public Roles(Integer id_rol) {
        this.id_rol = id_rol;
    }


    public Roles(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }


    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id_rol=" + id_rol +
                ", nombre_rol='" + nombre_rol + '\'' +
                '}';
    }
}
