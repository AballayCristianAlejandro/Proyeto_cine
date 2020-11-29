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
public class Ticket {
    
    public int id_ticket;
    public Pelicula id_pelicula;
    public Sala id_sala;
    public Butaca id_butaca;
    public Funcion id_funcion;
    public Cliente id_cliente;
    public String fecha;
    public String hora;
    public double monto;
    public String forma_pago;
    public boolean estado;

    public Ticket() {
    }

    public Ticket(Pelicula id_pelicula, Sala id_sala, Butaca id_butaca, Funcion id_funcion, Cliente id_cliente, String fecha, String hora, double monto, String forma_pago, boolean estado) {
        this.id_pelicula = id_pelicula;
        this.id_sala = id_sala;
        this.id_butaca = id_butaca;
        this.id_funcion = id_funcion;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.forma_pago = forma_pago;
        this.estado = estado;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
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

    public Funcion getId_funcion() {
        return id_funcion;
    }

    public void setId_funcion(Funcion id_funcion) {
        this.id_funcion = id_funcion;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_ticket=" + id_ticket + ", id_pelicula=" + id_pelicula.getId_pelicula() + ", id_sala=" + id_sala.getId_sala() + ", id_butaca=" + id_butaca.getId_butaca() + ", id_funcion=" + id_funcion.getId_funcion() + ", id_cliente=" + id_cliente.getId_cliente() + ", fecha=" + fecha + ", hora=" + hora + ", monto=" + monto + ", forma_pago=" + forma_pago + ", estado=" + estado + '}';
    }
    
}
