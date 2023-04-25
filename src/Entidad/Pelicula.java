/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.util.Date;

/**
 *
 * @author CHATARRA-2
 */
public class Pelicula {
    private int id;
    private int id_genero;
    private int id_estudio;
    private String titulo;
    private Date añoPublicacion;
    private String director;
    private String sinopsis;
    private String ruta;

    public Pelicula(int id, int id_genero, int id_estudio, String titulo, Date añoPublicacion, String director, String sinopsis, String ruta) {
        this.id = id;
        this.id_genero = id_genero;
        this.id_estudio = id_estudio;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.director = director;
        this.sinopsis = sinopsis;
        this.ruta = ruta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_estudio() {
        return id_estudio;
    }

    public void setId_estudio(int id_estudio) {
        this.id_estudio = id_estudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(Date añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", id_genero=" + id_genero + ", id_estudio=" + id_estudio + ", titulo=" + titulo + ", a\u00f1oPublicacion=" + añoPublicacion + ", director=" + director + ", sinopsis=" + sinopsis + ", ruta=" + ruta + '}';
    }
    
    
    
}
