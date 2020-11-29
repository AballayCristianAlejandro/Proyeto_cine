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
public class Funcion {
   public int id_funcion;
   public Pelicula id_pelicula;
   public Sala id_sala;
   public Butaca id_butaca;
   public String fecha;
   public String hora_entrada;
   public String hora_salida;

    public Funcion() {
    }

    public Funcion(Pelicula id_pelicula, Sala id_sala, Butaca id_butaca, String fecha, String hora_entrada, String hora_salida) {
        this.id_pelicula = id_pelicula;
        this.id_sala = id_sala;
        this.id_butaca = id_butaca;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
    }

    public int getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(int id_funcion) {
        this.id_funcion = id_funcion;
    }

    public Pelicula getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Pelicula id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public Sala getId_sala() {
        return id_sala;
    }

    public void setId_sala(Sala id_sala) {
        this.id_sala = id_sala;
    }

    public Butaca getId_butaca() {
        return id_butaca;
    }

    public void setId_butaca(Butaca id_butaca) {
        this.id_butaca = id_butaca;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    @Override
    public String toString() {
        return "Funcion{" + "id_funcion=" + id_funcion + ", id_pelicula=" + id_pelicula + ", id_sala=" + id_sala + ", id_butaca=" + id_butaca + ", fecha=" + fecha + ", hora_entrada=" + hora_entrada + ", hora_salida=" + hora_salida + '}';
    }

    
   
}
