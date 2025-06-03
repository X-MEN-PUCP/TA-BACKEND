/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softmodel.modelos;

/**
 *
 * @author Mcerv
 */
public class HistoriaClinicaDTO {
    private Integer idHistoriaClinica;
    private PacienteDTO paciente;
    
    
    public HistoriaClinicaDTO(){
        this.idHistoriaClinica= null;
        this.paciente = null;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }
    
    /**
     * @return the idHistoriaClinica
     */
    public Integer getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    /**
     * @param idHistoriaClinica the idHistoriaClinica to set
     */
    public void setIdHistoriaClinica(Integer idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    
}
