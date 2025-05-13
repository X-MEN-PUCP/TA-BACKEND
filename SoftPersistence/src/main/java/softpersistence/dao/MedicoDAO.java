/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;

import java.util.ArrayList;

import softmodel.modelos.MedicoDTO;

/**
 *
 * @author salva
 */
public interface MedicoDAO {
    
    public MedicoDTO buscarPorIdCuenta(int idCuenta);
    public ArrayList<MedicoDTO> listarPorIdEspecialidad(int idEspecialidad);
    
}
