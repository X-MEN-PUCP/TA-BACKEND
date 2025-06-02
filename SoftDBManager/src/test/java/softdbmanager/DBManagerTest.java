/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package softdbmanager;

import pe.edu.pucp.softdbmanager.db.DBManager;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author salva
 */
public class DBManagerTest {
    
    public DBManagerTest() {
    }

    
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");                
        DBManager dBManager = DBManager.getInstance();
        assertNotNull(dBManager);
    }

    @org.junit.jupiter.api.Test
    public void testGetConnection() {
        System.out.println("getConnection");  
        System.out.println("Consiguiendo Instancia");
        DBManager dBManager = DBManager.getInstance();
        System.out.println("Instancia conseguida");
        System.out.println("Consiguiendo conexion");
        Connection conexion = dBManager.getConnection();
        
        assertNotNull(conexion);
    }
    
}
