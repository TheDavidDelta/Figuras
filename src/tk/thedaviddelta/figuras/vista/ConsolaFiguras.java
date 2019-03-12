package tk.thedaviddelta.figuras.vista;

import java.util.Scanner;
import javafx.scene.paint.Color;
import tk.thedaviddelta.figuras.control.*;
import tk.thedaviddelta.figuras.modelo.*;

public class ConsolaFiguras {
    static Scanner tec = new Scanner(System.in);
    static GestionFiguras controlador = new GestionFiguras();
    
    public static void main(String[] args) {
        int op;
        do {
            System.out.println("1.- Añadir rectángulo\n2.- Añadir círculo\n3.- Listar\n4.- Salir");
            op = Integer.parseInt(tec.nextLine());
            if (op < 1 || op > 4) 
                System.out.println("Opción no válida");
            else
                menu(op);
            System.out.println();
        } while (op != 4);
    }
    
    private static void menu(int op){
        switch (op) {
            case 1: // rectángulo
                System.out.println("Introduce los datos del rectángulo:");
                addRectangulo();
                break;
            case 2: // círculo
                System.out.println("Introduce los datos del disco:");
                addCirculo();
                break;
            case 3: // listar
                if (controlador.listar().equals("")) 
                    System.out.println("La lista está vacía");
                else
                    System.out.println(controlador.listar());
                break;
            case 4: // salir
                System.out.println("Se saldrá del programa");
                break;
        }
    }
    
    private static void addRectangulo(){
        try{
            System.out.print("Base: ");
            double base = Double.parseDouble(tec.nextLine());
            System.out.print("Altura: ");
            double altura = Double.parseDouble(tec.nextLine());
            System.out.print("Centro X: ");
            int x = Integer.parseInt(tec.nextLine());
            System.out.print("Centro Y: ");
            int y = Integer.parseInt(tec.nextLine());
            try{
                System.out.print("Color: ");
                Color color = Color.valueOf(tec.nextLine());
                controlador.insertar(new Rectangulo(new Punto(x, y), color, base, altura));
            } catch (IllegalArgumentException e) {
                controlador.insertar(new Rectangulo(new Punto(x, y), base, altura));
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al añadir el rectángulo:\nTodos los valores menos el color deben ser números");
        }
    }
    
    private static void addCirculo(){
        try{
            System.out.print("Radio: ");
            double altura = Double.parseDouble(tec.nextLine());
            System.out.print("Centro X: ");
            int x = Integer.parseInt(tec.nextLine());
            System.out.print("Centro Y: ");
            int y = Integer.parseInt(tec.nextLine());
            try{
                System.out.print("Color: ");
                Color color = Color.valueOf(tec.nextLine());
                controlador.insertar(new Circulo(new Punto(x, y), color, altura));
            } catch (IllegalArgumentException e) {
                controlador.insertar(new Circulo(new Punto(x, y), altura));
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al añadir el círculo:\nTodos los valores menos el color deben ser números");
        }
    }
}
