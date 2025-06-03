/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softpersistence.daoImp.CitaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.MedicoDAOImpl;

/**
 *
 * @author salva
 */
public class MedicoBO {
    
    private MedicoDAO dao;
    private CitaDAO citaDao;
    
    public MedicoBO(){
        dao = new MedicoDAOImpl();
        citaDao = new CitaDAOImpl();
    }
    
    
//    public MedicoDTO buscarPorIdCuenta(int idCuenta);
    
    public MedicoDTO buscarPoridCuenta(Integer idcuenta){
        return this.dao.buscarPorIdCuenta(idcuenta);
    }
    
    public Integer insertar(Integer idCuenta,String nombres,String ap_paterno,String ap_materna,Date fechaNaciemiento,
            String correo, String genero,Integer idEspecialidad, Integer codMedico){
        
        MedicoDTO medico = new MedicoDTO();
                 
        
        CuentaDTO cuenta = new CuentaDTO();
        cuenta.setIdCuenta(idCuenta);
        
        
        medico.setCuenta(cuenta);
        medico.setNombres(nombres);
        medico.setApellido_paterno(ap_paterno);
        medico.setApellido_materno(ap_materna);
        medico.setFechaNaciemiento(fechaNaciemiento);
        medico.setCorreoElectronico(correo);
        medico.setGenero(Genero.valueOf(genero.toUpperCase()));
        
        EspecialidadDTO especialidad = new EspecialidadDTO();
        especialidad.setIdEspecialidad(idEspecialidad);
        
        medico.setEspecialidad(especialidad);
        medico.setCodMedico(codMedico);;
        return this.dao.insertar(medico);
        
    }
    
    public ArrayList<MedicoDTO> listarPorIdEspecialidad(int idEspecialidad){
        return this.dao.listarPorIdEspecialidad(idEspecialidad);
    }
    public MedicoDTO obtenerPorId(Integer id){
        return this.dao.obtenerPorId(id);
    }
    public Integer eliminar(Integer id){
        return this.eliminar(id);
    }
    
    public ArrayList<CitaDTO> listarCitasProgramadas(Integer codMedico){
        ArrayList<CitaDTO> citas;
        //ya tengo el id_persona en la clase base
        //busco las citas con estado: Pagado, Id_persona, fecha
        //int id = super.getIdPersona();
        citas = citaDao.listarCitasProgramadas(codMedico);
        
        return citas;
    }
    
    
}
