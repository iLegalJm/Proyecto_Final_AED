/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import DaoImpl.InterfaceCrudSimple;
import Entidad.Genero;
import Entidad.Pelicula;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author CHATARRA-2
 */
public class GeneroDao implements InterfaceCrudSimple<Genero> {

    private final Conexion cx;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public GeneroDao() {
        this.cx = Conexion.getInstancia();
    }

    @Override
    public List<Genero> listar(String texto) {
        List<Genero> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("SELECT * from genero where nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Genero(rs.getInt(1), rs.getString(2)));
            }

//            ORDENAMIENTO POR COMPARACION
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            cx.desconectar();
        }
        return registros;
    }

    public List<Genero> seleccionar() {
        List<Genero> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("select id, nombre from genero order by nombre asc");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Genero(rs.getInt(1), rs.getString(2)));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            cx.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Genero obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        List<Genero> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("SELECT nombre from genero where nombre = ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            if (rs.getRow() > 0) {
                resp = true;
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            cx.desconectar();
        }
        return resp = false;
    }

}
