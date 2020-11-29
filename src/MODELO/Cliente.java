/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Thomas
 */
public class Cliente {
    public int id_cliente;
    public String Nombre;
    public int dni;

    public Cliente() {
    }

    public Cliente(String Nombre, int dni) {
        this.Nombre = Nombre;
        this.dni = dni;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", Nombre=" + Nombre + ", dni=" + dni + '}';
    }
    
}
