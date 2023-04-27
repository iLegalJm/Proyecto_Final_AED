/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author CHATARRA-2
 */
public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3377/";
    private static final String BD = "catalogo?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "..MalTin2022";

    public Connection conex;
    public static Conexion instancia;

    public Conexion() {
        this.conex = null;
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.conex = DriverManager.getConnection(URL + BD, USER, PASSWORD);
//            JOptionPane.showMessageDialog(null, "Conexion correcta");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return (Connection) this.conex;
    }

    public Connection desconectar() {
        try {
            this.conex.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.conex;
    }

    public synchronized static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }

        return instancia;
    }
}
