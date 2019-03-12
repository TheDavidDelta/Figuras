package tk.thedaviddelta.figuras.modelo;

import javafx.scene.paint.Color;

public class Circulo extends Figura {
    private double radio;

    public Circulo(Punto punto, double radio) {
        super(punto);
        this.radio = radio;
    }

    public Circulo(Punto punto, Color color, double radio) {
        super(punto, color);
        this.radio = radio;
    }
    
    @Override
    public double area(){
        return Math.PI * Math.pow(radio,2);
    }
    
    @Override
    public double perimetro(){
        return 2 * Math.PI * radio;
    }
    
    @Override
    public String toString(){
        return String.format("Radio: %.2f\n%s", this.radio, super.toString());
    }

    public double getRadio() {
        return radio;
    }
    
}
