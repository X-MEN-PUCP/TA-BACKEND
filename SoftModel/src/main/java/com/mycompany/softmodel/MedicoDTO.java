/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softmodel;

/**
 *
 * @author Mcerv
 */
public class MedicoDTO extends PersonaDTO{
    private EspecialidadDTO especialidad;
    private String codMedico;

    /**
     * @return the especialidad
     */
    public EspecialidadDTO getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the codMedico
     */
    public String getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(String codMedico) {
        this.codMedico = codMedico;
    }
    
}
