package tk.thedaviddelta.figuras.modelo;

public class ListaFiguras {
    private Figura[] lista;
    private int num;

    public ListaFiguras(int n) {
        this.lista = new Figura[n];
    }
    
    public int size(){
        return num;
    }
    
    public boolean add(Figura f){
        if (num >= lista.length) 
            return false;
        lista[num] = f;
        num++;
        return true;
    }
    
    public Figura get(int n){
        if (n >= num)
            return null;
        return lista[n];
    }
    
}
