module com.example.proyectofinal2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyectofinal2 to javafx.fxml;
    exports com.example.proyectofinal2;
    exports com.example.proyectofinal2.views;
    opens com.example.proyectofinal2.views to javafx.fxml;
    opens com.example.proyectofinal2.modelos;

}