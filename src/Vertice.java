
import java.util.ArrayList;
/**
 *
 * @author arthu
 */
public class Vertice implements Comparable<Vertice>{
    private int dado;
    private int distancia;
    private Vertice anterior;
    private ArrayList<Vertice> adjacencias;
    private ArrayList<Aresta> arestas;

    public Vertice(int dado) {
        this.dado = dado;
        this.distancia = 9999;
        this.arestas = new ArrayList<Aresta>(); 
        this.adjacencias = new ArrayList<Vertice>();    
        
    }
    
    public void adicionarAresta (Aresta a) {
        this.arestas.add(a);
    }
    
    public void adicionarAdjacencia (Vertice v) {
        this.adjacencias.add(v);
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }
    
    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
    
    public void setAdjacencias(Vertice vertice) {
        this.adjacencias.add(vertice);
    }
    
    public ArrayList<Vertice> getAdjacencias() {
        return adjacencias;
    }
    
    public void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }
    
    public Vertice getAnterior() {
        return anterior;
    }

    @Override
    public int compareTo(Vertice t) {
        if(this.getDistancia()==t.getDistancia())
            return 0;
        else{
            if(this.getDistancia()>t.getDistancia())
                return -11;
            else
                return 1;
        }  
    }
}
