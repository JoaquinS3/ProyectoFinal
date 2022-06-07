package com.example.proyectofinal2.views;

import com.example.proyectofinal2.views.Loteria;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.File;

public class Plantilla  implements EventHandler<ActionEvent>{

    private File file;
    private Image imgCarta;
    static Button p [][]=new Button[4][4];
    private GridPane gdpPlan;


    public GridPane CrearPlantilla (String s[]){
        gdpPlan = new GridPane();
        int x=0;
        for (int i =0; i<4; i++){
            for (int j =0; j<4; j++){
                p[j][i]=new Button();
                p[j][i].setAccessibleRoleDescription(s[x]);
                file = new File("src/main/java/com/example/proyectofinal2/Imagenes/" + s[x]);
                imgCarta = new Image(file.toURI().toString());
                ImageView imv = new ImageView(imgCarta);
                imv.setFitWidth(50);
                imv.setFitHeight(70);
                p[j][i].setGraphic(imv);
                p[j][i].setOnAction(this);
                gdpPlan.add(p[j][i],i,j);
                x++;
            }
        }
        return gdpPlan;
    }

    @Override
    public void handle(ActionEvent event) {
        if (((Button)event.getSource()).getAccessibleRoleDescription()== Loteria.imgCart.getAccessibleRoleDescription()){
            ((Button)event.getSource()).setDisable(true);
        }
    }
}
