package softpersistence.dao;

import softmodel.modelos.AdminDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salva
 */
public interface AdminDAO {
    
    public AdminDTO buscarPorIdCuenta(int idCuenta);
    
    
}
