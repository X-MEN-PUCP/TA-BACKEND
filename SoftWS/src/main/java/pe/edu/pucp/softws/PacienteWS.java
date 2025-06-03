/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbusiness.bo.PacienteBO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.MetodoPago;

/**
 *
 * @author Cesar
 */
@WebService(serviceName = "PacienteWS")
public class PacienteWS {

    /**
     * This is a sample web service operation
     */
    private final PacienteBO pacienteBO;
    
    public PacienteWS(){
        pacienteBO=new PacienteBO();
    }
    
    @WebMethod(operationName = "insertarPaciente")
    public int insertarPaciente(
        @WebParam(name = "idPersona") int idPersona,
        @WebParam(name = "idCuenta") int idCuenta,
        @WebParam(name = "nombres") String nombres,
        @WebParam(name = "apellidoPaterno") String apellidoPaterno,
        @WebParam(name = "apellidoMaterno") String apellidoMaterno,
        @WebParam(name = "fechaNacimiento") Date fechaNacimiento,
        @WebParam(name = "correoElectronico") String correoElectronico,
        @WebParam(name = "numCelular") String numCelular,
        @WebParam(name = "genero") String genero
    ) {
        return pacienteBO.insertar(idPersona, idCuenta, nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, correoElectronico, numCelular, genero);
    }

    @WebMethod(operationName = "obtenerPacientePorId")
    public PacienteDTO obtenerPacientePorId(@WebParam(name = "personaId") int personaId) {
        return pacienteBO.obtenerPorId(personaId);
    }

    @WebMethod(operationName = "buscarPacientePorIdCuenta")
    public PacienteDTO buscarPacientePorIdCuenta(@WebParam(name = "idCuenta") int idCuenta) {
        return pacienteBO.buscarPorIdCuenta(idCuenta);
    }
    
    @WebMethod(operationName = "listarEspecialidades")
    public ArrayList<EspecialidadDTO> listarEspecialidades() {
        return pacienteBO.listaDeEspecialidades();
    }
    
    @WebMethod(operationName = "listarCitasDisponibles")
    public ArrayList<CitaDTO> listarCitasDisponibles(@WebParam(name = "idEspecialidad") Integer idEspecialidad, 
            @WebParam(name = "fecha") LocalDate fecha, 
            @WebParam(name = "CodMedico") Integer idMedico) {
        return pacienteBO.listarCitas(idEspecialidad, fecha, idMedico);
    }

    @WebMethod(operationName = "listarCitasPorPersona")
    public ArrayList<CitaDTO> listarCitasPorPersona(@WebParam(name = "idPersona") int idPersona) {
        return pacienteBO.listarCitasPorPersona(idPersona);
    }

    @WebMethod(operationName = "reprogramarCita")
    public int reprogramarCita(
        @WebParam(name = "idCitaAnterior") int idCitaAnterior,
        @WebParam(name = "idCitaNueva") int idCitaNueva
    ) {
        return pacienteBO.reprogramarCita(idCitaAnterior, idCitaNueva);
    }

    // Métodos más avanzados (requieren objetos)
    @WebMethod(operationName = "reservarCita")
    public int reservarCita(
        @WebParam(name = "idCita") int idCita,
        @WebParam(name = "idCuenta") int idCuenta
    ) {
        CitaDTO cita = new CitaDTO();
        cita.setIdCita(idCita);
        return pacienteBO.reservarCita(cita, idCuenta);
    }

    @WebMethod(operationName = "cancelarCita")
    public int cancelarCita(@WebParam(name = "idCita") int idCita) {
        CitaDTO cita = new CitaDTO();
        cita.setIdCita(idCita);
        return pacienteBO.cancelarCita(cita);
    }

    @WebMethod(operationName = "pagarCita")
    public int pagarCita(
        @WebParam(name = "idCita") int idCita,
        @WebParam(name = "titular") String titular,
        @WebParam(name = "numTarjeta") String numTarjeta,
        @WebParam(name = "cvv") String cvv,
        @WebParam(name = "metodoPago") String metodoPago,
        @WebParam(name = "fechaExpiracion") LocalDate fechaExpiracion
    ) {
        CitaDTO cita = new CitaDTO();
        cita.setIdCita(idCita);
        return pacienteBO.pagarCita(cita, titular, numTarjeta, cvv, MetodoPago.valueOf(metodoPago.toUpperCase()), fechaExpiracion);
    }
}
