package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/scig";
    static String usuario="root";
    static String password ="";
    
    protected Connection conn = null;
    
    public conexionBD(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el Driver"+ e.getMessage());
        } catch (SQLException e){
            System.out.println("Error en la conexion a la base de datos"+ e.getMessage());
        }
    }
    public Connection conectar(){
        return conn;
    }
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la Base de Datos "+ e.getMessage());
        }
    }      
}
