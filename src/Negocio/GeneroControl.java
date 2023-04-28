/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dao.GeneroDao;
import Dao.PeliculaDao;
import Entidad.Genero;
import Entidad.Pelicula;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHATARRA-2
 */
public class GeneroControl {

    private final GeneroDao DATOS;
    private Genero objGenero;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    private final PeliculaDao DATOSPELICULA;
    private Pelicula objPelicula;

    public GeneroControl() {
        this.DATOS = new GeneroDao();
        this.objGenero = new Genero();
        this.DATOSPELICULA = new PeliculaDao();
        this.objPelicula = new Pelicula();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Genero> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        //ORDENAMIENTO POR COMPARACION, CompareTo
        Collections.sort(lista);

        String[] titulos = {"Id", "Género"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[2];

        this.registrosMostrados = 0;
        for (Genero item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    
    public DefaultComboBoxModel seleccionar() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Genero> lista = new ArrayList();
        lista = DATOS.seleccionar();
        for (Genero item : lista) {
            items.addElement(new Genero(item.getId(), item.getNombre()));
        }
        return items;
    }

    public String insertar(String nombre) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";
        } else {
            objGenero.setNombre(nombre);

            if (DATOS.insertar(objGenero)) {
                return "OK";
            } else {
                return "Error al registrar";
            }

        }
    }

}
