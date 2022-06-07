package com.example.proyectofinal2.modelos;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {

    public static Connection conexion;
    private static String Server = "localhost";
    private static String user = "root";
    private static String pwd = "";
    private  static String bd = "taqueriadb";
    private static String puerto = "3306";
    private static String nombreBD = "taqueria_bd";
    private static String url="jdbc:mysql://"+Server+":"+puerto+"/"+nombreBD+"?sereverTimezone=UTC";

    public static void  crearConexion()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,pwd);
            System.out.println("Conexion exitosa");
        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();

        }


    }

    public Connection getConnection() {
        return conexion;
    }
}