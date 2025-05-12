/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import softmodel.modelos.CitaDTO;
import softmodel.modelos.PacienteDTO;
import java.util.ArrayList;
import java.util.Date;
import softpersistence.dao.PacienteDAO;
import softpersistence.daoImp.PacienteDAOImpl;

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
    
    public ArrayList<CitaDTO> listarCitasPorEspecialidad(String nombreEspecialidad){
        String nombreMedico = null;
        Date fecha = null;
        return listarCitas(nombreEspecialidad, fecha, nombreMedico);
    }
    
    public ArrayList<CitaDTO> listarCitasPorMedico(String nombreMedico){
        String nombreEspecialidad = null;
        Date fecha = null;
        return listarCitas(nombreEspecialidad, fecha, nombreMedico);
    }
    
    public ArrayList<CitaDTO> listarCitasPorEspecialidadYFecha(String nombreEspecialidad, Date fecha){
        String nombreMedico = null;
        return listarCitas(nombreEspecialidad, fecha, nombreMedico);
    }
    
    public ArrayList<CitaDTO> listarCitasPorMedicoYFecha(String nombreMedico, Date fecha){
        String nombreEspecialidad = null;
        return listarCitas(nombreEspecialidad, fecha, nombreMedico);
    }
    
    public ArrayList<CitaDTO> listarCitas(String nombreEspecialidad, Date fecha, String nombreMedico){
        ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
        if(nombreMedico!=null){
            //busca el idPersona por nombre
            //busca en la tabla de citas por id Persona y filtra por fecha devuelve un ArryList
        }else if(nombreEspecialidad!=null){
            //buca el id_especialidad
            //busca en el idPersona por Id_especialidad
            //busca en la tabla de citas por Id Persona y filtra por fecha devuelve un ArryList
        }else{
            //parametros incorrectos
        }
        return citas;
    }
    //devuelve valor mayor que 0 si se realizó correctamente lo solicitado, si es 0 entonces no hizo nada
    //o tal vez solo debería pasar el id?
    public int reservarCita(CitaDTO cita){
        //actualizar cita (Estado: reservado)
        //crear nueva fila de pago (solo id, id_cita, estado: pendiente, fecha_reserva, monto)
        return 2;
    }
    
    public int cancelarCita(CitaDTO cita){
        //actualizar cita (Estado: disponible)
        //eliminar fila de pago
        return 3;
    }
    
    public int pagarCita(CitaDTO cita){
        //actualizar cita (Esatado: Pagado)
        //actualizar fila de pago con los datos del pago y el estado: exitoso, fecha_pago
        return 4;
    }
    
    public void reprogramarCita(){
        //no se como implemnetarlo
        //también se deberían listar las nuevas citas y volver a elegir? pero 
    }
    
    public int actualizarContrasenha(String contrasenha){
        //solo es actualizar la tabla cuenta, ya se tiene el id en la cuenta base
        return 5;
    } //se debería actualizar tmb celular y correo?
}
