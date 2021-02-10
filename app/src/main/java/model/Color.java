package model;

public class Color {

    private int id_color;
    private String nombre_color;
    private String rgbcolor;

    public Color(int id_color, String nombre_color, String rgbcolor) {
        this.id_color = id_color;
        this.nombre_color = nombre_color;
        this.rgbcolor = rgbcolor;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }

    public String getRgbcolor() {
        return rgbcolor;
    }

    public void setRgbcolor(String rgbcolor) {
        this.rgbcolor = rgbcolor;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id_color=" + id_color +
                ", nombre_color='" + nombre_color + '\'' +
                ", rgbcolor='" + rgbcolor + '\'' +
                '}';
    }
}
