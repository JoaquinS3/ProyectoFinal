package com.example.proyectofinal2.views;

import com.example.proyectofinal2.modelos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Taqueria extends Stage {
    private VBox vBox ,vBox2;
    static HBox hBox1, hBox2, hBox3;
    private Button btnTacos, btnBurritos, btnBebidas, btnPostres, btnEspecialidades, btnmas, btnmenos, btnagregar, btnelimar;
    private Label lblcont;
    static Label imgCart;
    private Image imgCarta;
    private Scene escena;
    private File file;
    private ImageView imv;
    private TableView<TacosDAO> producto =new TableView<>();
    private TacosDAO cteTaco =new TacosDAO();
    private BurritosDAO cteBurrito =new BurritosDAO();
    private BebidasDAO cteBebida = new BebidasDAO();
    private PostresDAO ctePostre = new PostresDAO();
    private EspecialidadesDAO cteEspecilidad = new EspecialidadesDAO();
    private TableView<Orden> orden=new TableView<>();
    static ObservableList<Orden> listaC = FXCollections.observableArrayList();

    public int con=0;

    public Taqueria(){
        CrearUI();
        this.setTitle("Taqueria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnTacos = new Button("");
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Taco.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(70);
        imv.setFitHeight(70);
        btnTacos.setGraphic(imv);
        btnTacos.setOnAction(event -> {
            CrearTablaTaco();
        });


        btnBurritos = new Button("");
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Burrito.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(70);
        imv.setFitHeight(70);
        btnBurritos.setGraphic(imv);
        btnBurritos.setOnAction(event -> {
            CrearTablaBurrito();
         });

        btnBebidas = new Button("");
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Bebidas.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(70);
        imv.setFitHeight(70);
        btnBebidas.setGraphic(imv);
         btnBebidas.setOnAction(event -> {
             CrearTablaBebidas();
         });

        btnPostres = new Button("");
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Postres.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(70);
        imv.setFitHeight(70);
        btnPostres.setGraphic(imv);
        btnPostres.setOnAction(event -> {
            CrearTablaPostre();
         });

        btnEspecialidades = new Button("");
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/Especialidades.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(70);
        imv.setFitHeight(70);
        btnEspecialidades.setGraphic(imv);
         btnEspecialidades.setOnAction(event -> {
             CrearTablaEspecialidades();
        });

        hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(btnTacos,btnBurritos,btnBebidas,btnPostres,btnEspecialidades);

        lblcont=new Label("" +con);

        btnmas =new Button();
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/mas.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(30);
        imv.setFitHeight(30);
        btnmas.setGraphic(imv);
        btnmas.setOnAction(event -> {
            con++;
            lblcont.setText(""+con);
        });

        btnmenos =new Button();
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/menos.jpg"  );
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imv.setFitWidth(30);
        imv.setFitHeight(30);
        btnmenos.setGraphic(imv);
        btnmenos.setOnAction(event -> {
            if(con>0){
                con--;
            }
            lblcont.setText(""+con);

        });

        btnagregar =new Button("Agregar");
        btnagregar.setOnAction(event -> {
            TacosDAO taco=producto.getSelectionModel().getSelectedItem();
            Orden orden=new Orden();
            orden.setCantidad(con);
            orden.setNombre(taco.nomplatillo);
            orden.setPrecio(taco.precio*con);
            listaC.add(orden);



        });

        vBox2=new VBox();
        vBox2.setSpacing(5);
        vBox2.getChildren().addAll(btnmas,lblcont,btnmenos,btnagregar);

        hBox2=new HBox();
        hBox2.getChildren().addAll(producto,vBox2);

        btnelimar =new Button("Eliminar");
        btnelimar.setOnAction(event -> {
            if(orden.getSelectionModel().isEmpty()){
                System.out.println("selecciona algo a elimiar");
            }else {
                listaC.remove(orden.getSelectionModel().getSelectedItem());
            }
        });

        hBox3 =new HBox();
        hBox3.getChildren().addAll(orden,btnelimar);

        vBox =new VBox();
        vBox.setSpacing(5);
        CrearTablaTaco();
        CrearTablaOrden();
        vBox.getChildren().addAll(hBox1,hBox2,hBox3);



        escena = new Scene(vBox,570,380);
    }
    private void  CrearTablaTaco(){
        producto.getItems().clear();
        producto.getColumns().clear();

        TableColumn<TacosDAO,Integer> cveplatilloCol = new TableColumn("Id");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Integer>("cveplatillo"));

        TableColumn<TacosDAO,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,String>("nomplatillo"));

        TableColumn<TacosDAO,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Double>("precio"));

        producto.setItems(cteTaco.SELECCIONAR());
        producto.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);
        producto.setMaxSize(500,200);
        producto.setMinSize(250,200);
    }

    private void  CrearTablaBurrito(){
        producto.getItems().clear();
        producto.getColumns().clear();

        TableColumn<TacosDAO,Integer> cveplatilloCol = new TableColumn("Id");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Integer>("cveplatillo"));

        TableColumn<TacosDAO,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,String>("nomplatillo"));

        TableColumn<TacosDAO,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Double>("precio"));

        producto.setItems(cteBurrito.SELECCIONAR());
        producto.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);

    }
    private void  CrearTablaBebidas(){
        producto.getItems().clear();
        producto.getColumns().clear();

        TableColumn<TacosDAO,Integer> cveplatilloCol = new TableColumn("Id");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Integer>("cveplatillo"));

        TableColumn<TacosDAO,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,String>("nomplatillo"));

        TableColumn<TacosDAO,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Double>("precio"));

        producto.setItems(cteBebida.SELECCIONAR());
        producto.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);

    }

    private void  CrearTablaPostre(){
        producto.getItems().clear();
        producto.getColumns().clear();

        TableColumn<TacosDAO,Integer> cveplatilloCol = new TableColumn("Id");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Integer>("cveplatillo"));

        TableColumn<TacosDAO,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,String>("nomplatillo"));

        TableColumn<TacosDAO,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Double>("precio"));

        producto.setItems(ctePostre.SELECCIONAR());
        producto.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);

    }

    private void  CrearTablaEspecialidades(){
        producto.getItems().clear();
        producto.getColumns().clear();

        TableColumn<TacosDAO,Integer> cveplatilloCol = new TableColumn("Id");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Integer>("cveplatillo"));

        TableColumn<TacosDAO,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,String>("nomplatillo"));

        TableColumn<TacosDAO,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<TacosDAO,Double>("precio"));

        producto.setItems(cteEspecilidad.SELECCIONAR());
        producto.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);

    }

    private void  CrearTablaOrden(){
        orden.getItems().clear();
        orden.getColumns().clear();

        TableColumn<Orden,Integer> cveplatilloCol = new TableColumn("Cantidad");
        cveplatilloCol.setCellValueFactory(new PropertyValueFactory<Orden,Integer>("cantidad"));

        TableColumn<Orden,String> nomplatilloCol = new TableColumn("Nombre del Platillo");
        nomplatilloCol.setCellValueFactory(new PropertyValueFactory<Orden,String>("nombre"));

        TableColumn<Orden,Double> precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory<Orden,Double>("precio"));

        orden.setItems(listaC);
        orden.getColumns().addAll(cveplatilloCol, nomplatilloCol,precioCol);
        orden.setMaxSize(500,200);
        orden.setMinSize(250,200);

    }
}
