/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softbo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public LogInTest(){
        
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
        CuentaBO cuenta=null;
        LogIn instance = new LogIn();
        cuenta = instance.iniciarSesion(dni, contrasenha);
        if(cuenta!=null){
            System.out.println("Bienvenido");
            cuenta.QuienSoy();
            ArrayList<CitaDTO> citas;
            ArrayList<EspecialidadDTO> especialidades;
            ArrayList<MedicoDTO> medicos;
            if(cuenta instanceof CuentaPaciente){
                CuentaPaciente cuentaPaciente = (CuentaPaciente) cuenta;
                especialidades = cuentaPaciente.listaDeEspecialidades();
                Random rand = new Random();
                int indice = rand.nextInt(especialidades.size());
                EspecialidadDTO especialidad = especialidades.get(indice);
                Integer idEspecialidad = especialidad.getIdEspecialidad();
                System.out.println(idEspecialidad);
                medicos=cuentaPaciente.listaDeMedicoPorEspecialidad(idEspecialidad);
                indice = rand.nextInt(medicos.size());
                MedicoDTO medico = medicos.get(indice);
                Integer idMedico = medico.getIdPersona();
                System.out.println(idMedico);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date fecha = formato.parse("2025-06-13 00:00:00");
                citas = cuentaPaciente.listarCitas(idEspecialidad, fecha, idMedico);
                System.out.println(citas.get(0));
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
