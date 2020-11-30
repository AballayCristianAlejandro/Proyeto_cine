/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Ticket_BD {
    
      private Connection conex;

    public Ticket_BD() {
    }
    public Ticket_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en TicketBD");
        }
    }
    
    public void guardarTicket(Ticket ticket){
        try {

            String SQL = "INSERT INTO ticket (id_pelicula, id_sala, id_butaca, id_funcion , id_cliente, "
                    + "fecha, hora, monto, forma_pago, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ticket.getId_pelicula().getId_pelicula());
            ps.setInt(2, ticket.getId_sala().getId_sala());
            ps.setInt(3, ticket.getId_butaca().getId_butaca());
            ps.setInt(4, ticket.getId_funcion().getId_funcion());
            ps.setInt(5, ticket.getId_cliente().getId_cliente());
            ps.setDate(6, Date.valueOf(ticket.getFecha()));
            ps.setTime(7, Time.valueOf(ticket.getHora()));
            ps.setDouble(8, ticket.getMonto());
            ps.setString(9, ticket.getForma_pago());
            ps.setBoolean(10,ticket.isActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                ticket.setId_ticket(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id del ticket luego de intentar generarlo");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar ticket: "+ e);
        }
    }
    
    public void borrarTicket(int id){
        try {
            
            String instrucciones = "DELETE FROM ticket WHERE id_ticket = ?;";
            PreparedStatement ps = conex.prepareStatement(instrucciones, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar el ticket");
        }
    }
    
    public void modificarTicket(Ticket ticket){
        try {
            
            String SQL = "UPDATE ticket SET fecha=?, hora=?, monto=?, forma_pago=?, estado=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setDate(1, Date.valueOf(ticket.getFecha()));
            ps.setTime(2, Time.valueOf(ticket.getHora()));
            ps.setDouble(3, ticket.getMonto());
            ps.setString(4, ticket.getForma_pago());
            ps.setBoolean(5, ticket.isActivo());
            
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar la Pelicula "+ticket.getId_ticket());
        }
    }
    
    public Ticket buscarTicket(int id){
    Ticket ticket = null;
      try {
                
            String SQL = "SELECT * FROM ticket WHERE id_ticket=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                ticket = new Ticket();
                
                Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                ticket.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                ticket.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                ticket.setId_butaca(butaca);
                
                Funcion funcion = new Funcion();
                funcion.setId_funcion(resultSet.getInt("id_funcion"));
                ticket.setId_funcion(funcion);
                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                ticket.setId_cliente(cliente);
                
                ticket.setFecha(resultSet.getString("fecha"));
                ticket.setHora(resultSet.getString("hora"));
                ticket.setMonto(resultSet.getDouble("monto"));
                ticket.setForma_pago(resultSet.getString("forma_pago"));
                ticket.setActivo(resultSet.getBoolean("estado"));
                              
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar el ticket "+ e );
        }
      return ticket;
    }
  
    public List<Ticket> obtenerTicketsXpeli(int id){
        List<Ticket> tickets = new ArrayList<Ticket>();
        
        try {
            String instrucciones = "SELECT * FROM ticket where id_pelicula = "+id;
            PreparedStatement ps = conex.prepareStatement(instrucciones);
            ResultSet resultSet = ps.executeQuery();
            
            Ticket ticket;
            
            while(resultSet.next()){
                ticket = new Ticket();
                ticket.setId_ticket(resultSet.getInt("id_ticket"));
                
                Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                ticket.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                ticket.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                ticket.setId_butaca(butaca);
                
                Funcion funcion = new Funcion();
                funcion.setId_funcion(resultSet.getInt("id_funcion"));
                ticket.setId_funcion(funcion);
                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                ticket.setId_cliente(cliente);
                
                ticket.setFecha(resultSet.getString("fecha"));
                ticket.setHora(resultSet.getString("hora"));
                ticket.setMonto(resultSet.getDouble("monto"));
                ticket.setForma_pago(resultSet.getString("forma_pago"));
                ticket.setActivo(resultSet.getBoolean("estado"));
                
                
                tickets.add(ticket);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener Tickets " +e);
        }
        return tickets;
    }
   
   
    public List<Ticket> obtenerTicketsXFecha(String fecha){
        List<Ticket> tickets = new ArrayList<Ticket>();
        
        try {
            String instrucciones = "SELECT * FROM ticket where fecha_ticket = '"+fecha+"'";
            PreparedStatement ps = conex.prepareStatement(instrucciones);
            ResultSet resultSet = ps.executeQuery();
            
            Ticket ticket;
            
            while(resultSet.next()){
           ticket = new Ticket();
                ticket.setId_ticket(resultSet.getInt("id_ticket"));
                
                Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                ticket.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                ticket.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                ticket.setId_butaca(butaca);
                
                Funcion funcion = new Funcion();
                funcion.setId_funcion(resultSet.getInt("id_funcion"));
                ticket.setId_funcion(funcion);
                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                ticket.setId_cliente(cliente);
                
                ticket.setFecha(resultSet.getString("fecha"));
                ticket.setHora(resultSet.getString("hora"));
                ticket.setMonto(resultSet.getDouble("monto"));
                ticket.setForma_pago(resultSet.getString("forma_pago"));
                ticket.setActivo(resultSet.getBoolean("estado"));
                
                
                tickets.add(ticket);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener Tickets " + e);
        }
        return tickets;
    }
    public List<Ticket> obtenerListaTicket(){
        List<Ticket> tickets= new ArrayList<Ticket>();
        
        try {
            String SQL ="SELECT * FROM ticket  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Ticket ticket;
            
            while(resultSet.next()){
                ticket = new Ticket();
                ticket.setId_ticket(resultSet.getInt("id_ticket"));
                
                Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                ticket.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                ticket.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                ticket.setId_butaca(butaca);
                
                Funcion funcion = new Funcion();
                funcion.setId_funcion(resultSet.getInt("id_funcion"));
                ticket.setId_funcion(funcion);
                
                Cliente cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                ticket.setId_cliente(cliente);
                
                ticket.setFecha(resultSet.getString("fecha"));
                ticket.setHora(resultSet.getString("hora"));
                ticket.setMonto(resultSet.getDouble("monto"));
                ticket.setForma_pago(resultSet.getString("forma_pago"));
                ticket.setActivo(resultSet.getBoolean("estado"));
                
                
                tickets.add(ticket);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de ticket " + e);
        }
        return tickets;
    }
}
