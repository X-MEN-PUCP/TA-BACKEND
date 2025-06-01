/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import java.util.ArrayList;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;

/**
 *
 * @author salva
 */
public interface HistoriaClinicaDAO {
    
    public ArrayList<HistoriaClinicaDTO> listarTodos();
    
}
