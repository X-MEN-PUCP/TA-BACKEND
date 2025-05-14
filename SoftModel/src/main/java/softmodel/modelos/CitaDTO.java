/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softmodel.modelos;

import softmodel.util.Estado;

/**
 *
 * @author Mcerv
 */
public class CitaDTO {
    private Integer idCita;
    private HorarioDTO horario;
    private MedicoDTO medico;
    private String observacionesMedicas;
    private HistoriaClinicaDTO historiaClinicaPaciente;
    private Estado estado;

    /**
     * @return the idCita
     */
    public Integer getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    /**
     * @return the horario
     */
    public HorarioDTO getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(HorarioDTO horario) {
        this.horario = horario;
    }

    /**
     * @return the medico
     */
    public MedicoDTO getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    /**
     * @return the observacionesMedicas
     */
    public String getObservacionesMedicas() {
        return observacionesMedicas;
    }

    /**
     * @param observacionesMedicas the observacionesMedicas to set
     */
    public void setObservacionesMedicas(String observacionesMedicas) {
        this.observacionesMedicas = observacionesMedicas;
    }

    /**
     * @return the historiaClinicaPaciente
     */
    public HistoriaClinicaDTO getHistoriaClinicaPaciente() {
        return historiaClinicaPaciente;
    }

    /**
     * @param historiaClinicaPaciente the historiaClinicaPaciente to set
     */
    public void setHistoriaClinicaPaciente(HistoriaClinicaDTO historiaClinicaPaciente) {
        this.historiaClinicaPaciente = historiaClinicaPaciente;
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
    
    
}
