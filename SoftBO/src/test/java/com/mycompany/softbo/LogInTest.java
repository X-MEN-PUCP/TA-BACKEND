/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softbo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        if(cuenta!=null)
            System.out.println("Bienvenido");
    }
    
}
