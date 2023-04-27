/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CHATARRA-2
 */
public class pruebas {

    String nombre;
    String apeelido;

    public pruebas() {
    }

    public pruebas(String nombre, String apeelido) {
        this.nombre = nombre;
        this.apeelido = apeelido;
    }

    public static void main(String[] args) {
        List<pruebas> list = new ArrayList();
        List<pruebas> listNew = new ArrayList();

        list.add(new pruebas("jorge", "cueva"));
        list.add(new pruebas("maltin", "cueva"));
        list.add(new pruebas("joe", "va"));
        list.add(new pruebas("jaasdasd", "asdasd"));

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).apeelido.equals("cueva")) {
                listNew.add(list.get(i));
            }
        }

        for (int i = 0; i < listNew.size(); i++) {
            System.out.println(listNew.get(i));
        }
    }

    @Override
    public String toString() {
        return "pruebas{" + "nombre=" + nombre + ", apeelido=" + apeelido + '}';
    }
}
