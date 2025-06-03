/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp.Util;

import java.sql.Date;

/**
 *
 * @author Mcerv
 */
public class ParametrosCita {
    private Integer codMedico;
    private Integer idEspecialidad;
    private Date fecha;
    

    public ParametrosCita() {
        this.codMedico = null;
        this.idEspecialidad = null;
        this.fecha = null;
        //this.fecha = new java.sql.Date(fecha.getTime());
    }

    /**
     * @return the codMedico
     */
    public Integer getIdMedico() {
        return codMedico;
    }

    /**
     * @param idMedico the codMedico to set
     */
    public void setIdMedico(Integer idMedico) {
        this.codMedico = idMedico;
    }

    /**
     * @return the estado
     */
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the estado to set
     */
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
}

