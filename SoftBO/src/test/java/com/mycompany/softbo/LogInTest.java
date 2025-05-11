/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softbo;

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
    @org.junit.jupiter.api.Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String dni = "75843948";
        String contrasenha = "xd";
        CuentaBO cuenta=null;
        LogIn instance = new LogIn();
        cuenta = instance.iniciarSesion(dni, contrasenha);
        if(cuenta!=null){
            System.out.println("Bienvenido");
            cuenta.QuienSoy();
        }
    }
}
