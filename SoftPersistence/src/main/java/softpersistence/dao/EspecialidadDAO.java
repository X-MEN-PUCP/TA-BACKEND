/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import java.util.ArrayList;
import softmodel.modelos.EspecialidadDTO;

/**
 *
 * @author salva
 */
public interface EspecialidadDAO {
    
    public abstract Integer insertar(EspecialidadDTO especialidad);
    public abstract Integer modificar(EspecialidadDTO especialidad);
    public abstract Integer eliminar(Integer id);
    public abstract EspecialidadDTO obtenerPorId(Integer id);
    public abstract ArrayList<EspecialidadDTO> listarTodos();
    
}
