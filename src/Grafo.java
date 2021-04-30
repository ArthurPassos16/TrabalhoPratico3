import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Arthur Passos
 */
public class Grafo implements Comparable<Vertice>{
    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;
    
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> adjacencias;
    private ArrayList<Vertice> distancias;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.distancias = new ArrayList<Vertice>();
        this.adjacencias = new ArrayList<Vertice>();
    }

    public void adicionarVertice(int dado) {
        Vertice vertice = new Vertice(dado);
        this.vertices.add(vertice); 
    }
    
    public void adicionarAresta(int dadoInicio, int dadoFim, int peso){
        Vertice inicio = this.getVertice(dadoInicio);
        Vertice fim = this.getVertice(dadoFim);
        
        Aresta aresta = new Aresta(inicio,fim, peso);
         
        inicio.adicionarAresta(aresta);
        fim.adicionarAresta(aresta);
        
        inicio.adicionarAdjacencia(fim);
        fim.adicionarAdjacencia(inicio);
        
        this.arestas.add(aresta);
    }

    public Vertice getVertice (int dado) {
        Vertice vertice = null;
        for (Vertice aux : this.vertices) {
            if (aux.getDado() == dado){
                vertice = aux;
                break;
            }
        }
        return vertice;
    }
    
    public Vertice oposto(Vertice v, Aresta e){
        // retorna o ponto final da aresta e diferente de v.
        for (Aresta edge : v.getArestas()) {
            if (edge.equals(e)) {
                if (v.equals(edge.getInicio())) {
                        return edge.getFim();
                } else {
                        return edge.getInicio();
                }
            }
        }
        return null;
    }
    
    // A recursive function that finds and prints bridges
    // using DFS traversal
    // u --> The vertex to be visited next
    // visited[] --> keeps tract of visited vertices
    // disc[] --> Stores discovery times of visited vertices
    // parent[] --> Stores parent vertices in DFS tree
    void bridgeUtil(Vertice u, boolean visited[], int disc[], int low[], int parent[]) {
  
        // Mark the current node as visited
        visited[u.getDado()-1] = true;
  
        // Initialize discovery time and low value
        disc[u.getDado()-1] = low[u.getDado()-1] = ++time;
  
        // Go through all vertices adjacent to this
        Iterator<Vertice> i = u.getAdjacencias().iterator();
        while (i.hasNext())
        {
            Vertice v = i.next();  // v is current adjacent of u
  
            // If v is not visited yet, then make it a child
            // of u in DFS tree and recur for it.
            // If v is not visited yet, then recur for it
            if (!visited[v.getDado()-1])
            {
                parent[v.getDado()-1] = u.getDado();
                bridgeUtil(v, visited, disc, low, parent);
  
                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[u.getDado()-1]  = Math.min(low[u.getDado()-1], low[v.getDado()-1]);
  
                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low[v.getDado()-1] > disc[u.getDado()-1])
                    System.out.println("Ponte: "+u.getDado()+" "+v.getDado());
            }
  
            // Update low value of u for parent function calls.
            else if (v.getDado() != parent[u.getDado()-1])
                low[u.getDado()-1]  = Math.min(low[u.getDado()-1], disc[v.getDado()-1]);
        }
    }
    
    // DFS based function to find all bridges. It uses recursive
    // function bridgeUtil()
    void bridge()
    {
        // Mark all the vertices as not visited
        int tam = this.getVertices().size();
        boolean visited[] = new boolean[tam];
        int disc[] = new int[tam];
        int low[] = new int[tam];
        int parent[] = new int[tam];
  
  
        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < tam; i++)
        {
            parent[i] = NIL;
            visited[i] = false;
        }
  
        // Call the recursive helper function to find Bridges
        // in DFS tree rooted with vertex 'i'
        for (int i = 0; i < tam; i++)
            if (visited[i] == false)
                bridgeUtil(this.getVertices().get(i+1), visited, disc, low, parent);
    }
    
    public void dijkstra(Vertice s){
        s.setDistancia(0);
        
        LinkedList<Vertice> queue = new LinkedList<Vertice>();
        
        queue.add(s);
        for(Vertice v : this.getVertices()){
            if(!s.equals(v))    
                queue.add(v);
        }

        while (!queue.isEmpty()) {
            Vertice u = queue.poll();

            for (Aresta e : u.getArestas()) {
                Vertice v = oposto(u, e);
                int distanciaEntre = u.getDistancia() + e.getPeso();
                if (distanciaEntre < v.getDistancia()) {
                    queue.remove(v);
                    v.setDistancia(distanciaEntre);
                    v.setAnterior(u);
                    queue.add(v);
                }
            }
        }
    }
    
    public void dijkstraModificado(Vertice s, Vertice t){
        s.setDistancia(0);
        
        LinkedList<Vertice> queue = new LinkedList<Vertice>();
        
        queue.add(s);
        for(Vertice v : this.getVertices()){
            if(!s.equals(v))    
                queue.add(v);
        }

        while (!queue.isEmpty()) {
            Vertice u = queue.poll();

            for (Aresta e : u.getArestas()) {
                Vertice v = oposto(u, e);
                int distanciaEntre = u.getDistancia() + e.getPeso();
                if (distanciaEntre < v.getDistancia()) {
                    queue.remove(v);
                    v.setDistancia(distanciaEntre);
                    v.setAnterior(u);
                    queue.add(v);
                }
            }
        }
    }
    
    public void diametro(){
        Vertice vertice;
        Collections.sort(this.getVertices());
        
        List<Vertice> path = getMenorCaminho(this.getVertices().get(0));
        System.out.println("Diametro: "+this.getVertices().get(0).getDistancia());
        System.out.print("Caminho: ");
        for (int x = 0; x < path.size(); x++) {
                vertice = path.get(x);
                System.out.print(vertice.getDado());
                if (x < path.size() - 1) {
                        System.out.print(" -> ");
                }
        }
        System.out.println();
    }
    
    public void menorCaminho(){
        Vertice vertice;
        Collections.sort(this.getVertices());
        
        for(Vertice v : this.getVertices()){
            List<Vertice> path = getMenorCaminho(v);
            System.out.print("Vertice: "+v.getDado()+" Caminho: ");
            for (int x = 0; x < path.size(); x++) {
                    vertice = path.get(x);
                    System.out.print(vertice.getDado());
                    if (x < path.size() - 1) {
                            System.out.print(" -> ");
                    }
            }
            System.out.println();
        }
    }
    
    public static List<Vertice> getMenorCaminho(Vertice target) {
        List<Vertice> path = new ArrayList<Vertice>();
        for (Vertice vertice = target; vertice != null; vertice = vertice.getAnterior()) {
                path.add(vertice);
        }
        Collections.reverse(path);
        return path;
    }
    
    public ArrayList<Vertice> getVertices() {
        return this.vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return this.arestas;
    }

    @Override
    public int compareTo(Vertice t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
