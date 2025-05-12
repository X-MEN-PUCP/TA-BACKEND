/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import softmodel.modelos.CitaDTO;
import java.util.ArrayList;

/**
 *
 * @author salva
 */
public interface CitaDAO {
    
    public abstract Integer insertar(CitaDTO cita);
    public abstract Integer modificar(CitaDTO cita);
    public abstract Integer eliminar(Integer id);
    public abstract CitaDTO obtenerPorId(Integer id);
    public abstract ArrayList<CitaDTO> listarTodos();
    public abstract ArrayList<CitaDTO> listarTodos(String columna);
    
}
