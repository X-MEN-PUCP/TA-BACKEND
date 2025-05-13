/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import java.sql.Date;
import softmodel.modelos.CitaDTO;
import java.util.ArrayList;
import java.util.List;
import softmodel.util.Estado;

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
    public ArrayList<CitaDTO> listarPorIdMedicoEstadoFecha(Integer idMedico, Estado estado, java.util.Date fecha);

    
    public ArrayList<CitaDTO> ReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin);

}
