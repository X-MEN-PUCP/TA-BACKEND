/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softdbmanager.util.Cifrado;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.modelos.PagoDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softmodel.util.EstadoPago;
import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softmodel.util.MetodoPago;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.dao.CuentaDAO;
import pe.edu.pucp.softpersistence.dao.EspecialidadDAO;
import pe.edu.pucp.softpersistence.dao.HistoriaClinicaDAO;
import pe.edu.pucp.softpersistence.dao.HorarioDAO;
import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softpersistence.dao.PacienteDAO;
import pe.edu.pucp.softpersistence.dao.PagoDAO;
import pe.edu.pucp.softpersistence.daoImp.CitaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.CuentaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.EspecialidadDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.HistoriaClinicaDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.HorarioDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.MedicoDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.PacienteDAOImpl;
import pe.edu.pucp.softpersistence.daoImp.PagoDAOImpl;

/**
 *
 * @author Cesar
 */
public class PacienteBO {
    
    private PacienteDAO dao;
    private EspecialidadDAO especialidadDAO;
    private MedicoDAO medicoDAO;
    private CitaDAO citaDAO;
    private CuentaDAO cuentaDAO;
    private PagoDAO pagosDAO;
    private HorarioDAO horarioDAO;
    private HistoriaClinicaDAO historiaCilicaDAO;
    
    public PacienteBO(){
        dao=new PacienteDAOImpl();
        this.especialidadDAO = new EspecialidadDAOImpl();
        this.medicoDAO = new MedicoDAOImpl();
        this.citaDAO = new CitaDAOImpl();
        this.cuentaDAO = new CuentaDAOImpl();
        this.pagosDAO = new PagoDAOImpl();
        this.horarioDAO = new HorarioDAOImpl();
        this.historiaCilicaDAO = new HistoriaClinicaDAOImpl();
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
    
    public ArrayList<EspecialidadDTO> listaDeEspecialidades() {
        return especialidadDAO.listarTodos();
    }

//    public ArrayList<MedicoDTO> listaDeMedicoPorEspecialidad(Integer idEspecialidad) {
//        return medicoDAO.listarPorIdEspecialidad(idEspecialidad);
//    }
//
//    public ArrayList<CitaDTO> listarCitasPorEspecialidadYFecha(Integer idEspecialidad, Date fecha) {
//        Integer idMedico = null;
//        return listarCitas(idEspecialidad, fecha, idMedico);
//    }
//
    public ArrayList<CitaDTO> listarCitas(Integer idEspecialidad, LocalDate fecha, Integer idMedico) {
        ArrayList<CitaDTO> citas = new ArrayList<>();
        ArrayList<CitaDTO> citasPorMedico;
       
        if (idMedico != null || idEspecialidad != null) {
           citas = this.citaDAO.buscarCitasDisponibles(idEspecialidad, idMedico, fecha);
        } else {
            System.out.println("Debe seleccionar una especialidad o un médico. Error listar Citas");
        }
        return citas;
    }

    //devuelve valor mayor que 0 si se realizó correctamente lo solicitado, si es 0 entonces no hizo nada
    //o tal vez solo debería pasar el id?
    public int reservarCita(CitaDTO cita, int id) {//es id de cuenta o id persona?
        //actualizar cita (Estado: reservado)
        System.out.println("Modificando estado de cita");
        cita.setEstado(Estado.RESERVADO);
        //Integer id = super.getIdCuenta();
        PacienteDTO paciente = dao.buscarPorIdCuenta(id);
        Integer idPaciente = paciente.getIdPersona();
        HistoriaClinicaDTO historia = this.historiaCilicaDAO.obtenerPorIdPaciente(idPaciente);
        cita.setHistoriaClinicaPaciente(historia);
        if (historia == null || historia.getIdHistoriaClinica() == null) {
            System.out.println("Error: Historia clínica no encontrada para el paciente");
            return 0;
        }
        System.out.println("ID HISTORIA" + cita.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        citaDAO.modificar(cita);
        //crear nueva fila de pago (solo id, id_cita, estado: pendiente, fecha_reserva, monto)
        MedicoDTO medico = medicoDAO.obtenerPorId(cita.getMedico().getIdPersona());
        EspecialidadDTO especialidad = new EspecialidadDTO();
        EspecialidadDAOImpl espdao = new EspecialidadDAOImpl();
        especialidad = espdao.obtenerPorId(medico.getEspecialidad().getIdEspecialidad());
        System.out.println("Modifica estado de cita");
        PagoDTO pago = new PagoDTO();

        pago.setCita(cita);
        //pago.setMetodoPago(null);
        pago.setEstado(EstadoPago.PENDIENTE);
        LocalDate localDate = LocalDate.now(); // fecha actual sin hora
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pago.setFechaReserva(date);
        pago.setMonto(especialidad.getPrecioConsulta());
        return pagosDAO.insertar(pago);
    }

    public int cancelarCita(CitaDTO cita) {
        //actualizar cita (Estado: disponible)
        cita.setEstado(Estado.DISPONIBLE);
        System.out.println("ID HISTORIA " + cita.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        int cancel = cita.getIdCita();
        citaDAO.modificar(cita);
        PagoDTO pago = pagosDAO.buscarPorIdCita(cita.getIdCita());
        //eliminar fila de pago
        pagosDAO.eliminar(pago.getIdPago());
        return cancel;
    }

    public int pagarCita(CitaDTO cita, String titular, String numTarjeta, String cvv, MetodoPago metodo,
             LocalDate fecha) {
        //actualizar cita (Esatado: Pagado)
        cita.setEstado(Estado.PAGADO);
        citaDAO.modificar(cita);
        PagoDTO pago = pagosDAO.buscarPorIdCita(cita.getIdCita());
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

    public int reprogramarCita(Integer idCitaAnterior, Integer idCitaNueva) {
        CitaDTO citaAnterior = citaDAO.obtenerPorId(idCitaAnterior);
        CitaDTO citaNueva = citaDAO.obtenerPorId(idCitaNueva);
        HistoriaClinicaDTO historiaNueva = new HistoriaClinicaDTO();
        historiaNueva.setIdHistoriaClinica(citaAnterior.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        citaNueva.setHistoriaClinicaPaciente(historiaNueva);
        citaAnterior.setEstado(Estado.DISPONIBLE);
        citaNueva.setEstado(Estado.PAGADO);
        System.out.println("ID HISTORIA cita anterior " + citaAnterior.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        System.out.println("ID HISTORIA cita nueva " + citaNueva.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        
  
        citaAnterior.setHistoriaClinicaPaciente(null);
        citaDAO.modificar(citaAnterior);
        
        citaDAO.modificar(citaNueva);
        PagoDTO pago = pagosDAO.buscarPorIdCita(idCitaAnterior);
        pago.setCita(citaNueva);
        return pagosDAO.modificar(pago);
    }

    
    
    public ArrayList<CitaDTO> listarCitasPorPersona(int idPersona){
        ArrayList<CitaDTO> citas = null;
        PacienteDTO paciente = this.dao.obtenerPorId(idPersona);
        if(paciente!=null){
            citas = this.citaDAO.listarPorPaciente(paciente);
        }
        return citas;
    }
}
