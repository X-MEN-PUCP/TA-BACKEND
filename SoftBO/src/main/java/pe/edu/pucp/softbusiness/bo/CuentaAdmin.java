/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;

import java.util.ArrayList;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.dao.CuentaDAO;
import pe.edu.pucp.softpersistence.dao.EspecialidadDAO;
import pe.edu.pucp.softpersistence.dao.HorarioDAO;
import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softpersistence.daoImp.CitaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.CuentaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.EspecialidadDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.HorarioDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.MedicoDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaAdmin extends CuentaBO{
    private CitaDAO citaDAO;
    private MedicoDAO medicoDAO;
    private EspecialidadDAO especialidadDAO;
    private CuentaDAO cuentaDAO;
    private HorarioDAO horarioDAO;
    
    public CuentaAdmin(Integer id){
        super.setIdCuenta(id);
        this.citaDAO= new CitaDAOImpl();
        this.medicoDAO= new MedicoDAOImpl();
        this.cuentaDAO= new CuentaDAOImpl();
        this.especialidadDAO = new EspecialidadDAOImpl();
        this.horarioDAO = new HorarioDAOImpl();
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaAdmin.QuienSoy()");
    }
    
    public ArrayList<EspecialidadDTO> listaDeEspecialidades() {
        return especialidadDAO.listarTodos();
    }
    
    public int insertarNuevoMedico(MedicoDTO medico){
        EspecialidadDTO especialidad = medico.getEspecialidad();
        CuentaDTO cuenta = medico.getCuenta();
        String numDocCuenta = cuenta.getNumeroDocumento();
        CuentaDTO cuentaEncontrada = cuentaDAO.buscarPorNumeroDocumento(numDocCuenta);
        String nombreEspe = especialidad.getNombreEspecialidad();
        EspecialidadDTO especialidadEncontrada = especialidadDAO.buscarPorNombre(nombreEspe);
        if(cuentaEncontrada==null){
            Integer idCuenta=cuentaDAO.insertar(cuenta);
            cuenta.setIdCuenta(idCuenta);
            medico.setCuenta(cuenta);
            if(especialidadEncontrada==null){
                System.out.println("NuevaEspecialidad");
                Integer idNuevaEspecialidad = especialidadDAO.insertar(especialidad);
                EspecialidadDTO especialidadNueva = especialidadDAO.obtenerPorId(idNuevaEspecialidad);
                medico.setEspecialidad(especialidadNueva);
            }else{
                System.out.println("Especialidad registrada antes");
                medico.setEspecialidad(especialidadEncontrada);
            }
            return medicoDAO.insertar(medico);
        }else{
            System.out.println("Ya existe una cuenta con ese documento de identidad");
        }
        return -1;
    }
    
    public int insertarNuevaCita(CitaDTO cita){
        HorarioDTO horario = cita.getHorario();
        HorarioDTO horarioBuscado = horarioDAO.buscarHorario(horario);
        Integer idHorario;
        if(horarioBuscado==null){
            idHorario= horarioDAO.insertar(horario);
            HorarioDTO nuevoHorario = horarioDAO.obtenerPorId(idHorario);
            cita.setHorario(nuevoHorario);
        }else{
            cita.setHorario(horarioBuscado);
        }
        return citaDAO.insertar(cita);
    }
    
    
    
    public ArrayList<CitaDTO> GenerarReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin){
        ArrayList<CitaDTO> lista;
        lista = citaDAO.ReporteResumenGeneral(especialidad, estado, fechaInicio, fechaFin);
        return lista;
    }
    
    public ArrayList<CitaDTO> ReporteCitasDelMedico(int idMedico){
        ArrayList<CitaDTO> lista;
        lista = citaDAO.listarPorMedico(idMedico);
        
        return lista;
    }
    
    public ArrayList<CitaDTO> ReporteCitasPaciente(PacienteDTO paciente){
        ArrayList<CitaDTO> lista;
        lista = citaDAO.listarPorPaciente( paciente);
        
        return lista;
    }
    
            
           

}
