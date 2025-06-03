/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;

import pe.edu.pucp.softmodel.modelos.AdminDTO;
import pe.edu.pucp.softpersistence.dao.AdminDAO;
import pe.edu.pucp.softpersistence.daoImp.AdminDAOImpl;

/**
 *
 * @author Cesar
 */
public class AdminBO {
    
    private AdminDAO dao;
    
    public AdminBO(){
        dao=new AdminDAOImpl();
    }
    
    public AdminDTO buscarPorIdCuenta(int idCuenta){
        return dao.buscarPorIdCuenta(idCuenta);
    }
}
