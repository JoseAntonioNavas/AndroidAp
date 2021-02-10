package model;

public class busquedaVehiculo {

    private String busquedaVehiculo;

    public busquedaVehiculo(String busquedaVehiculo) {
        this.busquedaVehiculo = busquedaVehiculo;
    }

    public String getBusquedaVehiculo() {
        return busquedaVehiculo;
    }

    public void setBusquedaVehiculo(String busquedaVehiculo) {
        this.busquedaVehiculo = busquedaVehiculo;
    }

    @Override
    public String toString() {
        return "busquedaVehiculo{" +
                "busquedaVehiculo='" + busquedaVehiculo + '\'' +
                '}';
    }

}
