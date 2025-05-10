/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.PersonaDTO;
import pe.edu.pucp.softinv.softpersistence.dao.PersonaDAO;

/**
 *
 * @author salva
 */
public class PersonaDAOImpl extends DAOImplBase implements PersonaDAO {
    
    PersonaDTO persona;
    
    public PersonaDAOImpl() {
        super("Persona");
        this.retornarLlavePrimaria = true;
        this.persona = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
