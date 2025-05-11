/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import com.mycompany.softmodel.PacienteDTO;
import pe.edu.pucp.softinv.softpersistence.dao.PacienteDAO;
import pe.edu.pucp.softinv.softpersistence.daoImp.PacienteDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaPaciente extends CuentaBO{
    private Integer idHistoria;
    private PacienteDAO pacienteDAO;
    
    public CuentaPaciente(Integer id){
        super.setIdCuenta(id);
        pacienteDAO = new PacienteDAOImpl();
        PacienteDTO paciente = pacienteDAO.buscarPorIdCuenta(id);
        this.idHistoria = paciente.getHistoriaClinica().getIdHistoriaClinica();
        super.setIdPersona(paciente.getIdPersona());
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaPaciente.QuienSoy()");
    }
}
