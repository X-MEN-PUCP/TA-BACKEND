/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbusiness.bo.AdminBO;
import pe.edu.pucp.softmodel.modelos.AdminDTO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softmodel.util.Rol;
import pe.edu.pucp.softmodel.util.TipoDocumento;
import pe.edu.pucp.softpersistence.daoImp.AdminDAOImpl;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Cesar
 */
@WebService(serviceName = "AdminWS")
public class AdminWS {

    private AdminBO adminBO;
    
    public AdminWS(){
        adminBO = new AdminBO();
    }

    @WebMethod(operationName = "buscarAdminPorIdCuenta")
    public AdminDTO buscarAdminPorIdCuenta(@WebParam(name = "idCuenta") int idCuenta) {
        return adminBO.buscarPorIdCuenta(idCuenta);
    }

    @WebMethod(operationName = "listaDeEspecialidades")
    public ArrayList<EspecialidadDTO> listaDeEspecialidades() {
        return adminBO.listaDeEspecialidades();
    }

    @WebMethod(operationName = "insertarNuevaEspecialidad")
    public int insertarNuevaEspecialidad(
        @WebParam(name = "nombreEspecialidad") String nombreEspecialidad,
        @WebParam(name = "precioConsulta") double precioConsulta
    ) {
        EspecialidadDTO especialidad = new EspecialidadDTO();
        especialidad.setNombreEspecialidad(nombreEspecialidad);
        especialidad.setPrecioConsulta(precioConsulta);
        return adminBO.insertarNuevaEspecialidad(especialidad);
    }

    @WebMethod(operationName = "insertarNuevoMedico")
    public int insertarNuevoMedico(
        @WebParam(name = "nombres") String nombres,
        @WebParam(name = "apellidoPaterno") String apellidoPaterno,
        @WebParam(name = "apellidoMaterno") String apellidoMaterno,
        @WebParam(name = "genero") String genero,
        @WebParam(name = "fechaNacimiento") Date fechaNacimiento,
        @WebParam(name = "correoElectronico") String correoElectronico,
        @WebParam(name = "numCelular") String numCelular,
        @WebParam(name = "numeroDocumento") String numeroDocumento,
        @WebParam(name = "usuario") String rol,
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "tipoDoc") String tipoDoc,
        @WebParam(name = "nombreEspecialidad") String nombreEspecialidad
    ) {
        MedicoDTO medico = new MedicoDTO();
        medico.setNombres(nombres);
        medico.setApellido_paterno(apellidoPaterno);
        medico.setApellido_materno(apellidoMaterno);
        medico.setGenero(Genero.valueOf(genero.toUpperCase()));
        medico.setFechaNaciemiento(fechaNacimiento);
        medico.setCorreoElectronico(correoElectronico);
        medico.setNumCelular(numCelular);

        CuentaDTO cuenta = new CuentaDTO();
        cuenta.setNumeroDocumento(numeroDocumento);
        cuenta.setRol(Rol.valueOf(rol.toUpperCase()));
        cuenta.setContrasenha(contrasena);
        cuenta.setTipoDocumento(TipoDocumento.valueOf(tipoDoc.toUpperCase()));
        medico.setCuenta(cuenta);
        

        EspecialidadDTO especialidad = new EspecialidadDTO();
        especialidad.setNombreEspecialidad(nombreEspecialidad);
        medico.setEspecialidad(especialidad);

        return adminBO.insertarNuevoMedico(medico);
    }

    @WebMethod(operationName = "insertarNuevaCita")
    public int insertarNuevaCita(
        @WebParam(name = "idMedico") int idMedico,
        @WebParam(name = "idEspecialidad") int idEspecialidad,
        @WebParam(name = "fecha") LocalDate fecha,
        @WebParam(name = "horaInicio") LocalTime horaInicio,
        @WebParam(name = "horaFin") LocalTime horaFin
    ) {
        CitaDTO cita = new CitaDTO();
        MedicoDTO medico = new MedicoDTO();
        medico.setIdPersona(idMedico);
        cita.setMedico(medico);

        EspecialidadDTO especialidad = new EspecialidadDTO();
        especialidad.setIdEspecialidad(idEspecialidad);
        medico.setEspecialidad(especialidad);

        HorarioDTO horario = new HorarioDTO();
        horario.setFecha(fecha);
        horario.setHoraInicio(horaInicio);
        horario.setHoraInicio(horaFin);
        cita.setHorario(horario);

        return adminBO.insertarNuevaCita(cita);
    }

    @WebMethod(operationName = "generarReporteResumenGeneral")
    public ArrayList<CitaDTO> generarReporteResumenGeneral(
        @WebParam(name = "idEspecialidad") int idEspecialidad,
        @WebParam(name = "estado") String estado,
        @WebParam(name = "fechaInicio") Date fechaInicio,
        @WebParam(name = "fechaFin") Date fechaFin
    ) {
        return adminBO.GenerarReporteResumenGeneral(
            idEspecialidad,
            Estado.valueOf(estado.toUpperCase()),
            fechaInicio,
            fechaFin
        );
    }

    @WebMethod(operationName = "reporteCitasDelMedico")
    public ArrayList<CitaDTO> reporteCitasDelMedico(@WebParam(name = "idMedico") int idMedico) {
        return adminBO.ReporteCitasDelMedico(idMedico);
    }

    @WebMethod(operationName = "reporteCitasPaciente")
    public ArrayList<CitaDTO> reporteCitasPaciente(@WebParam(name = "idPersona") int idPersona) {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setIdPersona(idPersona);
        return adminBO.ReporteCitasPaciente(paciente);
    }
}