/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import softdbmanager.DBManager;
import softmodel.modelos.HistoriaClinicaDTO;
import softpersistence.dao.HistoriaClinicaDAO;
import softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class HistoriaClinicaDAOImpl extends DAOImplBase implements HistoriaClinicaDAO {
    
    HistoriaClinicaDTO historia;
    
    public HistoriaClinicaDAOImpl(){
        super("HistoriaClinica");
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
        ArrayList<HistoriaClinicaDTO> lista = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
                lista.add(historia);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }
    
}
