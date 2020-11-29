/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.*;
import MODELO.Conexion;
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
public class Pelicula_BD {
    private Connection conex;

    public Pelicula_BD() {
    }
    public Pelicula_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en Pelicula_BD");
        }
    }
    
      
    public void guardarPelicula(Pelicula pelicula){
        try {

            String SQL = "INSERT INTO pelicula (nombre, genero, anio) VALUES (?, ?, ?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,pelicula.getNombre());
            ps.setNString(2,pelicula.getGenero());
            ps.setInt(3, pelicula.getAnio());
          
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                  pelicula.setId_pelicula(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id de la pelicula");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar la pelicula: "+ e);
        }
    }
    public void borrarPelicula(int id){
        try {
            
            String SQL = "DELETE FROM pelicula WHERE id_pelicula =?;";
            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar la pelicula: "+ e);
        }
    }
    public void modificarPelicula(Pelicula pelicula){
        try {
            
            String SQL= "UPDATE pelicula SET  nombre=?, genero=?, anio=? WHERE id_pelicula = ?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
          
            ps.setString(1, pelicula.getNombre());
            ps.setString(2, pelicula.getGenero());
            ps.setInt(3 ,pelicula.getAnio());
        ps.setInt(4 ,pelicula.getId_pelicula());
          
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar la Pelicula "+pelicula.getId_pelicula());
        }
    }
    public Pelicula buscarPelicula(int id){
    Pelicula pelicula = null;
      try {
                
            String SQL = "SELECT * FROM pelicula WHERE id_pelicula=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                pelicula = new Pelicula();
              
                pelicula.setNombre(resultSet.getString("nombre"));
                pelicula.setGenero(resultSet.getString("genero"));
                pelicula.setAnio(resultSet.getInt("anio"));
                
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar a la pelicula "+ e );
        }
      return pelicula;
    }
    public List<Pelicula> obtenerPeliculaXsala_y_horario(int id, String x){
        List<Pelicula> lista= new ArrayList<Pelicula>();
        
        try {
            String SQL ="SELECT * FROM pelicula, funcion WHERE funcion.id_sala = "+id+
                 " AND funcion.hora_entrada = '"+Time.valueOf(x)+"';";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Pelicula pelicula;
            
            while(resultSet.next()){
                pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                pelicula.setNombre(resultSet.getString("nombre"));
                pelicula.setGenero(resultSet.getString("genero"));
                pelicula.setAnio(resultSet.getInt("anio"));
                                
                lista.add(pelicula);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de pelicula " + e);
        }
        return lista;
    }
     public List<Pelicula> obtenerListaPelicula(){
        List<Pelicula> lista= new ArrayList<Pelicula>();
        
        try {
            String SQL ="SELECT * FROM pelicula  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Pelicula pelicula;
            
            while(resultSet.next()){
                pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                pelicula.setNombre(resultSet.getString("nombre"));
                pelicula.setGenero(resultSet.getString("genero"));
                pelicula.setAnio(resultSet.getInt("anio"));
                                
                lista.add(pelicula);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de pelicula " + e);
        }
        return lista;
    }

}
