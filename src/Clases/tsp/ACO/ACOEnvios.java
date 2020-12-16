package Clases.tsp.ACO;

import Clases.Util.Fecha;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ACOEnvios {
    static final int NUMERO_DE_HORMIGAS = 500;
    static final double PROBABILIDAD_CICLO_PROCESAMIENTO = 0.8;
    static ArrayList<Ciudad> rutaInicial = new ArrayList<Ciudad>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static ExecutorCompletionService<Hormiga> executorCompletionService = new ExecutorCompletionService<Hormiga>(executorService);
    private Ruta RutaMasCorta = null;
    private int HormigasActivas = 0;
    private double distancia = 0.0;
    
    public void setAddArrayList(String name, double latitud, double longitud){
        rutaInicial.add(new Ciudad(name, latitud, longitud));
    }

    public String info() throws IOException {
        String show = "";
        //Load Fecha Example
        
        Fecha objeto = new Fecha();
        show += (objeto.GetCurrentDate()) + "\n";
        show += ("------------------------") + "\n";


        //Load Algoritmo
        show += ("> " + NUMERO_DE_HORMIGAS + " Hormigas Artificiales ...") + "\n";
        System.out.println(PintarCabecera());
        HormigaColoniaOptimizacion aco = new HormigaColoniaOptimizacion();
        IntStream.range(1, NUMERO_DE_HORMIGAS).forEach(x -> {
            executorCompletionService.submit(new Hormiga(aco, x));
            HormigasActivas++;
            if (Math.random() > PROBABILIDAD_CICLO_PROCESAMIENTO) {
                System.out.println(ProcesoDeHormigas());
            }
        });
        show += ProcesoDeHormigas();
        executorService.shutdownNow();
        show += ("\nRuta más Óptima : " + Arrays.toString(RutaMasCorta.ObtenerCiudades().toArray())) + "\n";
        show += ("Peso Mínimo de Ciclo   : " + RutaMasCorta.ObtenerDistancia()) + "\n";      
        distancia = RutaMasCorta.ObtenerDistancia();
        return(show);
    }
    
    public double getDistanciaTotal(){
        return(distancia);
    }
    
    public String[] ClonarRutaCorta() {
        String array = Arrays.toString(RutaMasCorta.ObtenerCiudades().toArray());
        array = array.replace("[", "");
        array = array.replace("]","");
        array = array.replace(" ", "");
        String [] vectAuxiliar = array.split(",");
        return vectAuxiliar;
    }
    
    private String ProcesoDeHormigas() {
        String show = "";
        while (HormigasActivas > 0) {
            try {
                Hormiga hormiga = executorCompletionService.take().get();
                Ruta rutaActual = hormiga.ObtenerRuta();
                if (RutaMasCorta == null || rutaActual.ObtenerDistancia() < RutaMasCorta.ObtenerDistancia()) {
                    RutaMasCorta = rutaActual;
                    StringBuffer distancia = new StringBuffer("       " + String.format("%.2f", rutaActual.ObtenerDistancia()));
                    IntStream.range(0, 21 - distancia.length()).forEach(k -> distancia.append(" "));
                    show += (Arrays.toString(RutaMasCorta.ObtenerCiudades().toArray()) + " |" + distancia + "| " + hormiga.getNumeroHormiga()) + "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            HormigasActivas--;
        }
        return(show);
    }

    private String PintarCabecera() {
        String show = "";
        String headingColumn1 = "Ruta";
        String remainingHeadingColumns = "Distancia (en kms) | # Hormigas";
        int cityNamesLength = 0;
        for (int x = 0; x < rutaInicial.size(); x++) {
            cityNamesLength += rutaInicial.get(x).ObtenerNombre().length();
        }
        int arrayLength = cityNamesLength + rutaInicial.size() * 2;
        int partialLength = (arrayLength - headingColumn1.length()) / 2;
        for (int x = 0; x < partialLength; x++) {
            show += (" ");
        }
        show += (headingColumn1);
        for (int x = 0; x < partialLength; x++) {
            show += (" ");
        }
        if ((arrayLength % 2) == 0) {
            show += (" ");
        }
        show += (" | " + remainingHeadingColumns) +"\n";
        cityNamesLength += remainingHeadingColumns.length() + 3;
        for (int x = 0; x < cityNamesLength + rutaInicial.size() * 2; x++) {
            show += ("-");
        }
        show += ("\n");
        return(show);
    }
}
