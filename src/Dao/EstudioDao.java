/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import DaoImpl.InterfaceCrudSimple;
import Entidad.Estudio;
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
public class EstudioDao implements InterfaceCrudSimple<Estudio> {

    private final Conexion cx;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public EstudioDao() {
        this.cx = Conexion.getInstancia();
    }

    @Override
    public List<Estudio> listar(String texto) {
        List<Estudio> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("SELECT * from estudio where nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Estudio(rs.getInt(1), rs.getString(2)));
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

    public List<Estudio> seleccionar() {
        List<Estudio> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("select id, nombre from estudio order by nombre asc");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Estudio(rs.getInt(1), rs.getString(2)));
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

    public boolean insertar(Estudio obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Estudio obj) {
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
