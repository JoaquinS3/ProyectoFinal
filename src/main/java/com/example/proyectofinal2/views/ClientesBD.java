package com.example.proyectofinal2.views;


import com.example.proyectofinal2.modelos.TacosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientesBD extends Stage {

    private Scene escena;
    private TableView<TacosDAO> tbvClientes;
    private Button btnAgregar;
    private VBox vBox;
    private TacosDAO cteDAO;

    public ClientesBD(){
        cteDAO = new TacosDAO();
        CrearUI();
        this.setTitle("Clientes Taqueria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        tbvClientes = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregra Cliente");
        btnAgregar.setOnAction(event ->{


        } );
        vBox = new VBox();
        vBox.getChildren().addAll(tbvClientes,btnAgregar);
        escena = new Scene(vBox,700,250);
    }

    private void  CrearTabla(){
        TableColumn<TacosDAO,Integer> tbcIdCliente = new TableColumn<>("ID");
        tbcIdCliente.setCellValueFactory(new PropertyValueFactory<>("cvecte"));

        TableColumn<TacosDAO,String> tbcNomCliente= new TableColumn<>("Nombre");
        tbcNomCliente.setCellValueFactory(new PropertyValueFactory<>("nomcte"));

        TableColumn<TacosDAO,String> tbcTelCliente = new TableColumn<>("Telefono");
        tbcTelCliente.setCellValueFactory(new PropertyValueFactory<>("telcte"));

        TableColumn<TacosDAO,String> tbcDirCliente = new TableColumn<>("Dirección");
        tbcDirCliente.setCellValueFactory(new PropertyValueFactory<>("dircte"));

        tbvClientes.getColumns().addAll(tbcIdCliente, tbcNomCliente,tbcTelCliente,tbcDirCliente);
        tbvClientes.setItems(cteDAO.SELECCIONAR());
    }
}

