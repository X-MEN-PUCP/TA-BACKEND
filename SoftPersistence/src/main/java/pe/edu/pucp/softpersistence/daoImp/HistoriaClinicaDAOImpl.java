/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softpersistence.dao.HistoriaClinicaDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class HistoriaClinicaDAOImpl extends DAOImplBase implements HistoriaClinicaDAO {
    
    HistoriaClinicaDTO historia;
    
    public HistoriaClinicaDAOImpl(){
        super("historia_clinica");
        historia = null;
    }
    

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_historia", true, true));
    }
    
    @Override
    public void instanciarObjetoDelResultSet(){
        historia = new HistoriaClinicaDTO();
        try {
            historia.setIdHistoriaClinica(this.resultSet.getInt("Id_historia"));
        } catch (SQLException ex) {
            Logger.getLogger(HistoriaClinicaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<HistoriaClinicaDTO> listarTodos() {        
        return (ArrayList<HistoriaClinicaDTO>) super.listarTodos();

    }
    
}
