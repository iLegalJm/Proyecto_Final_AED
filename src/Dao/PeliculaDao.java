/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Config.Conexion;
import DaoImpl.InterfaceCrudPaginado;
import DaoImpl.InterfaceCrudSimple;
import Entidad.Pelicula;
import java.sql.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author CHATARRA-2
 */
public class PeliculaDao implements InterfaceCrudPaginado<Pelicula> {

    private final Conexion cx;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public PeliculaDao() {
        this.cx = Conexion.getInstancia();
    }

    @Override
    public List<Pelicula> listar(String texto, int totalPorPagina, int numPagina) {
        List<Pelicula> registros = new ArrayList();
        try {
            ps = cx.conectar().prepareStatement("select p.id, g.id, g.nombre, e.id, e.nombre, p.titulo, p.año_publicacion, p.director, p.sinopsis,p.ruta from pelicula p inner join genero g on g.id=p.id_genero inner join estudio e on e.id = p.id_estudio where p.titulo like ? ORDER BY p.id ASC LIMIT ?, ? ");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Pelicula(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
    public boolean insertar(Pelicula obj) {
        resp = false;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO pelicula (id_genero, id_estudio, titulo, año_publicacion, director, sinopsis, ruta) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, obj.getId_genero());
            ps.setInt(2, obj.getId_estudio());
            ps.setString(3, obj.getTitulo());
            ps.setInt(4, obj.getAñoPublicacion());
            ps.setString(5, obj.getDirector());
            ps.setString(6, obj.getSinopsis());
            ps.setString(7, obj.getRuta());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {
            ps = null;
            cx.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Pelicula obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        try {
            ps = cx.conectar().prepareStatement("Select count(id) from pelicula");
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt("count(id)");
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
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try {
            ps = cx.conectar().prepareStatement("Select titulo from pelicula where titulo=?");
            ps.setString(1, texto);
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
