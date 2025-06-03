/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp.Util;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Mcerv
 */
public class ParametrosCitaBuilder {
    private Integer codMedico;
    private Integer idEspecialidad;
    private Date fecha;

    public ParametrosCitaBuilder(){
        this.codMedico = null;
        this.fecha = null;
        this.idEspecialidad = null;
    }
    
    public ParametrosCitaBuilder conCodMedico(Integer codMedico){
        this.codMedico = codMedico;
        return this;
    }
    
    public ParametrosCitaBuilder conIdEspecialidad(Integer idEspecialidad){
        this.idEspecialidad = idEspecialidad;
        return this;
    }
    
    public ParametrosCitaBuilder conFecha(Date fecha){
        this.fecha = fecha;
        return this;
    }
    
    public ParametrosCita BuildParametrosCita(){
        ParametrosCita cita = new ParametrosCita();
        cita.setFecha(this.getFecha());
        cita.setIdEspecialidad(this.getIdEspecialidad());
        cita.setIdMedico(this.getCodMedico());
        return cita;
    }
    
    public Integer getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(Integer codMedico) {
        this.codMedico = codMedico;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
