/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dao.EstudioDao;
import Dao.GeneroDao;
import Dao.PeliculaDao;
import Entidad.Estudio;
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
public class EstudioControl {

    private final EstudioDao DATOS;
    private Estudio objEstudio;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    private final PeliculaDao DATOSPELICULA;
    private Pelicula objPelicula;

    public EstudioControl() {
        this.DATOS = new EstudioDao();
        this.objEstudio = new Estudio();
        this.DATOSPELICULA = new PeliculaDao();
        this.objPelicula = new Pelicula();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Estudio> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

//        ORDENAMIENTO POR COMPARACION
      
        String[] titulos = {"Id", "Género"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[2];

        this.registrosMostrados = 0;
        for (Estudio item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    
    public DefaultComboBoxModel seleccionar() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Estudio> lista = new ArrayList();
        lista = DATOS.seleccionar();
        for (Estudio item : lista) {
            items.addElement(new Genero(item.getId(), item.getNombre()));
        }
        return items;
    }

    public String insertar(String nombre) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";
        } else {
            objEstudio.setNombre(nombre);

            if (DATOS.insertar(objEstudio)) {
                return "OK";
            } else {
                return "Error al registrar";
            }

        }
    }

}
