/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;

import java.time.LocalDate;
import java.time.ZoneId;
import softmodel.modelos.CitaDTO;
import softmodel.modelos.PacienteDTO;
import java.util.ArrayList;
import java.util.Date;
import softdbmanager.util.Cifrado;
import softmodel.modelos.CuentaDTO;
import softmodel.modelos.EspecialidadDTO;
import softmodel.modelos.MedicoDTO;
import softmodel.modelos.PagosDTO;
import softmodel.util.Estado;
import softpersistence.dao.CitaDAO;
import softpersistence.dao.CuentaDAO;
import softpersistence.dao.EspecialidadDAO;
import softpersistence.dao.MedicoDAO;
import softpersistence.dao.PacienteDAO;
import softpersistence.dao.PagosDAO;
import softpersistence.daoImp.CitaDAOImpl;
import softpersistence.daoImp.CuentaDAOImpl;
import softpersistence.daoImp.EspecialidadDAOImpl;
import softpersistence.daoImp.MedicoDAOImpl;
import softpersistence.daoImp.PacienteDAOImpl;
import softpersistence.daoImp.PagosDAOImpl;

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
    private CuentaDAO cuentaDAO;
    private PagosDAO pagosDAO;
    
    public CuentaPaciente(Integer id){
        super.setIdCuenta(id);
        pacienteDAO = new PacienteDAOImpl();
        PacienteDTO paciente = pacienteDAO.buscarPorIdCuenta(id);
        this.idHistoria = paciente.getHistoriaClinica().getIdHistoriaClinica();
        super.setIdPersona(paciente.getIdPersona());
        this.especialidadDAO= new EspecialidadDAOImpl();
        this.medicoDAO= new MedicoDAOImpl();
        this.citaDAO = new CitaDAOImpl();
        this.cuentaDAO= new CuentaDAOImpl();
        this.pagosDAO = new PagosDAOImpl();
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
        ArrayList<CitaDTO> citas = new ArrayList<>();
        ArrayList<CitaDTO> citasPorMedico;
        if(idMedico!=null){
            //busca en la tabla de citas por id Persona y filtra por fecha devuelve un ArryList
            citasPorMedico = citaDAO.listarPorIdMedicoEstadoFecha(idMedico, Estado.DISPONIBLE, fecha);//generico
            citas.addAll(citasPorMedico);
        }else if(idEspecialidad!=null){
            //busca en el idPersona por Id_especialidad
            ArrayList<MedicoDTO> medicos = medicoDAO.listarPorIdEspecialidad(idEspecialidad);//generico
            //busca en la tabla de citas por Id Persona y filtra por fecha devuelve un ArryList
            for(MedicoDTO medico : medicos){
                Integer idM = medico.getIdPersona();
                citasPorMedico = citaDAO.listarPorIdMedicoEstadoFecha(idM, Estado.DISPONIBLE, fecha);
                if(!citasPorMedico.isEmpty())
                    citas.addAll(citasPorMedico);
            }
        }else{
            System.out.println("Debe seleccionar una especialidad o un médico. Error listar Citas");
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
        cita.setEstado(Estado.DISPONIBLE);
        citaDAO.modificar(cita);
        PagosDTO pago = pagosDAO.buscarPorIdCita(cita.getIdCita());
        //eliminar fila de pago
        pagosDAO.eliminar(pago.getIdPago());
        return 3;
    }
    
    public int pagarCita(CitaDTO cita){
        //actualizar cita (Esatado: Pagado)
        cita.setEstado(Estado.PAGADO);
        citaDAO.modificar(cita);
        PagosDTO pago = pagosDAO.buscarPorIdCita(cita.getIdCita());
        //actualizar fila de pago con los datos del pago y el estado: exitoso, fecha_pago
        
        LocalDate localDate = LocalDate.now(); // fecha actual sin hora
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pago.setFechaPago(date);
        return 4;
    }
    
    public void reprogramarCita(){
        //no se como implemnetarlo
        //también se deberían listar las nuevas citas y volver a elegir? pero 
    }
    
    public int actualizarContrasenha(String contrasenha){
        //solo es actualizar la tabla cuenta, ya se tiene el id en la cuenta base
        CuentaDTO cuenta = new CuentaDTO();
        cuenta = cuentaDAO.obtenerPorID(super.getIdCuenta());
        cuenta.setContrasenha(Cifrado.cifrarMD5(contrasenha));
        cuentaDAO.modificar(cuenta);
        
        
        return 5;
    } //se debería actualizar tmb celular y correo?
}
