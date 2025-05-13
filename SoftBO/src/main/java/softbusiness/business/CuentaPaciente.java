/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import softmodel.modelos.CitaDTO;
import softmodel.modelos.PacienteDTO;
import java.util.ArrayList;
import java.util.Date;
import softmodel.modelos.EspecialidadDTO;
import softmodel.modelos.MedicoDTO;
import softmodel.util.Estado;
import softpersistence.dao.CitaDAO;
import softpersistence.dao.EspecialidadDAO;
import softpersistence.dao.MedicoDAO;
import softpersistence.dao.PacienteDAO;
import softpersistence.daoImp.CitaDAOImpl;
import softpersistence.daoImp.EspecialidadDAOImpl;
import softpersistence.daoImp.MedicoDAOImpl;
import softpersistence.daoImp.PacienteDAOImpl;

/**
 *
 * @author Mcerv
 */
public class CuentaPaciente extends CuentaBO{
    private Integer idHistoria;
    private PacienteDAO pacienteDAO;
    private EspecialidadDAO especialidadDAO;
    private MedicoDAO medicoDAO;
    private CitaDAO citaDAO;
    
    public CuentaPaciente(Integer id){
        super.setIdCuenta(id);
        pacienteDAO = new PacienteDAOImpl();
        PacienteDTO paciente = pacienteDAO.buscarPorIdCuenta(id);
        this.idHistoria = paciente.getHistoriaClinica().getIdHistoriaClinica();
        super.setIdPersona(paciente.getIdPersona());
        this.especialidadDAO= new EspecialidadDAOImpl();
        this.medicoDAO= new MedicoDAOImpl();
        this.citaDAO = new CitaDAOImpl();
    }
    
    @Override
    public void QuienSoy(){
        System.out.println("com.mycompany.softbo.CuentaPaciente.QuienSoy()");
    }

    
    public ArrayList<EspecialidadDTO> listaDeEspecialidades(){
        return especialidadDAO.listarTodos();
    }
    
    public ArrayList<MedicoDTO> listaDeMedicoPorEspecialidad (Integer idEspecialidad){
        return medicoDAO.listarPorIdEspecialidad(idEspecialidad);
    }
    
    public ArrayList<CitaDTO> listarCitasPorEspecialidadYFecha(Integer idEspecialidad, Date fecha){
        Integer idMedico = null;
        return listarCitas(idEspecialidad, fecha, idMedico);
    }
    
    public ArrayList<CitaDTO> listarCitas(Integer idEspecialidad, Date fecha, Integer idMedico){
        ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
        ArrayList<CitaDTO> citasPorMedico;
        if(idMedico!=null){
            //busca en la tabla de citas por id Persona y filtra por fecha devuelve un ArryList
            citasPorMedico = citaDAO.listarPorIdMedico(idMedico);//generico
            citas.addAll(citasPorMedico);
        }else if(idEspecialidad!=null){
            //busca en el idPersona por Id_especialidad
            ArrayList<MedicoDTO> medicos = medicoDAO.listarPorIdEspecialidad(idEspecialidad);//generico
            //busca en la tabla de citas por Id Persona y filtra por fecha devuelve un ArryList
            
            for(MedicoDTO medico : medicos){
                Integer idM = medico.getIdPersona();
                citasPorMedico = citaDAO.listarPorIdMedicoYEstado(idM, Estado.DISPONIBLE);
                if(!citasPorMedico.isEmpty())
                    citas.addAll(citasPorMedico);
            }
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
