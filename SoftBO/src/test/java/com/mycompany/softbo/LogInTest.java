/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import softbusiness.business.CuentaBO;
import softbusiness.business.LogIn;

import softmodel.modelos.*;
import java.util.Random;
import softbusiness.business.CuentaPaciente;
import softmodel.util.*;

/**
 *
 * @author Mcerv
 */
public class LogInTest {

    public LogInTest() {

    }

    /**
     * Test of iniciarSesion method, of class LogIn.
     */
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

    @org.junit.jupiter.api.Test
    public void testIniciarSesionVistaPaciente() throws ParseException {
        System.out.println("iniciarSesion");
        String dni = "74073698";
        String contrasenha = "pass1";
        CuentaBO cuenta = null;
        LogIn instance = new LogIn();
        cuenta = instance.iniciarSesion(dni, contrasenha);
        if (cuenta != null) {
            System.out.println("Bienvenido");
            cuenta.QuienSoy();
            ArrayList<CitaDTO> citas;
            ArrayList<EspecialidadDTO> especialidades;
            ArrayList<MedicoDTO> medicos;
            if (cuenta instanceof CuentaPaciente) {
                CuentaPaciente cuentaPaciente = (CuentaPaciente) cuenta;
                //listar cita
                especialidades = cuentaPaciente.listaDeEspecialidades();
                Random rand = new Random();
                int indice = rand.nextInt(especialidades.size());
                EspecialidadDTO especialidad = especialidades.get(indice);
                Integer idEspecialidad = especialidad.getIdEspecialidad();
                System.out.println(idEspecialidad + "Especialidad");
                medicos = cuentaPaciente.listaDeMedicoPorEspecialidad(idEspecialidad);
                indice = rand.nextInt(medicos.size());
                MedicoDTO medico = medicos.get(indice);
                Integer idMedico = medico.getIdPersona();
                System.out.println(idMedico + "Medico");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date fecha = formato.parse("2025-06-13 00:00:00");
                citas = cuentaPaciente.listarCitas(idEspecialidad, fecha, idMedico);
                CitaDTO citavieja;
                if (citas.size() != 0) {
                    System.out.println(citas.get(0).getIdCita() + "Cita");
                    CitaDTO citaElegida = citas.get(0);
                    citavieja = citaElegida;
                    System.out.println("Reservando");
                    Integer reserva = cuentaPaciente.reservarCita(citaElegida);
                    System.out.println("Reserva: " + reserva);
                    LocalDate hoy = LocalDate.now();
                    cuentaPaciente.pagarCita(citaElegida, "Yo", "145456", "454", MetodoPago.VISA, hoy);
                    //Reprogramar y cancelar
                    citas = cuentaPaciente.listarCitas(idEspecialidad, fecha, idMedico);

                    if (citas.size() != 0) {
                        System.out.println(citas.get(0).getIdCita() + "Cita");
                        int historiaVieja = citavieja.getHistoriaClinicaPaciente().getIdHistoriaClinica();
                        citaElegida = citas.get(0);
                        System.out.println("Reprogramando...");
                        Integer reprogramacion = cuentaPaciente.reprogramarCita(citavieja.getIdCita(), citaElegida.getIdCita());
                        System.out.println("Reprogramado: " + reserva);
                        System.out.println("Cancelando...");
                        citaElegida.getHistoriaClinicaPaciente().setIdHistoriaClinica(historiaVieja);
                        System.err.println("Historia cita elegida" + citaElegida.getHistoriaClinicaPaciente().getIdHistoriaClinica());
                        Integer cancelar = cuentaPaciente.cancelarCita(citaElegida);
                        System.out.println("Cancelado: " + cancelar);
                        

                    } else {
                        System.out.println("No hay citas disponibles");
                    }

                } else {
                    System.out.println("No hay citas disponibles");
                }
                
                System.out.println("Fin de los test");

            }
        }
    }

//    @org.junit.jupiter.api.Test
//    public void testIniciarSesionVistaMedico() {
//        System.out.println("iniciarSesion");
//        String dni = "75843948";
//        String contrasenha = "xd";
//        CuentaBO cuenta=null;
//        LogIn instance = new LogIn();
//        cuenta = instance.iniciarSesion(dni, contrasenha);
//        if(cuenta!=null){
//            System.out.println("Bienvenido");
//            cuenta.QuienSoy();
//        }
//    }
//    @org.junit.jupiter.api.Test
//    public void testIniciarSesionVistaAdministrador() {
//        System.out.println("iniciarSesion");
//        String dni = "75843948";
//        String contrasenha = "xd";
//        CuentaBO cuenta=null;
//        LogIn instance = new LogIn();
//        cuenta = instance.iniciarSesion(dni, contrasenha);
//        if(cuenta!=null){
//            System.out.println("Bienvenido");
//            cuenta.QuienSoy();
//        }
//    }
}
