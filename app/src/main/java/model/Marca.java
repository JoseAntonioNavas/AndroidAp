package model;

public class Marca {

    private int id_marca;
    private String nombre_marca;
    private int visible;

    public Marca(int id_marca, String nombre_marca, int visible) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
        this.visible = visible;
    }

    public Marca(int id_marca, String nombre_marca) {
        this.id_marca = id_marca;
        this.nombre_marca = nombre_marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "id_marca=" + id_marca +
                ", nombre_marca='" + nombre_marca + '\'' +
                ", visible=" + visible +
                '}';
    }
}
