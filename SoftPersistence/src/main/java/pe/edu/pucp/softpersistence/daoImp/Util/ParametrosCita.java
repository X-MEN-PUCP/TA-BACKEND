/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp.Util;

import pe.edu.pucp.softmodel.util.Estado;

/**
 *
 * @author Mcerv
 */
public class ParametrosCita {
    private Integer idMedico;
    private Estado estado;
    private java.sql.Date fecha;
    
    public ParametrosCita(Integer idMedico, Estado estado){
        this.idMedico = idMedico;
        this.estado = estado;
        this.fecha = null;
    }

    public ParametrosCita(Integer idMedico, Estado estado, java.util.Date fecha) {
        this.idMedico = idMedico;
        this.estado = estado;
        this.fecha = new java.sql.Date(fecha.getTime());
    }

    /**
     * @return the idMedico
     */
    public Integer getIdMedico() {
        return idMedico;
    }

    /**
     * @param idMedico the idMedico to set
     */
    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * @return the fecha
     */
    public java.sql.Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
   
}

