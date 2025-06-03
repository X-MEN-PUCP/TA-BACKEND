/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;

import java.util.Date;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softpersistence.dao.PacienteDAO;
import pe.edu.pucp.softpersistence.daoImp.PacienteDAOImpl;

/**
 *
 * @author Cesar
 */
public class PacienteBO {
    private PacienteDAO dao;
    public PacienteBO(){
        dao=new PacienteDAOImpl();
    }
    
    public PacienteDTO buscarPorIdCuenta(int idCuenta){
        return dao.buscarPorIdCuenta(idCuenta);
    }
    
    public Integer insertar(Integer idPersona, Integer idCuenta, String nombres,
            String apellido_peterno, String apellido_materno, Date fechaNaciemiento,
            String correoElectronico, String numCelular, String genero){
        PacienteDTO paciente=new PacienteDTO();
        paciente.setIdPersona(idPersona);
        CuentaDTO cuenta=new CuentaDTO();
        cuenta.setIdCuenta(idCuenta);
        paciente.setCuenta(cuenta);
        paciente.setNombres(nombres);
        paciente.setApellido_paterno(apellido_materno);
        paciente.setApellido_materno(apellido_materno);
        paciente.setFechaNaciemiento(fechaNaciemiento);
        paciente.setCorreoElectronico(correoElectronico);
        paciente.setNumCelular(numCelular);
        paciente.setGenero(Genero.valueOf(genero.toUpperCase()));
        return this.dao.insertar(paciente);
    }
    
    public PacienteDTO obtenerPorId(Integer personaId){
        return this.dao.obtenerPorId(personaId);
    }
}
