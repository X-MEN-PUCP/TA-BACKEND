/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softpersistence.daoImp.CitaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.MedicoDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaMedico extends CuentaBO{
    private Integer idEspecialidad;
    private MedicoDAO medicoDAO;
    private CitaDAO citaDAO;
    
    public CuentaMedico(Integer id){
      super.setIdCuenta(id);
      medicoDAO = new MedicoDAOImpl();
      citaDAO = new CitaDAOImpl();
      MedicoDTO medico = medicoDAO.buscarPorIdCuenta(id); //se debería verificar que se encuentre
      this.idEspecialidad = medico.getEspecialidad().getIdEspecialidad();
      super.setIdPersona(medico.getIdPersona());
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaMedico.QuienSoy()");
    }
    
    public ArrayList<CitaDTO> listarCitasProgramadas(Date fecha){
        ArrayList<CitaDTO> citas;
        //ya tengo el id_persona en la clase base
        //busco las citas con estado: Pagado, Id_persona, fecha
        int id = super.getIdPersona();
        citas = citaDAO.listarPorIdMedicoEstadoFecha(id, Estado.PAGADO, fecha);
        
        return citas;
    }
    
    public ArrayList<CitaDTO> listarCitasDisponibles(Date fecha){
        ArrayList<CitaDTO> citas;
        //ya tengo el id_persona en la clase base
        //busco las citas con estado: Disponible, Id_persona, fecha
        int id = super.getIdPersona();
        citas = citaDAO.listarPorIdMedicoEstadoFecha(id, Estado.DISPONIBLE, fecha);
        return citas;
    }
    
    public ArrayList<CitaDTO> listarCitas(Date fecha){
        ArrayList<CitaDTO> citas = new ArrayList<>();
        ArrayList<CitaDTO> citasProg= this.listarCitasProgramadas(fecha);
        ArrayList<CitaDTO> citasDis= this.listarCitasDisponibles(fecha);
        citas.addAll(citasProg);
        citas.addAll(citasDis);
        return citas;
    }
}
