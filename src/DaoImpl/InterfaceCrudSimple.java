/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoImpl;

import java.util.List;

/**
 *
 * @author CHATARRA-2
 */
public interface InterfaceCrudSimple<T> {
    public List<T> listar(String texto);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public int total();
    public boolean existe(String texto);
}
