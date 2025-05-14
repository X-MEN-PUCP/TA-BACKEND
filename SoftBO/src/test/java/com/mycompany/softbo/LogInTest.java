/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import softbusiness.business.CuentaBO;
import softbusiness.business.LogIn;

import softmodel.modelos.*;
import java.util.Random;
import softbusiness.business.CuentaAdmin;
import softbusiness.business.CuentaMedico;
import softbusiness.business.CuentaPaciente;
import softbusiness.util.NuevoUsuarioMedicoBuilder;
import softbusiness.util.NuevoUsuarioPacienteBuilder;
import softmodel.util.*;
/**
 *
 * @author Mcerv
 */
public class LogInTest {
    public LogInTest(){
        
    }
    /**
     * Test of iniciarSesion method, of class LogIn.
     */
    
    
    /*==============================================================*/
    //TEST INICIAR SESSIÓN
//    @org.junit.jupiter.api.Test
//    public void testIniciarSesion() {
//        System.out.println("iniciarSesion");
//        String dni = "74073698";
//        String contrasenha = "pass1";
//        CuentaBO cuenta=null;
//        LogIn instance = new LogIn();
//        cuenta = instance.iniciarSesion(dni, contrasenha);
//        if(cuenta!=null){
//            System.out.println("Bienvenido");
//            cuenta.QuienSoy();
//        }
//    }
//    
    
    /*==============================================================*/
    //TEST PACIENTE
    
//    @org.junit.jupiter.api.Test
//     public void testIniciarSesionVistaPaciente() throws ParseException {
//        System.out.println("iniciarSesion");
//        String dni = "74073698";
//        String contrasenha = "pass1";
//        CuentaBO cuenta = null;
//        LogIn instance = new LogIn();
//        cuenta = instance.iniciarSesion(dni, contrasenha);
//        if (cuenta != null) {
//            System.out.println("Bienvenido");
//            cuenta.QuienSoy();
//            ArrayList<CitaDTO> citas;
//            ArrayList<EspecialidadDTO> especialidades;
//            ArrayList<MedicoDTO> medicos;
//            if (cuenta instanceof CuentaPaciente) {
//                CuentaPaciente cuentaPaciente = (CuentaPaciente) cuenta;
//                //listar cita
//                especialidades = cuentaPaciente.listaDeEspecialidades();
//                Random rand = new Random();
//                int indice = rand.nextInt(especialidades.size());
//                EspecialidadDTO especialidad = especialidades.get(indice);
//                Integer idEspecialidad = especialidad.getIdEspecialidad();
//                System.out.println(idEspecialidad + "Especialidad");
//                medicos = cuentaPaciente.listaDeMedicoPorEspecialidad(idEspecialidad);
//                indice = rand.nextInt(medicos.size());
//                MedicoDTO medico = medicos.get(indice);
//                Integer idMedico = medico.getIdPersona();
//                System.out.println(idMedico + "Medico");
//                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date fecha = formato.parse("2025-06-13 00:00:00");
//                citas = cuentaPaciente.listarCitas(idEspecialidad, fecha, idMedico);
//                CitaDTO citavieja;
//                if (citas.size() != 0) {
//                    System.out.println(citas.get(0).getIdCita() + "Cita");
//                    CitaDTO citaElegida = citas.get(0);
//                    citavieja = citaElegida;
//                    System.out.println("Reservando");
//                    Integer reserva = cuentaPaciente.reservarCita(citaElegida);
//                    System.out.println("Reserva: " + reserva);
//                    LocalDate hoy = LocalDate.now();
//                    cuentaPaciente.pagarCita(citaElegida, "Yo", "145456", "454", MetodoPago.VISA, hoy);
//                    //Reprogramar y cancelar
//                    citas = cuentaPaciente.listarCitas(idEspecialidad, fecha, idMedico);
//
//                    if (citas.size() != 0) {
//                        System.out.println(citas.get(0).getIdCita() + "Cita");
//                        int historiaVieja = citavieja.getHistoriaClinicaPaciente().getIdHistoriaClinica();
//                        citaElegida = citas.get(0);
//                        System.out.println("Reprogramando...");
//                        Integer reprogramacion = cuentaPaciente.reprogramarCita(citavieja.getIdCita(), citaElegida.getIdCita());
//                        System.out.println("Reprogramado: " + reserva);
//                        System.out.println("Cencelando...");
//                        System.out.println("Cancelando...");
//                        citaElegida.getHistoriaClinicaPaciente().setIdHistoriaClinica(historiaVieja);
//                        System.err.println("Historia cita elegida" + citaElegida.getHistoriaClinicaPaciente().getIdHistoriaClinica());
//                        Integer cancelar = cuentaPaciente.cancelarCita(citaElegida);
//                        System.out.println("Cancelado: " + cancelar);
//                    } else {
//                        System.out.println("No hay citas disponibles");
//                    }
//
//                } else {
//                    System.out.println("No hay citas disponibles");
//                }
//
//                System.out.println("Fin de los test");
//
//            }
//        }
//    }
    
     
     
     /*==============================================================*/
    //TEST MEDICO
     
//    @org.junit.jupiter.api.Test
//    public void testIniciarSesionVistaMedico() throws ParseException {
//        
//        System.out.println("iniciarSesion");
//        String dni = "77751498";
//        String contrasenha = "pass2";
//        CuentaBO cuenta=null;
//        LogIn instance = new LogIn();
//        cuenta = instance.iniciarSesion(dni, contrasenha);
//        if(cuenta!=null){
//            System.out.println("Bienvenido");
//            cuenta.QuienSoy();
//            ArrayList<CitaDTO> citas;
//            ArrayList<CitaDTO> citasProgramadas;
//            ArrayList<CitaDTO> citasDisponibles;
//            if(cuenta instanceof CuentaMedico){
//                CuentaMedico cuentaMedico = (CuentaMedico) cuenta;
//                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date fecha = formato.parse("2025-06-13 00:00:00");
//                citas = cuentaMedico.listarCitas(fecha);
//                System.out.println("Lista citas: "+citas.size());
//                citasProgramadas = cuentaMedico.listarCitasProgramadas(fecha);
//                System.out.println("Lista citas programadas: "+citasProgramadas.size());
//                citasDisponibles = cuentaMedico.listarCitasDisponibles(fecha);
//                System.out.println("Lista citas disponibles : "+citasDisponibles.size());
//            }
//        }
//    }
     
     
     
     
     /*==============================================================*/
    //TEST ADMINISTRADOR
    
    @org.junit.jupiter.api.Test
    public void testIniciarSesionVistaAdministrador() throws ParseException {
        System.out.println("iniciarSesion");
        String dni = "72945356";
        String contrasenha = "pass3";
        CuentaBO cuenta=null;
        LogIn instance = new LogIn();
        cuenta = instance.iniciarSesion(dni, contrasenha);
        if(cuenta!=null){
            System.out.println("Bienvenido");
            cuenta.QuienSoy();
            
            ArrayList<CitaDTO> ReporteResumenGeneral;
            ArrayList<CitaDTO> ReporteMedico;
            ArrayList<CitaDTO> reportePaciente;
            
            
            if(cuenta instanceof CuentaAdmin){
                CuentaAdmin cuentaAdmin = (CuentaAdmin) cuenta;
                
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date fechaIniDate = formato.parse("2025-06-10 00:00:00");
                Date fechaFinDate = formato.parse("2025-06-20 00:00:00");
                PacienteDTO paciente = new PacienteDTO();
                HistoriaClinicaDTO historia= new HistoriaClinicaDTO();
                historia.setIdHistoriaClinica(1);
                paciente.setHistoriaClinica(historia);
                System.out.println("Generando reporte Resumen...");
                ReporteResumenGeneral = cuentaAdmin.GenerarReporteResumenGeneral(null, null, fechaIniDate, fechaFinDate);
                System.out.println("Lista citas: "+ReporteResumenGeneral.size());
                System.out.println("Generando reporte del medico...");
                ReporteMedico = cuentaAdmin.ReporteCitasDelMedico(2);
                System.out.println("Lista citas del medico: "+ReporteMedico.size());
                System.out.println("Generando reporte del paciente...");
                reportePaciente = cuentaAdmin.ReporteCitasPaciente(paciente);
                System.out.println("Lista citas del paciente : "+reportePaciente.size());
                
                //También se podría listar las especialidades y elegir una
                
                //Nueva especialidad
                EspecialidadDTO especialidad = new EspecialidadDTO();
                especialidad.setNombreEspecialidad("Reumatología");
                especialidad.setPrecioConsulta(75);
                
                NuevoUsuarioMedicoBuilder usuario = new NuevoUsuarioMedicoBuilder(TipoDocumento.DNI,"75843948", "pass7");
                MedicoDTO medico = usuario.conGenero(Genero.MASCULINO).builNuevoUsuario();
                medico.setEspecialidad(especialidad);
                medico.setCodMedico(34589);
                Date fechaNacimiento = formato.parse("1985-06-13 00:00:00");
                medico.setFechaNaciemiento(fechaNacimiento);
                Integer insertado = cuentaAdmin.insertarNuevoMedico(medico);
                System.out.println("Insertado médico "+insertado);
                CitaDTO cita = new CitaDTO();
                HorarioDTO horario = new HorarioDTO();
                LocalDate fechaHorario = LocalDate.now();
                horario.setFecha(fechaHorario);
                horario.setTurno(Turno.MANHANA);
                LocalTime horaHorario = LocalTime.now();
                horario.setHoraInicio(horaHorario);
                cita.setMedico(medico);
                cita.setEstado(Estado.DISPONIBLE);
                cita.setHorario(horario);
//                insertado=cuentaAdmin.insertarNuevaCita(cita);
//                System.out.println("Insertado cita "+insertado);
            }
        }
    }
    
    /*======================================*/
    //FIN TEST
    /*======================================*/
    
    
}
