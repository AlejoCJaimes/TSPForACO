package Clases.tsp.ACO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import Clases.Util.DobleAtomico;
public class HormigaColoniaOptimizacion {
 //Atributos
    
    private DobleAtomico[][] MatrizNivelFeromona = null;
    private double[][] MatrizDistancia = null;
    private ArrayList<Ciudad> ciudades = ACOEnvios.rutaInicial;
    private int TamCiudad = ACOEnvios.rutaInicial.size();
    
    //Public Constructor 

    public HormigaColoniaOptimizacion() throws IOException {
        inicializarDistancias();
        inicializarNivelFeromona();
    }

    public DobleAtomico[][] getMatrizNivelFeromona() {
        return MatrizNivelFeromona;
    }

    public double[][] getDistancesMatrix() {
        return MatrizDistancia;
    }

    private void inicializarDistancias() throws IOException {
        MatrizDistancia = new double[TamCiudad][TamCiudad];
        IntStream.range(0, TamCiudad).forEach(x -> {
            Ciudad CiudadY = ciudades.get(x);
            IntStream.range(0, TamCiudad).forEach(y -> MatrizDistancia[x][y] = CiudadY.medirDistancia(ciudades.get(y)));
        });
    }

    private void inicializarNivelFeromona() {
        MatrizNivelFeromona = new DobleAtomico[TamCiudad][TamCiudad];
        Random random = new Random();
        IntStream.range(0, TamCiudad).forEach(x -> {
            IntStream.range(0, TamCiudad).forEach(y -> MatrizNivelFeromona[x][y] = new DobleAtomico(random.nextDouble()));
        });
    }
}
