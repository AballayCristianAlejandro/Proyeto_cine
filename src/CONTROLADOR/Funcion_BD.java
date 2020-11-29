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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Funcion_BD {
        private Connection conex;

    public Funcion_BD() {
    }
    public Funcion_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en Funcion_BD");
        }
    }
    
      
    public void guardarFuncion(Funcion funcion){
        try {

            String SQL = "INSERT INTO funcion (id_pelicula, id_sala,id_butaca, fecha, hora_entrada, hora_salida) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, funcion.getId_pelicula().getId_pelicula());
            ps.setInt(2, funcion.getId_sala().getId_sala());
            ps.setInt(3, funcion.getId_butaca().getId_butaca());
            ps.setDate(4, Date.valueOf(funcion.getFecha()));
            ps.setTime(5, Time.valueOf(funcion.getHora_entrada()));
            ps.setTime(6, Time.valueOf(funcion.getHora_salida()));
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                  funcion.setId_funcion(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id de la funcion");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar la funcion: "+ e);
        }
    }
    public void borrarFuncion(int id){
        try {
            
            String SQL = "DELETE FROM funcion WHERE id_funcion = ?;";
            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar la funcion: "+ e);
        }
    }
    public void modificarFuncion(Funcion funcion){
        try {
            
            String SQL = "UPDATE funcion SET fecha=?, hora_entrada=?, hora_salida=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            //ps.setInt(1,funcion.getId_pelicula().getId_pelicula());
            //ps.setInt(2,funcion.getId_sala().getId_sala());
            ps.setDate(1, Date.valueOf(funcion.getFecha()));
            ps.setTime(2, Time.valueOf(funcion.getHora_entrada()));
            ps.setTime(3, Time.valueOf(funcion.getHora_salida()));
            
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar la Funcion "+funcion.getId_funcion());
        }
    }
    public Funcion buscarFuncion(int id){
    Funcion funcion = null;
      try {
                
            String SQL = "SELECT * FROM funcion WHERE id_funcion=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                funcion = new Funcion();
                
                Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                funcion.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                funcion.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                funcion.setId_butaca(butaca);
                 
                funcion.setFecha(resultSet.getString("fecha"));
                funcion.setHora_entrada(resultSet.getString("hora_entrada"));
                funcion.setHora_salida(resultSet.getString("hora_salida"));
                
                              
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar a la funcion "+ e );
        }
      return funcion;
    }
    public List<Funcion> obtenerListaFuncion(){
        List<Funcion> lista= new ArrayList<Funcion>();
        
        try {
            String SQL ="SELECT * FROM funcion  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Funcion funcion;
            
            while(resultSet.next()){
                funcion = new Funcion();
                funcion.setId_funcion(resultSet.getInt("id_funcion"));
                 Pelicula pelicula = new Pelicula();
                pelicula.setId_pelicula(resultSet.getInt("id_pelicula"));
                funcion.setId_pelicula(pelicula);
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                funcion.setId_sala(sala);
                
                Butaca butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                funcion.setId_butaca(butaca);
                 
                funcion.setFecha(resultSet.getString("fecha"));
                funcion.setHora_entrada(resultSet.getString("hora_entrada"));
                funcion.setHora_salida(resultSet.getString("hora_salida"));
                
                                
                lista.add(funcion);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de funcion " + e);
        }
        return lista;
    }
}
