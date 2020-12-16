package Frames.tsp.ACO;
public class coordenada_aux_grafo {
    //Atributos
    private int x, y;
    String letter;
    //Constructor
    public coordenada_aux_grafo(int x, int y, String letter){
        this.x = x;
        this.y = y;
        this.letter = letter;
    }
    //Métodos
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getLetter(){
        return(letter);
    }
    
}
