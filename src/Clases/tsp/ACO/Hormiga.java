package Clases.tsp.ACO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Hormiga implements Callable<Hormiga> {

    //Atributos
    public static final double Q = 0.0005;     //Parámetro utilizado para ajustar la cantidad de feromona depositada. el valor está entre 0 y 1
    public static final double RHO = 0.2;     //Parámetro utilizado para variar el nivel de evaporación de fermona. el valor está entre 0 y 1
    public static final double ALPHA = 0.01; //Parámetro utilizado para controlar la importancia del rastro de fermona. el valor es >= 0
    public static final double BETA = 9.5;  //Parámetro utilizado para controlar la importancia de la distancia entre el origen y el destino.
    private HormigaColoniaOptimizacion aco;
    private int numeroHormiga;
    private Ruta ruta = null;
    static int indexCiudadInvalido = -1;
    static int numeroDeCiudades = ACOEnvios.rutaInicial.size();
    public Hormiga() {}

    public Hormiga(HormigaColoniaOptimizacion aco, int numeroHormiga) {
        this.aco = aco;
        this.numeroHormiga = numeroHormiga;
    }
    //Constructor

        
    //Métodos
    public Ruta ObtenerRuta() {
        return ruta;
    }

    

    public Hormiga call() throws Exception {
        int originarIndexCiudad = ThreadLocalRandom.current().nextInt(numeroDeCiudades);
        ArrayList<Ciudad> rutaCiudades = new ArrayList<Ciudad>(numeroDeCiudades);
        HashMap<String, Boolean> ciudadesVisitadas = new HashMap<String, Boolean>(numeroDeCiudades);
        IntStream.range(0, numeroDeCiudades).forEach(x -> ciudadesVisitadas.put(ACOEnvios.rutaInicial.get(x).ObtenerNombre(), false));
        int numeroDeCiudadesVisitadas = 0;
        ciudadesVisitadas.put(ACOEnvios.rutaInicial.get(originarIndexCiudad).ObtenerNombre(), true);
        double rutaDistancia = 0.0;
        int x = originarIndexCiudad;
        int y = indexCiudadInvalido;
        if (numeroDeCiudadesVisitadas != numeroDeCiudades) {
            y = getY(x, ciudadesVisitadas);
        }
        while (y != indexCiudadInvalido) {
            rutaCiudades.add(numeroDeCiudadesVisitadas++, ACOEnvios.rutaInicial.get(x));
            rutaDistancia += aco.getDistancesMatrix()[x][y];
            AjustarNiveldeFeromona(x, y, rutaDistancia);
            ciudadesVisitadas.put(ACOEnvios.rutaInicial.get(y).ObtenerNombre(), true);
            x = y;
            if (numeroDeCiudadesVisitadas != numeroDeCiudades) {
                y = getY(x, ciudadesVisitadas);
            } else {
                y = indexCiudadInvalido;
            }
        }
        rutaDistancia += aco.getDistancesMatrix()[x][originarIndexCiudad];
        rutaCiudades.add(numeroDeCiudadesVisitadas, ACOEnvios.rutaInicial.get(x));
        ruta = new Ruta(rutaCiudades, rutaDistancia);
        return this;
    }

    private void AjustarNiveldeFeromona(int x, int y, double distancia) {
        boolean flag = false;
        while (!flag) {
            double NivelActualdeFeromona = aco.getMatrizNivelFeromona()[x][y].doubleValue();
            double ActualizarNiveldeFeromona = (1 - RHO) * NivelActualdeFeromona + Q / distancia;
            if (ActualizarNiveldeFeromona < 0.00) {
                flag = aco.getMatrizNivelFeromona()[x][y].compareAndSet(0);
            } else {
                flag = aco.getMatrizNivelFeromona()[x][y].compareAndSet(ActualizarNiveldeFeromona);
            }
        }
    }

    private int getY(int x, HashMap<String, Boolean> CiudadesVisitadas) {
        int returnY = indexCiudadInvalido;
        double random = ThreadLocalRandom.current().nextDouble();
        ArrayList<Double> ProbabilidadDeTransicion = getProbabilidadDeTransicion(x, CiudadesVisitadas);
        for (int y = 0; y < numeroDeCiudades; y++) {
            if (ProbabilidadDeTransicion.get(y) > random) {
                returnY = y;
                break;
            } else {
                random -= ProbabilidadDeTransicion.get(y);
            }
        }
        return returnY;
    }

    private ArrayList<Double> getProbabilidadDeTransicion(int x, HashMap<String, Boolean> CiudadesVisitadas) {
        ArrayList<Double> ProbabilidadDeTransicion = new ArrayList<Double>(numeroDeCiudades);
        IntStream.range(0, numeroDeCiudades).forEach(i -> ProbabilidadDeTransicion.add(0.0));
        double denominator = getTPDenominador(ProbabilidadDeTransicion, x, CiudadesVisitadas);
        IntStream.range(0, numeroDeCiudades).forEach(y -> ProbabilidadDeTransicion.set(y, ProbabilidadDeTransicion.get(y) / denominator));
        return ProbabilidadDeTransicion;
    }

    private double getTPDenominador(ArrayList<Double> ProbabilidadDeTransicion, int x, HashMap<String, Boolean> CiudadesVisitadas) {
        double denominador = 0.0;
        for (int y = 0; y < numeroDeCiudades; y++) {
            if (!CiudadesVisitadas.get(ACOEnvios.rutaInicial.get(y).ObtenerNombre())) {
                if (x == y) {
                    ProbabilidadDeTransicion.set(y, 0.0);
                } else {
                    ProbabilidadDeTransicion.set(y, getTPNumerador(x, y));
                }
                denominador += ProbabilidadDeTransicion.get(y);
            }
        }
        return denominador;
    }

    private double getTPNumerador(int x, int y) {
        double numerador = 0.0;
        double NiveldeFeromona = aco.getMatrizNivelFeromona()[y][x].doubleValue();
        if (NiveldeFeromona != 0.0) {
            numerador = Math.pow(NiveldeFeromona, ALPHA) * Math.pow(1 / aco.getDistancesMatrix()[x][y], BETA);
        }
        return numerador;
    }

    public int getNumeroHormiga() {
        return numeroHormiga;
    }
}
