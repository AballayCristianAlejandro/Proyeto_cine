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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class Butaca_BD {
    private Connection conex;

    public Butaca_BD() {
    }
    public Butaca_BD(Conexion conexion) {
        try {
            conex = conexion.getConex();
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion en Butaca_BD");
        }
    }
      
    public void guardarButaca(Butaca butaca){
        try {

            String SQL = "INSERT INTO butaca (id_sala, estado) VALUES (?, ?);";

            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,butaca.getId_sala().getId_sala());
            ps.setBoolean(2, butaca.isEstado());
           
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); 
            
            if(rs.next()){
                  butaca.setId_butaca(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el id de la butaca");
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error al querer guardar la butaca: "+ e);
        }
    }
    
    public void borrarButaca(int id){
        try {
            
            String SQL = "DELETE FROM butaca WHERE id_butaca = ?;";
            PreparedStatement ps = conex.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer borrar la butaca: "+ e);
        }
    } 
     
    public void modificarButaca(Butaca butaca){
        try {
            
            String SQL = "UPDATE butaca SET estado=? WHERE id_butaca=? ;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setBoolean(1, butaca.isEstado());
            ps.setInt(2,butaca.getId_butaca());
                      
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer actualizar la Butaca "+butaca.getId_butaca()+ e);
        }
    }
    
    public Butaca buscarButaca(int id){
      Butaca butaca = null;
      try {
                
            String SQL = "SELECT * FROM butaca WHERE id_butaca=?;";
            
            PreparedStatement ps = conex.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery(); 
            
            while(resultSet.next()){
                butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                butaca.setId_sala(sala);
                
                butaca.setEstado(resultSet.getBoolean("estado"));
                              
            }        
     
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al querer buscar la butaca"+ e );
        }
      return butaca;
    }
    public List<Butaca> obtenerListaButaca(){
        List<Butaca> lista= new ArrayList<Butaca>();
        
        try {
            String SQL ="SELECT * FROM butaca  ;";        
            PreparedStatement ps = conex.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            
            Butaca butaca;
            
            while(resultSet.next()){
                butaca = new Butaca();
                butaca.setId_butaca(resultSet.getInt("id_butaca"));
                Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt("id_sala"));
                butaca.setId_sala(sala);
                butaca.setEstado(resultSet.getBoolean("estado"));
                
                
                                
                lista.add(butaca);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de pelicula " + e);
        }
        return lista;
    }
}
