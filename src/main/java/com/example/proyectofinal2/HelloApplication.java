package com.example.proyectofinal2;

import com.example.proyectofinal2.modelos.Conexion;
import com.example.proyectofinal2.views.ClientesBD;
import com.example.proyectofinal2.views.Codigo;
import com.example.proyectofinal2.views.Loteria;
import com.example.proyectofinal2.views.Taqueria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private VBox vBox;
    private MenuBar mnbMenu;
    private Menu menCompetencia1, menCompetencia2;
    private MenuItem miLoteria, mitBuscaminas, mitParseador, mitClientes;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        mnbMenu =new MenuBar();
        menCompetencia1 = new Menu("Competencia 1");
        miLoteria =  new Menu("Loteria");

        menCompetencia2 = new Menu("Competencia 2");
        mnbMenu.getMenus().addAll(menCompetencia1,menCompetencia2);

        //miLoteria = new Menu("Loteria");
        miLoteria.setOnAction(event -> eventoLoteria(1));
        //mitBuscaminas = new Menu("Buscaminas");
        //mitBuscaminas.setOnAction(event -> eventoLoteria(2));
        mitParseador = new MenuItem("Codigo Morse");
        mitParseador.setOnAction(event -> eventoLoteria(2));
        mitClientes = new MenuItem("Taqueria");
        mitClientes.setOnAction(event -> eventoLoteria(3));

        menCompetencia1.getItems().addAll(miLoteria, mitParseador, mitClientes);

        vBox = new VBox();
        vBox.getChildren().addAll(mnbMenu);

        Scene scene = new Scene(vBox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        Conexion.crearConexion();
    }

    private void eventoLoteria(int opc) {

        switch (opc){
            case 1: new Loteria();
                break;
            case 2: new Codigo();
                break;
            case 3: new Taqueria();
                break;
        }
    }

    public static void main(String[] args) {
        launch();

    }
}