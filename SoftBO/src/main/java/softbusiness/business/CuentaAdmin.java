/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import java.util.ArrayList;
import softmodel.modelos.CitaDTO;
import softmodel.modelos.PacienteDTO;
import softmodel.util.Estado;
import softpersistence.dao.CitaDAO;
import softpersistence.daoImp.CitaDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaAdmin extends CuentaBO{
    
    private CitaDAO citaDao;
    
    public CuentaAdmin(Integer id){
        super.setIdCuenta(id);
        this.citaDao =new CitaDAOImpl();
    }
    
    
    public ArrayList<CitaDTO> GenerarReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin){
        ArrayList<CitaDTO> lista = new ArrayList<>();
        lista = citaDao.ReporteResumenGeneral(especialidad, estado, fechaInicio, fechaFin);
        
        return lista;
    }
    
    public ArrayList<CitaDTO> ReporteCitasDelMedico(int idMedico){
        ArrayList<CitaDTO> lista = new ArrayList<>();
        lista = citaDao.listarPorMedico(idMedico);
        
        return lista;
    }
    
    public ArrayList<CitaDTO> ReporteCitasPaciente(PacienteDTO paciente){
        ArrayList<CitaDTO> lista = new ArrayList<>();
        lista = citaDao.listarPorPaciente( paciente);
        
        return lista;
    }
    
    
    
}
