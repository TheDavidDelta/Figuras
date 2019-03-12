package tk.thedaviddelta.figuras.modelo;

import javafx.scene.paint.Color;

public class Rectangulo extends Figura {
    private double base;
    private double altura;

    public Rectangulo(Punto punto, double base, double altura) {
        super(punto);
        this.base = base;
        this.altura = altura;
    }

    public Rectangulo(Punto punto, Color color, double base, double altura) {
        super(punto, color);
        this.base = base;
        this.altura = altura;
    }
    
    @Override
    public double area(){
        return base * altura;
    }
    
    @Override
    public double perimetro(){
        return 2 * (base + altura);
    }
    
    @Override
    public String toString(){
        return String.format("Base: %.2f\nAltura: %.2f\n%s", this.base, this.altura, super.toString());
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
    
}
