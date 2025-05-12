/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import com.mycompany.softmodel.CitaDTO;
import com.mycompany.softmodel.EspecialidadDTO;
import com.mycompany.softmodel.MedicoDTO;
import java.util.ArrayList;
import java.util.Date;
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
    
    public ArrayList<CitaDTO> listarCitasProgramadas(Date fecha){
        ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
        //ya tengo el id_persona en la clase base
        //busco las citas con estado: Pagado, Id_persona, fecha
        return citas;
    }
    
    public ArrayList<CitaDTO> listarCitasDisponibles(Date fecha){
        ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
        //ya tengo el id_persona en la clase base
        //busco las citas con estado: Disponible, Id_persona, fecha
        return citas;
    }
    
    public ArrayList<CitaDTO> listarCitas(Date fecha){
        ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
        ArrayList<CitaDTO> citasProg= this.listarCitasProgramadas(fecha);
        ArrayList<CitaDTO> citasDis= this.listarCitasDisponibles(fecha);
        citas.addAll(citasProg);
        citas.addAll(citasDis);
        return citas;
    }
}
