/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import java.time.LocalDate;
import java.util.ArrayList;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.daoImp.CitaDAOImpl;

/**
 *
 * @author salva
 */
public class citaBO {

    private CitaDTO cita;
    private CitaDAO citaDao;

    public citaBO() {
        citaDao = new CitaDAOImpl();
    }
    
    public Integer insertar(Integer idHorario,Integer idPersona,String observaciones,
            int idHistoria,String estado){
        
        HorarioDTO horario = new HorarioDTO();
        horario.setIdHorario(idHorario);
        
        MedicoDTO medico = new MedicoDTO();
        medico.setIdPersona(idPersona);
        
        HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
        historia.setIdHistoriaClinica(idHistoria);
        
        this.cita = new CitaDTO();
        this.cita.setHorario(horario);
        this.cita.setMedico(medico);
        this.cita.setObservacionesMedicas(observaciones);
        this.cita.setHistoriaClinicaPaciente(historia);
        this.cita.setEstado(Estado.valueOf(estado));
        
        return citaDao.insertar(this.cita);
    }
     
    public Integer modificar(Integer idCita,Integer idHorario,Integer idPersona,String observaciones,
            int idHistoria,String estado){
        
        HorarioDTO horario = new HorarioDTO();
        horario.setIdHorario(idHorario);
        
        MedicoDTO medico = new MedicoDTO();
        medico.setIdPersona(idPersona);
        
        HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
        historia.setIdHistoriaClinica(idHistoria);
        
        this.cita = new CitaDTO();
        this.cita.setIdCita(idCita);
        this.cita.setHorario(horario);
        this.cita.setMedico(medico);
        this.cita.setObservacionesMedicas(observaciones);
        this.cita.setHistoriaClinicaPaciente(historia);
        this.cita.setEstado(Estado.valueOf(estado));
        
        return citaDao.modificar(this.cita);
    }
    
    public Integer eliminar(Integer id){
        return citaDao.eliminar(id);
    }
    
    public CitaDTO obtenerPorId(Integer id){
        return this.citaDao.obtenerPorId(id);
        
    }
    
    public ArrayList<CitaDTO> listarTodos(){
        return this.citaDao.listarTodos();
    }
    
    public ArrayList<CitaDTO> listarPorIdMedico(Integer idMedico){
        return this.citaDao.listarPorIdMedico(idMedico);
    }
    
    public ArrayList<CitaDTO> listarPorIdMedicoEstadoFecha(Integer idMedico, Estado estado, java.util.Date fecha){
        return this.citaDao.listarPorIdMedicoEstadoFecha(idMedico, estado, fecha);
    }
    
    public ArrayList<CitaDTO> ReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin){
        return this.citaDao.ReporteResumenGeneral(especialidad, estado, fechaInicio, fechaFin);
    }
    
    public ArrayList<CitaDTO> listarPorMedico(int idMedico){
        return this.citaDao.listarPorIdMedico(idMedico);
    }
    
    public ArrayList<CitaDTO> listarPorPaciente(PacienteDTO paciente){
        return this.citaDao.listarPorPaciente(paciente);
    }
    
    public ArrayList<CitaDTO> buscarCitasDisponibles(Integer idEspecialidad, Integer codMedico, LocalDate fecha){
        return this.buscarCitasDisponibles(idEspecialidad, codMedico, fecha);
    }   
    
 }
