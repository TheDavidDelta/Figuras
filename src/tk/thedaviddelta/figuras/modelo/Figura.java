package tk.thedaviddelta.figuras.modelo;

import javafx.scene.paint.Color;

public abstract class Figura {
    protected Color color;
    protected Punto punto;

    public Figura(Punto punto) {
        this.punto = punto;
        this.color = Color.BLACK;
    }

    public Figura(Punto punto, Color color) {
        this.punto = punto;
        this.color = color;
    }
    
    public abstract double area();
    
    public abstract double perimetro();
    
    public boolean menorQue(Figura f){
        return this.area() < f.area();
    }
    
    @Override
    public String toString(){
        return String.format("Centro X: %d\nCentro Y: %d\nColor: %s", this.punto.getX(), this.punto.getY(), this.color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }
    
}
