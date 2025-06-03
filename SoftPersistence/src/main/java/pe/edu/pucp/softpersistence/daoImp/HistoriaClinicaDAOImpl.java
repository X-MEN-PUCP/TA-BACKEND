/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
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
        this.listaColumnas.add(new Columna("id_persona", false, false));
    }
    
    @Override
    public void instanciarObjetoDelResultSet() throws SQLException{
        this.historia = new HistoriaClinicaDTO();
        this.historia.setIdHistoriaClinica(this.resultSet.getInt("id_historia"));
        PacienteDTO paciente = new PacienteDTO();
        paciente.setIdPersona(this.resultSet.getInt("id_persona"));
        this.historia.setPaciente(paciente);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.historia = null;
    }

    @Override
    public ArrayList<HistoriaClinicaDTO> listarTodos() {        
        return (ArrayList<HistoriaClinicaDTO>) super.listarTodos();
    }
    
    @Override
    public HistoriaClinicaDTO obtenerPorIdPaciente(Integer idPaciente){
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_persona");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        ArrayList<HistoriaClinicaDTO> historiasClinicas = (ArrayList<HistoriaClinicaDTO>) super.listarTodos(sql, idPaciente, incluirValorDeParametros, parametros);
        if(!historiasClinicas.isEmpty())
            this.historia = historiasClinicas.get(0);
        return this.historia; 
    }
}
