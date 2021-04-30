
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Arthur Passos
 */
public class Main {

    public static void main(String[] args){
        int tam, vertice1, vertice2;
        double peso;
        
        Vertice vertice;
        Grafo grafo = new Grafo();
        
        for(int i=0;i<5;i++){
            grafo.adicionarVertice(i+1);
        } 

        grafo.adicionarAresta(1, 2, 1);
        grafo.adicionarAresta(2, 5, 2);
        grafo.adicionarAresta(5, 3, 5);
        grafo.adicionarAresta(3, 4, 3);
        grafo.adicionarAresta(1, 5, 1);
        
        grafo.dijkstra(grafo.getVertice(1));
        grafo.diametro();
        
        grafo.bridge();
        
        //grafo.dijkstraModificado(grafo.getVertice(1), grafo.getVertice(3));
        //grafo.menorCaminho();
    }
}
