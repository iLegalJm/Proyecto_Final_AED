/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dao.GeneroDao;
import Dao.PeliculaDao;
import Entidad.Pelicula;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHATARRA-2
 */
public class PeliculaControl {

    private final PeliculaDao DATOS;
    private final GeneroDao DATOSGENERO;
    private Pelicula obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public PeliculaControl() {
        this.DATOS = new PeliculaDao();
        this.DATOSGENERO = new GeneroDao();
        this.obj = new Pelicula();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Pelicula> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        String[] titulos = {"Id", "Género Id", "Género", "Estudio Id", "Estudio", "Título", "Año publicación", "Director", "Sinopsis", "Ruta"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[10];
        SimpleDateFormat objFecha = new SimpleDateFormat("yyyy");
        this.registrosMostrados = 0;
        for (Pelicula item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getId_genero());
            registro[2] = item.getGeneroNombre();
            registro[3] = Integer.toString(item.getId_estudio());
            registro[4] = item.getEstudioNombre();
            registro[5] = item.getTitulo();
            registro[6] = objFecha.format(item.getAñoPublicacion()); // Para obtener la fecha primero creamos un objeto de simple date format
            registro[7] = item.getDirector();
            registro[8] = item.getSinopsis();
            registro[9] = item.getRuta();

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public DefaultTableModel listarPorGenero(String texto, int totalPorPagina, int numPagina, String genero) {
        List<Pelicula> lista = new ArrayList();
        List<Pelicula> listaTemp = new ArrayList();
        listaTemp.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        //Busqueda lineal o linear search
        for (int i = 0; i < listaTemp.size(); i++) {
            if (listaTemp.get(i).getGeneroNombre().equals(genero)) {
                lista.add(listaTemp.get(i));
            }
        }

        String[] titulos = {"Id", "Género Id", "Género", "Estudio Id", "Estudio", "Título", "Año publicación", "Director", "Sinopsis", "Ruta"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

//        String estado;
        String[] registro = new String[10];
        this.registrosMostrados = 0;
        for (Pelicula item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getId_genero());
            registro[2] = item.getGeneroNombre();
            registro[3] = Integer.toString(item.getId_estudio());
            registro[4] = item.getEstudioNombre();
            registro[5] = item.getTitulo();
            registro[6] = Integer.toString(item.getAñoPublicacion());
            registro[7] = item.getDirector();
            registro[8] = item.getSinopsis();
            registro[9] = item.getRuta();

            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    public String insertar(int GeneroId, int EstudioId, String titulo, int añoPublicacion, String director, String sinopsis, String ruta) {
        if (DATOS.existe(titulo)) {
            return "La pelicula ya ha sido agregada";
        } else {
            obj.setId_genero(GeneroId);
            obj.setId_estudio(EstudioId);
            obj.setTitulo(titulo);
            obj.setAñoPublicacion(añoPublicacion);
            obj.setDirector(director);
            obj.setSinopsis(sinopsis);
            obj.setRuta(ruta);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error al registrar";
            }
        }
    }

    public int total() {
        return DATOS.total();
    }
}
