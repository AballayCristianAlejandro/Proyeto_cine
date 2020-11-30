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
public class Sala {
   public int id_sala;
   public int cant_butaca;
   public Boolean activo;

    public Sala() {
    }

    public Sala(int cant_butaca) {
        this.cant_butaca = cant_butaca;
      
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getCant_butaca() {
        return cant_butaca;
    }

    public void setCant_butaca(int cant_butaca) {
        this.cant_butaca = cant_butaca;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return "Sala{" + "id_sala=" + id_sala + ", cant_butaca=" + cant_butaca +"activo="+ activo+ '}';
    }
   
   
}
