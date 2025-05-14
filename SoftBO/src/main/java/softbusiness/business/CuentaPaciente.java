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
import softmodel.util.EstadoPago;
import softmodel.util.MetodoPago;
import softpersistence.dao.CitaDAO;
import softpersistence.dao.CuentaDAO;
import softpersistence.dao.EspecialidadDAO;
import softpersistence.dao.HorarioDAO;
import softpersistence.dao.MedicoDAO;
import softpersistence.dao.PacienteDAO;
import softpersistence.dao.PagosDAO;
import softpersistence.daoImp.CitaDAOImpl;
import softpersistence.daoImp.CuentaDAOImpl;
import softpersistence.daoImp.EspecialidadDAOImpl;
import softpersistence.daoImp.HorarioDAOImpl;
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
    private HorarioDAO horarioDAO;
    
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
        this.horarioDAO = new HorarioDAOImpl();
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
        System.out.println("Modificando estado de cita");
        cita.setEstado(Estado.RESERVADO);
        citaDAO.modificar(cita);
        //crear nueva fila de pago (solo id, id_cita, estado: pendiente, fecha_reserva, monto)
        System.out.println("Modifica estado de cita");
        PagosDTO pago = new PagosDTO();
        pago.setCita(cita);
        pago.setEstado(EstadoPago.PENDIENTE);
        LocalDate localDate = LocalDate.now(); // fecha actual sin hora
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pago.setFechaReserva(date);
        pago.setMonto(cita.getMedico().getEspecialidad().getPrecioConsulta());
        return pagosDAO.modificar(pago);
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
    
    public int pagarCita(CitaDTO cita,String titular,String numTarjeta,String cvv, MetodoPago metodo
    , LocalDate fecha){
        //actualizar cita (Esatado: Pagado)
        cita.setEstado(Estado.PAGADO);
        citaDAO.modificar(cita);
        PagosDTO pago = pagosDAO.buscarPorIdCita(cita.getIdCita());
        //actualizar fila de pago con los datos del pago y el estado: exitoso, fecha_pago
        
        //DATOS DE PAGO
        pago.setTitular(Cifrado.cifrarMD5(titular));
        pago.setNumTarjeta(Cifrado.cifrarMD5(numTarjeta));
        pago.setMetodoPago(metodo);
        pago.setFechaExpiracion(fecha);
        pago.setCVV(Cifrado.cifrarMD5(cvv));
        
        //estado y hora
        pago.setEstado(EstadoPago.EXITOSO);
        LocalDate localDate = LocalDate.now(); // fecha actual sin hora
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pago.setFechaPago(date);
        return pagosDAO.modificar(pago);
    }
    
    public int reprogramarCita(Integer idCitaAnterior, Integer idCitaNueva){
        CitaDTO citaAnterior = citaDAO.obtenerPorId(idCitaAnterior);
        CitaDTO citaNueva = citaDAO.obtenerPorId(idCitaNueva);
        citaAnterior.setEstado(Estado.DISPONIBLE);
        citaNueva.setEstado(Estado.PAGADO);
        citaDAO.modificar(citaAnterior);
        citaDAO.modificar(citaNueva);
        PagosDTO pago = pagosDAO.buscarPorIdCita(idCitaAnterior);
        pago.setCita(citaNueva);
        return pagosDAO.modificar(pago);
    }
    
    public int actualizarContrasenha(String contrasenha){
        //solo es actualizar la tabla cuenta, ya se tiene el id en la cuenta base
        CuentaDTO cuenta;
        cuenta = cuentaDAO.obtenerPorID(super.getIdCuenta());
        cuenta.setContrasenha(Cifrado.cifrarMD5(contrasenha));
        return cuentaDAO.modificar(cuenta);
    } //se debería actualizar tmb celular y correo?
}
