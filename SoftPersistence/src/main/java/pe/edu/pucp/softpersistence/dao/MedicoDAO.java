/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import java.util.ArrayList;

import pe.edu.pucp.softmodel.modelos.MedicoDTO;

/**
 *
 * @author salva
 */
public interface MedicoDAO {
    
    public MedicoDTO buscarPorIdCuenta(int idCuenta);
    public ArrayList<MedicoDTO> listarPorIdEspecialidad(int idEspecialidad);
    public MedicoDTO obtenerPorId(Integer id);
    public Integer insertar(MedicoDTO medico);
    public Integer eliminar(Integer id);
}
