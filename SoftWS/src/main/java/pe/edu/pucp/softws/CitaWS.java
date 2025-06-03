/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbusiness.bo.citaBO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;
/**
 *
 * @author salva
 */
@WebService(serviceName = "CitaWS")
public class CitaWS {

    private final citaBO cita;

    public CitaWS() {
        cita = new citaBO();
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "Insertar")
    public Integer insertarCita(@WebParam(name = "idHorario") Integer idHorario,
            @WebParam(name = "idPersona") Integer idPersona,
            @WebParam(name = "observaciones") String observaciones,
            @WebParam(name = "idHistoria") Integer idHistoria, @WebParam(name = "estado") String estado) {
        return cita.insertar(idHorario, idPersona, observaciones, idHistoria, estado);
    }

    @WebMethod(operationName = "Modificar")
    public Integer modificarCita(@WebParam(name = "idCita") Integer idCita,
             @WebParam(name = "idHorario") Integer idHorario,
            @WebParam(name = "idPersona") Integer idPersona,
            @WebParam(name = "observaciones") String observaciones,
            @WebParam(name = "idHistoria") Integer idHistoria, @WebParam(name = "estado") String estado) {
        return cita.modificar(idCita, idHorario, idPersona, observaciones, idHistoria, estado);
    }

    @WebMethod(operationName = "Eliminar")
    public Integer eliminarCita(@WebParam(name = "idCita") Integer idCita) {
        return cita.eliminar(idCita);
    }

    @WebMethod(operationName = "ObtenerPorId")
    public CitaDTO obtenerCitaPorId(@WebParam(name = "idCita") Integer idCita) {
        return cita.obtenerPorId(idCita);
    }

    @WebMethod(operationName = "ListarTodos")
    public ArrayList<CitaDTO> listarTodasLasCitas() {
        return cita.listarTodos();
    }

    @WebMethod(operationName = "ListarPorIdMedico")
    public ArrayList<CitaDTO> listarCitasPorIdMedico(@WebParam(name = "idMedico") Integer idMedico) {
        return cita.listarPorIdMedico(idMedico);
    }

    @WebMethod(operationName = "ListarPorIdMedicoEstadoFecha")
    public ArrayList<CitaDTO> listarCitasPorIdMedicoEstadoFecha(
            @WebParam(name = "idMedico") Integer idMedico,
            @WebParam(name = "estado") String estado,
            @WebParam(name = "fecha") Date fecha) {
        return cita.listarPorIdMedicoEstadoFecha(idMedico, Estado.valueOf(estado), fecha);
    }

    @WebMethod(operationName = "ReporteResumenGeneral")
    public ArrayList<CitaDTO> reporteResumenGeneral(
            @WebParam(name = "idEspecialidad") Integer idEspecialidad,
            @WebParam(name = "estado") String estado,
            @WebParam(name = "fechaInicio") Date fechaInicio,
            @WebParam(name = "fechaFin") Date fechaFin) {
        return cita.ReporteResumenGeneral(idEspecialidad, Estado.valueOf(estado), fechaInicio, fechaFin);
    }

    @WebMethod(operationName = "ListarPorPaciente")
    public ArrayList<CitaDTO> listarCitasPorPaciente(@WebParam(name = "idPaciente") Integer idPaciente) {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setIdPersona(idPaciente);
        return cita.listarPorPaciente(paciente);
    }

    @WebMethod(operationName = "BuscarCitasDisponibles")
    public ArrayList<CitaDTO> buscarCitasDisponibles(
            @WebParam(name = "idEspecialidad") Integer idEspecialidad,
            @WebParam(name = "idMedico") Integer idMedico,
            @WebParam(name = "fecha") LocalDate fecha) {
        return cita.buscarCitasDisponibles(idEspecialidad, idMedico, fecha);
    }

}
