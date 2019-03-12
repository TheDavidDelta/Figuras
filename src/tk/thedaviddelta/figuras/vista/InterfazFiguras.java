package tk.thedaviddelta.figuras.vista;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import tk.thedaviddelta.figuras.control.*;
import tk.thedaviddelta.figuras.modelo.*;

public class InterfazFiguras extends Application {
    
    private static GestionFiguras controlador = new GestionFiguras();
    private static Stage primaryStage;
    private static Stage lienzoStage = new Stage();
    private static Canvas canvas = new Canvas();
    
    @Override
    public void start(Stage primStage) {
        primaryStage = primStage;
        
        //primaryStage.setTitle("Menú principal");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(InterfazFiguras.class.getResourceAsStream("IconoFiguras.png")));
        
        mainScene();
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void mainScene(){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        
        Scene scene = new Scene(vb, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menú principal");
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        
        //primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image(InterfazFiguras.class.getResourceAsStream("IconoFiguras.png")));
        //primaryStage.show();
        
        Text welcome = new Text("Bienvenido");
        welcome.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 46));
        welcome.setTextAlignment(TextAlignment.CENTER);
        vb.getChildren().add(welcome);
        
        Region r = new Region();
        vb.getChildren().add(r);

        Button[] op = new Button[5];
        
        op[0] = new Button("Añadir rectangulo");
        op[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addRectangulo();
            }
        });
        
        op[1] = new Button("Añadir círculo");
        op[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addCirculo();
            }
        });
        
        op[2] = new Button("Ver en el plano");
        op[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plano();
            }
        });
        
        op[3] = new Button("Listar figuras");
        op[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listar();
            }
        });
        
        op[4] = new Button("Salir");
        op[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.exit(0);
                Platform.exit();
            }
        });
        
        for (Button option : op) {
            option.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 24));
            vb.getChildren().add(option);
        }
    }
    
    private static void addRectangulo(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(25);
        
        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setTitle("Añadiendo rectángulo");
        primaryStage.setScene(scene);
        
        Text txt = new Text("Introduce los datos del rectángulo:");
        txt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.add(txt, 0, 0, 2, 1);
        
        Label[] labs = new Label[5];
        TextField[] texts = new TextField[5];
        
        labs[0] = new Label("\tBase");
        texts[0] = new TextField();
        
        labs[1] = new Label("Altura");
        texts[1] = new TextField();
        
        labs[2] = new Label("Centro X");
        texts[2] = new TextField();
        
        labs[3] = new Label("Centro Y");
        texts[3] = new TextField();
        
        labs[4] = new Label("Color (opcional)");
        texts[4] = new TextField();
        
        for (int i = 0; i < labs.length; i++) {
            labs[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
            labs[i].setAlignment(Pos.CENTER_RIGHT);
            labs[i].setMaxWidth(250);
            grid.add(labs[i], 0, i+2);
            texts[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
            texts[i].setAlignment(Pos.CENTER);
            texts[i].setMaxWidth(250);
            grid.add(texts[i], 1, i+2);
        }
        
        Text target = new Text();
        target.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
        grid.add(target, 1, 7);
        
        HBox hbBtn = new HBox();
        hbBtn.setSpacing(30);
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(back);
        Button enter = new Button("Crear");
        enter.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 8);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene();
            }
        });
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    try{
                        Color color = Color.valueOf(texts[4].getText());
                        controlador.insertar(new Rectangulo(new Punto(Integer.parseInt(texts[2].getText()), Integer.parseInt(texts[3].getText())), color, Double.parseDouble(texts[0].getText()), Double.parseDouble(texts[1].getText())));
                    } catch (IllegalArgumentException e) {
                        controlador.insertar(new Rectangulo(new Punto(Integer.parseInt(texts[2].getText()), Integer.parseInt(texts[3].getText())), Double.parseDouble(texts[0].getText()), Double.parseDouble(texts[1].getText())));
                    }
                    target.setFill(Color.GREEN);
                    target.setText("Rectángulo añadido correctamente");
                    lienzo();
                } catch (NumberFormatException e) {
                    target.setFill(Color.FIREBRICK);
                    target.setText("Todos los valores menos el color deben ser números");
                } 
            }
        });
    }
    
    private static void addCirculo(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(25);
        
        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setTitle("Añadiendo círculo");
        primaryStage.setScene(scene);
        
        Text txt = new Text("Introduce los datos del círculo:");
        txt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        grid.add(txt, 0, 0, 2, 1);
        
        Label[] labs = new Label[4];
        TextField[] texts = new TextField[4];
        
        labs[0] = new Label("\tRadio");
        texts[0] = new TextField();
        
        labs[1] = new Label("Centro X");
        texts[1] = new TextField();
        
        labs[2] = new Label("Centro Y");
        texts[2] = new TextField();
        
        labs[3] = new Label("Color (opcional)");
        texts[3] = new TextField();
        
        for (int i = 0; i < labs.length; i++) {
            labs[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
            labs[i].setAlignment(Pos.CENTER_RIGHT);
            labs[i].setMaxWidth(250);
            grid.add(labs[i], 0, i+2);
            texts[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
            texts[i].setAlignment(Pos.CENTER);
            texts[i].setMaxWidth(250);
            grid.add(texts[i], 1, i+2);
        }
        
        Text target = new Text();
        target.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
        grid.add(target, 1, 6);
        
        HBox hbBtn = new HBox();
        hbBtn.setSpacing(30);
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(back);
        Button enter = new Button("Crear");
        enter.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 7);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene();
            }
        });
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    try{
                        Color color = Color.valueOf(texts[3].getText());
                        controlador.insertar(new Circulo(new Punto(Integer.parseInt(texts[1].getText()), Integer.parseInt(texts[2].getText())), color, Double.parseDouble(texts[0].getText())));
                    } catch (IllegalArgumentException e) {
                        controlador.insertar(new Circulo(new Punto(Integer.parseInt(texts[1].getText()), Integer.parseInt(texts[2].getText())), Double.parseDouble(texts[0].getText())));
                    }
                    target.setFill(Color.GREEN);
                    target.setText("Círculo añadido correctamente");
                    lienzo();
                } catch (NumberFormatException e) {
                    target.setFill(Color.FIREBRICK);
                    target.setText("Todos los valores menos el color deben ser números");
                } 
            }
        });
    }
    
    private static void listar(){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.setPadding(new Insets(25, 75, 25, 75));
        
        Scene scene = new Scene(vb, 640, 480);
        primaryStage.setTitle("Lista de figuras");
        primaryStage.setScene(scene);
        
        VBox vbPane = new VBox();
        int size = controlador.getLista().size();
        
        ArrayList<Text> target = new ArrayList<>();
        target.add(new Text());
        target.get(0).setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        target.get(0).setTextAlignment(TextAlignment.CENTER);
        vbPane.getChildren().add(target.get(0));
        
        Text slash;
        for (int i = 1; i < size; i++) {
            slash = new Text("---------------------");
            slash.setFill(Color.GREY);
            slash.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
            slash.setTextAlignment(TextAlignment.CENTER);
            vbPane.getChildren().add(slash);
            
            target.add(new Text());
            target.get(i).setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
            target.get(i).setTextAlignment(TextAlignment.CENTER);
            vbPane.getChildren().add(target.get(i));
        }
        
        if (size == 0) {
            target.get(0).setFill(Color.FIREBRICK);
            target.get(0).setText("\nLa lista está vacía\n");
        }else{
            for (int i = 0; i < size; i++) {
                target.get(i).setFill(controlador.getLista().get(i).getColor());
                target.get(i).setText(controlador.getLista().get(i).toString());
            }
        }
        vbPane.setAlignment(Pos.CENTER);
        
        ScrollPane sp = new ScrollPane(vbPane);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        //sp.setPadding(new Insets(1, 100, 1, 150));
        vb.getChildren().add(sp);
        
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        vb.getChildren().add(back);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene();
            }
        });
    }
    
    private static void plano(){
        lienzoStage.close();
        
        canvas.setWidth(800);
        canvas.setHeight(800);
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(pane, canvas.getWidth(), canvas.getHeight());
        
        lienzoStage.setTitle("Plano");
        lienzoStage.setScene(scene);
        //lienzoStage.setResizable(false);
        lienzoStage.getIcons().add(new Image(InterfazFiguras.class.getResourceAsStream("IconoFiguras.png")));
        lienzoStage.show();
        
        lienzo();
        
        lienzoStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            Double newWidth = canvas.getWidth() + ((Double)(newVal) - (Double)(oldVal));
            canvas.setWidth(newWidth);
            lienzo();
        });

        lienzoStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            Double newHeight = canvas.getHeight() + ((Double)(newVal) - (Double)(oldVal));
            canvas.setHeight(newHeight);
            lienzo();
        });
    }
    
    private static void lienzo(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeLine(canvas.getWidth() / 2, 10, canvas.getWidth() / 2, canvas.getHeight()-10);
        gc.strokeLine(10, canvas.getHeight() / 2, canvas.getWidth()-10, canvas.getHeight() / 2);
        
        gc.setLineWidth(2);
        for (int i = 0; i < controlador.getLista().size(); i++) {
            if (controlador.getLista().get(i) instanceof Rectangulo) {
                Rectangulo r = (Rectangulo)controlador.getLista().get(i);
                gc.setStroke(r.getColor());
                //gc2.strokeRect(400 + r.getPunto().getX() - (r.getBase()/2), 400 + (- r.getPunto().getY()) - (r.getAltura()/2), r.getBase(), r.getAltura());
                gc.strokeRect(canvas.getWidth() / 2 + (r.getPunto().getX() - (r.getBase()/2)) * 10, canvas.getHeight() / 2 + (- r.getPunto().getY() - (r.getAltura()/2)) *10, r.getBase() * 10, r.getAltura() * 10);
            }
            if (controlador.getLista().get(i) instanceof Circulo) {
                Circulo c = (Circulo)controlador.getLista().get(i);
                gc.setStroke(c.getColor());
                //gc2.strokeOval(400 + c.getPunto().getX() - c.getRadio(), 400 + (- c.getPunto().getY()) - c.getRadio(), c.getRadio() * 2, c.getRadio() * 2);
                gc.strokeOval(canvas.getWidth() / 2 + (c.getPunto().getX() - c.getRadio()) * 10, canvas.getHeight() / 2 + (- c.getPunto().getY() - c.getRadio()) * 10, c.getRadio() * 20, c.getRadio() * 20);
            }
        }
    }
    
}
