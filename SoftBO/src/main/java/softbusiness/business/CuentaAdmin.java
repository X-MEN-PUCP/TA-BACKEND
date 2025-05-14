/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import softmodel.modelos.CitaDTO;
import softmodel.modelos.MedicoDTO;
import softpersistence.dao.CitaDAO;
import softpersistence.dao.CuentaDAO;
import softpersistence.dao.MedicoDAO;
import softpersistence.daoImp.CitaDAOImpl;
import softpersistence.daoImp.CuentaDAOImpl;
import softpersistence.daoImp.MedicoDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaAdmin extends CuentaBO{
    private CitaDAO citaDAO;
    private MedicoDAO medicoDAO;
    
    public CuentaAdmin(Integer id){
        super.setIdCuenta(id);
        this.citaDAO= new CitaDAOImpl();
        this.medicoDAO= new MedicoDAOImpl();
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaAdmin.QuienSoy()");
    }
    
    public int insertarNuevoMedico(MedicoDTO medico){
        return medicoDAO.insertar(medico);
    }
    
    public int insertarNuevaCita(CitaDTO cita){
        return citaDAO.insertar(cita);
    }
}
