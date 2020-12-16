package Clases.tsp.ACO;
public class Ciudad {
 //Atributos
    private static final double RADIO_TIERRA_ECUATORIAL = 6378.1370D;
    private static final double CONVERTIR_GRADO_A_RADIANES = Math.PI / 180D;
    private static final double CONVERTIR_KM_A_MILLAS = 0.621371;
    private double longitud;
    private double latitud;
    private String nombre;

    //Constructor
    public Ciudad() {
    }

    //Métodos 
    public String ObtenerNombre() {
        return nombre;
    }

    public Ciudad(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.longitud = longitud * CONVERTIR_GRADO_A_RADIANES;
        this.latitud = latitud * CONVERTIR_GRADO_A_RADIANES;
    }

    public double medirDistancia(Ciudad ciudad) {
        double deltaLongitud = (ciudad.ObtenerLongitud() - this.ObtenerLongitud());
        double deltaLatitud = (ciudad.ObtenerLatitud() - this.ObtenerLatitud());
        double resultado = Math.pow(Math.sin(deltaLatitud / 2D), 2D) + Math.cos(this.ObtenerLatitud()) * Math.cos(ciudad.ObtenerLatitud()) * Math.pow(Math.sin(deltaLongitud / 2D), 2D);
        return CONVERTIR_KM_A_MILLAS*RADIO_TIERRA_ECUATORIAL * 2D * Math.atan2(Math.sqrt(resultado), Math.sqrt(1D - resultado));
    }

    public double ObtenerLatitud() {
        return this.latitud;
    }

    public double ObtenerLongitud() {
        return this.longitud;
    }

   
    public String toString() {
        return nombre;
    }
}
