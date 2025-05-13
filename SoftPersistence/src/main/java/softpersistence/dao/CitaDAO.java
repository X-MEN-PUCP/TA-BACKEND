/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import softmodel.modelos.CitaDTO;
import java.util.ArrayList;
import java.util.List;

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
    
}
