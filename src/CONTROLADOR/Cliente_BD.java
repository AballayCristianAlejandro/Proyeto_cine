/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion;
import MODELO.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Cliente_BD {
       private Connection conex;

    public Cliente_BD() {
    }
    public Cliente_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en Cliente_BD");
        }
    }
    
      
    public void guardarCliente(Cliente cliente){
        try {

            String SQL = "INSERT INTO cliente (nombre, dni) VALUES (?, ?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setInt(2,cliente.getDni());
           
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                  cliente.setId_cliente(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id de la cliente");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar la cliente: "+ e);
        }
    }
    public void borrarCliente(int id){
        try {
            
            String SQL = "DELETE FROM cliente WHERE id_cliente = ?;";
            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar la cliente: "+ e);
        }
    }
    public void modificarCliente(Cliente cliente){
        try {
            
            String SQL = "UPDATE cliente SET nombre=?, dni=? WHERE id_cliente=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setString(1, cliente.getNombre());   
            ps.setInt(2,cliente.getDni());
            ps.setInt(3,cliente.getId_cliente());
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar el Cliente "+cliente.getId_cliente() + e);
        }
    }
    public Cliente buscarCliente(int id){
      Cliente cliente = null;
      try {
                
            String SQL = "SELECT * FROM cliente WHERE id_cliente=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDni(resultSet.getInt("dni"));
                              
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar al cliente "+ e );
        }
      return cliente;
    }
    
     public List<Cliente> ListaClienteXfecha(String fecha){
        List<Cliente> lista= new ArrayList<Cliente>();
        
        try {
            String SQL ="SELECT * FROM cliente, ticket WHERE ticket.fecha = "+fecha+";";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Cliente cliente;
            
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDni(resultSet.getInt("dni"));
                
                lista.add(cliente);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de cliente " + e);
        }
        return lista;
    }
     public List<Cliente> obtenerListaCliente(){
        List<Cliente> lista= new ArrayList<Cliente>();
        
        try {
            String SQL ="SELECT * FROM cliente  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Cliente cliente;
            
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDni(resultSet.getInt("dni"));
                                
                lista.add(cliente);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de Cliente " + e);
        }
        return lista;
    }
}
