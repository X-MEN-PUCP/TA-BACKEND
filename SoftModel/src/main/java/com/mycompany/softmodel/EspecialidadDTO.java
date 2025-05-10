/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softmodel;

/**
 *
 * @author Mcerv
 */
public class EspecialidadDTO {
    private Integer idEspecialidad;
    private String nombreEspecialidad;
    private double precioConsulta; //o float

    /**
     * @return the idEspecialidad
     */
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the nombreEspecialidad
     */
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    /**
     * @param nombreEspecialidad the nombreEspecialidad to set
     */
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    /**
     * @return the precioConsulta
     */
    public double getPrecioConsulta() {
        return precioConsulta;
    }

    /**
     * @param precioConsulta the precioConsulta to set
     */
    public void setPrecioConsulta(double precioConsulta) {
        this.precioConsulta = precioConsulta;
    }
    
    
}
