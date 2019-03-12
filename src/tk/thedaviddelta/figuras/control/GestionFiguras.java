package tk.thedaviddelta.figuras.control;

import tk.thedaviddelta.figuras.modelo.*;

public class GestionFiguras {
    private ListaFiguras lista;

    public GestionFiguras() {
        this.lista = new ListaFiguras(10);
    }

    public GestionFiguras(int n) {
        this.lista = new ListaFiguras(n);
    }
    
    public void insertar(Figura f){
        lista.add(f);
    }
    
    public String listar(){
        String s = "";
        for (int i = 0; i < lista.size(); i++) {
            s += lista.get(i);
            if (i < lista.size()-1) 
                s += "\n-------------------\n";
        }
        return s;
    }
    
    public boolean estanEnOrden(){
        int menor = 0;
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(menor).area() > lista.get(i).area()) 
                return false;
            else
                menor = i;
        }
        return true;
    }
    
    public ListaFiguras getLista(){
        return lista;
    }
    
}
