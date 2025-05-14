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
    
    public Integer insertar(EspecialidadDTO especialidad);
    public Integer modificar(EspecialidadDTO especialidad);
    public Integer eliminar(Integer id);
    public EspecialidadDTO obtenerPorId(Integer id);
    public ArrayList<EspecialidadDTO> listarTodos();
    public EspecialidadDTO buscarPorNombre(String nombreEspe);
}
