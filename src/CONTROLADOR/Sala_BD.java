/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;


import MODELO.*;
import java.sql.Connection;
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
public class Sala_BD {
      private Connection conex;

    public Sala_BD() {
    }
    public Sala_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en Sala_BD");
        }
    }
    
      
    public void guardarSala(Sala sala){
        try {

            String SQL = "INSERT INTO sala (cant_butaca) VALUES (?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, sala.getCant_butaca());
           
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                  sala.setId_sala(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id del sala luego de intentar generarlo");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar sala: "+ e);
        }
    }
    public void borrarSala(int id){
        try {
            
            String SQL = "DELETE FROM sala WHERE id_sala = ?;";
            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar la sala: "+ e);
        }
    }
    public void modificarSala(Sala sala){
        try {
            
            String SQL = "UPDATE sala SET cant_butaca = ? WHERE id_sala = ? ;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1, sala.getCant_butaca());
            ps.setInt(2, sala.getId_sala());
           
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar la Sala "+sala.getId_sala());
        }
    }
    
    public Sala buscarSala(int id){
    Sala sala = null;
      try {
                
            String SQL = "SELECT * FROM sala WHERE id_sala=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                sala = new Sala();
                sala.setCant_butaca(resultSet.getInt("cant_butaca"));
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar la Sala " + e);
        }
      return sala;
    }
    
    public List<Sala> obtenerSalaXpelicula(int id){
        List<Sala> lista= new ArrayList<Sala>();
        
        try {
            String SQL ="SELECT * FROM sala, funcion WHERE funcion.id_pelicula = "+id+";";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Sala sala;
            
            while(resultSet.next()){
                sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                sala.setCant_butaca(resultSet.getInt("cant_butaca"));
                                               
                lista.add(sala);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de salas " + e);
        }
        return lista;
    }
    public List<Sala> obtenerListaSala(){
        List<Sala> lista= new ArrayList<Sala>();
        
        try {
            String SQL ="SELECT * FROM sala  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Sala sala;
            
            while(resultSet.next()){
                sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                sala.setCant_butaca(resultSet.getInt("cant_butaca"));
                                
                lista.add(sala);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de pelicula " + e);
        }
        return lista;
    }
}
