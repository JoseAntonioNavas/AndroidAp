package model;

public class Vehiculo {

    private int id_vehiculo;
    private Marca marca;
    private Modelo modelo;
    private String matricula;
    private model.Color color;


    public Vehiculo(int id_vehiculo, Marca marca, Modelo modelo, String matricula, Color color) {
        this.id_vehiculo = id_vehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.color = color;
    }
    public Vehiculo(){

    }
    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id_vehiculo=" + id_vehiculo +
                ", marca=" + marca +
                ", modelo=" + modelo +
                ", matricula='" + matricula + '\'' +
                ", color=" + color +
                '}';
    }
}
