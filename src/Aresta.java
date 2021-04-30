public class Aresta {
    private int peso;
    private Vertice inicio;
    private Vertice fim;
    
    public Aresta(Vertice inicio, Vertice fim, int peso){
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }
    
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
