/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softmodel;

/**
 *
 * @author Mcerv
 */
public class PacienteDTO extends PersonaDTO{
    private HistoriaClinicaDTO historiaClinica;

    /**
     * @return the historiaClinica
     */
    public HistoriaClinicaDTO getHistoriaClinica() {
        return historiaClinica;
    }

    /**
     * @param historiaClinica the historiaClinica to set
     */
    public void setHistoriaClinica(HistoriaClinicaDTO historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
    
}
