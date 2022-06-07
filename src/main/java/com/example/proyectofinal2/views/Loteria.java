package com.example.proyectofinal2.views;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

import java.util.Timer;
import java.util.TimerTask;

public class Loteria extends Stage{

    private VBox vBox;
    static HBox hBox1, hBox2;
    private Button btnAtras, btnSiguiente, btnJugar;
    private Label lblTiempo;
    static Label imgCart;
    static GridPane gdpPlantilla;
    private Image imgCarta;
    private Scene escena;
    private File file;

    private String[] mazo ={"1.jpg","2.jpg","3.jpg","4.jpg",
            "5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg",
            "11.jpg","12.jpg","13.jpg","14.jpg", "15.jpg",
            "16.jpg","17.jpg","18.jpg","19.jpg", "20.jpg",
            "21.jpg","22.jpg","23.jpg","24.jpg","25.jpg"};
    //static Button[][] arBtnPlantilla=new Button[4][4];

    public Image imgCardP;
    private ImageView imv;

    private Plantilla plantilla;
    private GridPane p1;
    private String[] p1cartas ={"1.jpg","2.jpg","3.jpg","4.jpg",
            "5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg",
            "11.jpg","12.jpg","13.jpg","14.jpg", "15.jpg",
            "17.jpg"};
    private GridPane p2;
    private String[] p2cartas ={"17.jpg","18.jpg","19.jpg","20.jpg",
            "21.jpg","22.jpg","23.jpg","24.jpg","25.jpg","1.jpg",
            "3.jpg","5.jpg","7.jpg","9.jpg", "11.jpg",
            "13.jpg"};
    private GridPane p3;
    private String[] p3cartas ={"18.jpg","11.jpg","14.jpg","12.jpg",
            "10.jpg","8.jpg","6.jpg","24.jpg","4.jpg","2.jpg",
            "25.jpg","23.jpg","21.jpg","19.jpg", "17.jpg",
            "15.jpg"};
    private GridPane p4;
    private String[] p4cartas ={"20.jpg","22.jpg","24.jpg","1.jpg",
            "2.jpg","3.jpg","5.jpg","8.jpg","13.jpg","25.jpg",
            "14.jpg","15.jpg","17.jpg","19.jpg", "21.jpg",
            "23.jpg"};
    private GridPane p5;
    private String[] p5cartas ={"19.jpg","17.jpg","15.jpg","13.jpg",
            "11.jpg","9.jpg","7.jpg","25.jpg","5.jpg","3.jpg",
            "1.jpg","24.jpg","20.jpg","18.jpg", "14.jpg",
            "17.jpg"};
    private GridPane cartas [];

    private int cont,x;

    private Timer timer;

    public Loteria(){
        CrearUI();
        this.setTitle("Loteria");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        btnAtras = new Button( "Atras");
        btnAtras.setPrefWidth(100);
        btnAtras.setOnAction(event ->{
            cont--;
            if(cont<5 && cont>=0) {
                gdpPlantilla = cartas[cont];
                hBox2.getChildren().clear();
                hBox2.getChildren().addAll(gdpPlantilla,imgCart);
            }else{
                cont =4;
                gdpPlantilla = cartas[cont];
                hBox2.getChildren().clear();
                hBox2.getChildren().addAll(gdpPlantilla,imgCart);
            }
        });

        btnSiguiente = new Button("Siguiente");
        btnSiguiente.setOnAction(event -> {
            cont++;
            if (cont < 5 && cont >= 0) {
                gdpPlantilla = cartas[cont];
                hBox2.getChildren().clear();
                hBox2.getChildren().addAll(gdpPlantilla, imgCart);
            } else {
                cont = 0;
                gdpPlantilla = cartas[cont];
                hBox2.getChildren().clear();
                hBox2.getChildren().addAll(gdpPlantilla, imgCart);
            }
        });

        btnSiguiente.setPrefWidth(100);
        lblTiempo = new Label("00:00");
        hBox1 = new HBox();
        hBox1.setSpacing(35);

        gdpPlantilla = new GridPane();
        plantilla = new Plantilla();
        p1 = plantilla.CrearPlantilla(p1cartas);
        p2 = plantilla.CrearPlantilla(p2cartas);
        p3 = plantilla.CrearPlantilla(p3cartas);
        p4 = plantilla.CrearPlantilla(p4cartas);
        p5 = plantilla.CrearPlantilla(p5cartas);
        cartas =  new GridPane[]{p1,p2,p3,p4,p5};
        gdpPlantilla =cartas[0];

        hBox2 =new HBox();
        hBox2.setSpacing(5);
            file = new File("src/main/java/com/example/proyectofinal2/Imagenes/0.jpg"  );
            imgCarta = new Image(file.toURI().toString());
            imv = new ImageView(imgCarta);
            imgCart = new Label();
            imv.setFitWidth(200);
            imv.setFitHeight(280);
            imgCart.setGraphic(imv);

        btnJugar =new Button("Jugar");
        btnJugar.setPrefWidth(250);

        btnJugar.setOnAction(event -> {
            x=0;
            btnSiguiente.setDisable(true);
            btnJugar.setDisable(true);
            btnAtras.setDisable(true);
            moveCircle();
        });

        hBox1.getChildren().addAll(btnAtras,btnSiguiente,lblTiempo);
        hBox2.getChildren().addAll(gdpPlantilla, imgCart);

        vBox =new VBox();
        vBox.setSpacing(5);

        vBox.getChildren().addAll(hBox1,hBox2,btnJugar);

        escena = new Scene(vBox,470,380);
    }

    private Label crono(){
        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/" + mazo[x]);
        imgCarta = new Image(file.toURI().toString());
        imv = new ImageView(imgCarta);
        imgCart = new Label();
        imv.setFitWidth(200);
        imv.setFitHeight(280);
        imv.setPreserveRatio(true);
        imgCart.setGraphic(imv);
        imgCart.setVisible(false);
        imgCart.setVisible(true);
        x++;

        if (x>=25){
            System.out.println("chocho");

            timer.cancel();
        }
        return imgCart;
    }

    private int[] fisher_yates (int[] arreglo) {
        for(int i = arreglo.length - 1; i > 0; i--) {
            int shuffled_index = (int)Math.floor(Math.random() * (i + 1));
            int tmp = arreglo[i];
            arreglo[i] = arreglo[shuffled_index];
            arreglo[shuffled_index] = tmp;
        }
        return arreglo;
    }

    public void moveCircle() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    file = new File("src/main/java/com/example/proyectofinal2/Imagenes/" + mazo[x]);
                    imgCarta = new Image(file.toURI().toString());
                    imv = new ImageView(imgCarta);
                    imgCart = new Label();
                    imgCart.setAccessibleRoleDescription(mazo[x]);
                    imv.setFitWidth(200);
                    imv.setFitHeight(280);
                    imv.setPreserveRatio(true);
                    imgCart.setGraphic(imv);
                    imgCart.setVisible(false);
                    imgCart.setVisible(true);
                    hBox2.getChildren().clear();
                    hBox2.getChildren().addAll(gdpPlantilla, imgCart);
                    x++;
                    if(Plantilla.p[0][0].isDisable() && Plantilla.p[0][1].isDisable() &&
                            Plantilla.p[0][2].isDisable() && Plantilla.p[0][3].isDisable() &&
                            Plantilla.p[1][0].isDisable() && Plantilla.p[1][1].isDisable() &&
                            Plantilla.p[1][2].isDisable() && Plantilla.p[1][3].isDisable() &&
                            Plantilla.p[2][0].isDisable() && Plantilla.p[2][1].isDisable() &&
                            Plantilla.p[2][2].isDisable() && Plantilla.p[2][3].isDisable() &&
                            Plantilla.p[3][0].isDisable() && Plantilla.p[3][1].isDisable() &&
                            Plantilla.p[3][2].isDisable() && Plantilla.p[3][3].isDisable()){
                        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/0.jpg"  );
                        imgCarta = new Image(file.toURI().toString());
                        imv = new ImageView(imgCarta);
                        timer.cancel();

                    }
                    if (x>=25){
                        //System.out.println("chochote");
                        btnAtras.setDisable(false);
                        btnSiguiente.setDisable(false);
                        btnJugar.setDisable(false);
                        file = new File("src/main/java/com/example/proyectofinal2/Imagenes/0.jpg"  );
                        imgCarta = new Image(file.toURI().toString());
                        imv = new ImageView(imgCarta);

                        timer.cancel();

                    }
                });
            }
        }, 1000, 2000);
    }


}