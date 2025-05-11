/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import com.mycompany.softmodel.EspecialidadDTO;
import com.mycompany.softmodel.MedicoDTO;
import pe.edu.pucp.softinv.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softinv.softpersistence.daoImp.MedicoDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaMedico extends CuentaBO{
    private Integer idEspecialidad;
    private MedicoDAO medicoDAO;
    public CuentaMedico(Integer id){
      super.setIdCuenta(id);
      medicoDAO = new MedicoDAOImpl();
      MedicoDTO medico = medicoDAO.buscarPorIdCuenta(id); //se debería verificar que se encuentre
      this.idEspecialidad = medico.getEspecialidad().getIdEspecialidad();
      super.setIdPersona(medico.getIdPersona());
    }
    
    public void mostrarCitas(){
        
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaMedico.QuienSoy()");
    }
}
