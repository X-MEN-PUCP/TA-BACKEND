/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import java.sql.Date;
import java.time.LocalDate;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;

/**
 *
 * @author salva
 */
public interface CitaDAO {
    
    public Integer insertar(CitaDTO cita);
    public Integer modificar(CitaDTO cita);
    public Integer eliminar(Integer id);
    public CitaDTO obtenerPorId(Integer id);
    public ArrayList<CitaDTO> listarTodos();
    public ArrayList<CitaDTO> listarPorIdMedico(Integer idMedico);
    public ArrayList<CitaDTO> listarCitasProgramadas(Integer codMedico);
//    public ArrayList<CitaDTO> listarPorIdMedicoEstadoFecha(Integer idMedico, Estado estado, java.util.Date fecha);

    
    public ArrayList<CitaDTO> ReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin);
    public ArrayList<CitaDTO> listarPorMedico(int idMedico);
    public ArrayList<CitaDTO> listarPorPaciente(PacienteDTO paciente);
    public ArrayList<CitaDTO> buscarCitasDisponibles(Integer idEspecialidad, Integer codMedico, LocalDate fecha);

}
