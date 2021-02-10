package model;

public class Modelo {

    private int id_modelo;
    private String nombre_modelo;
    private Marca marca;
    private int potencia;
    private int visible;
    private float precio;


    public Modelo(int id_modelo, String nombre_modelo, Marca marca, int potencia, int visible, float precio) {
        this.id_modelo = id_modelo;
        this.nombre_modelo = nombre_modelo;
        this.marca = marca;
        this.potencia = potencia;
        this.visible = visible;
        this.precio = precio;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getNombre_modelo() {
        return nombre_modelo;
    }

    public void setNombre_modelo(String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id_modelo=" + id_modelo +
                ", nombre_modelo='" + nombre_modelo + '\'' +
                ", marca=" + marca +
                ", potencia=" + potencia +
                ", visible=" + visible +
                ", precio=" + precio +
                '}';
    }
}
