/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import softmodel.modelos.PacienteDTO;

/**
 *
 * @author salva
 */
public interface PacienteDAO {
    public PacienteDTO buscarPorIdCuenta(int idCuenta);
    public Integer insertar(PacienteDTO paciente);
    
    
}
