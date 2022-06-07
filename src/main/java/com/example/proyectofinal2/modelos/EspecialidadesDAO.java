package com.example.proyectofinal2.modelos;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EspecialidadesDAO {

    private Conexion fabricaConexion =new Conexion();

    public int cveplatillo;
    public String nomplatillo;
    public Double precio;

    public EspecialidadesDAO() {

    }

    public int getCveplatillo() {return cveplatillo;}

    public void setCveplatillo(int cveplatillo) {this.cveplatillo = cveplatillo;}

    public String getNomplatillo() {
        return nomplatillo;
    }

    public void setNomplatillo(String nomplatillo) {
        this.nomplatillo = nomplatillo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public ObservableList<TacosDAO> SELECCIONAR(){//Recuperar todos los registros
        ObservableList<TacosDAO> listaC = FXCollections.observableArrayList();
        try {
            TacosDAO objC;
            String query="SELECT * FROM tblespecilidades;";
            Connection connection = this.fabricaConexion.getConnection();
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res =stmt.executeQuery(query);
            while (res.next()){

                objC = new TacosDAO();
                objC.setCveplatillo(res.getInt("cveplatillo"));
                objC.setNomplatillo(res.getString("nomplatillo"));
                objC.setPrecio(res.getDouble("precio"));
                listaC.add(objC);

                System.out.println("Exito");
            }
            res.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fracaso");

        }
        return listaC;
    }

}

