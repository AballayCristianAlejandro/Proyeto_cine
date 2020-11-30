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
public class Butaca {
    public int id_butaca;
    public Sala id_sala;
    public boolean estado;//asdsdsa//

    public Butaca() {
    }

    public Butaca(Sala id_sala, boolean estado) {
        this.id_sala = id_sala;
        this.estado = estado;
    }

    public int getId_butaca() {
        return id_butaca;
    }

    public void setId_butaca(int id_butaca) {
        this.id_butaca = id_butaca;
    }

    public Sala getId_sala() {
        return id_sala;
    }

    public void setId_sala(Sala id_sala) {
        this.id_sala = id_sala;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Butaca{" + "id_butaca=" + id_butaca + ", id_sala=" + id_sala + ", estado=" + estado + '}';
    }
    
}
