package Clases.tsp.ACO;

import java.util.ArrayList;
import java.util.Arrays;

public class Ruta {

     //Atributos
    private ArrayList<Ciudad> ciudades;
    private double distancia;

    //Constructor
    public Ruta() {}
    
    //Métodos
    public Ruta(ArrayList<Ciudad> ciudades, double distancia) {
        this.ciudades = ciudades;
        this.distancia = distancia;
    }
    //
    public ArrayList<Ciudad> ObtenerCiudades() {
        return ciudades;
    }

    public double ObtenerDistancia() {
        return distancia;
    }

    public String toString() {
        return Arrays.toString(ciudades.toArray()) + " | " + distancia;
    }
}
