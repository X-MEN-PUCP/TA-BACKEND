/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import java.util.ArrayList;

/**
 *
 * @author salva
 */
public interface HorarioDAO {
    
    public abstract Integer insertar(HorarioDTO horario);
    public abstract HorarioDTO obtenerPorId(Integer id);
    public abstract Integer modificar(HorarioDTO horario);
    public abstract Integer eliminar(Integer id);
    public abstract ArrayList<HorarioDTO> listarTodos();
    public HorarioDTO buscarHorario(HorarioDTO horario);
}
