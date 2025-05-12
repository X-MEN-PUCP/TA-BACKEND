/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.util.Genero;
import softmodel.modelos.HistoriaClinicaDTO;
import softmodel.modelos.PacienteDTO;
import softmodel.modelos.PersonaDTO;
import java.sql.SQLException;
import softpersistence.dao.PacienteDAO;

/**
 *
 * @author Mcerv
 */
public class PacienteDAOImpl extends DAOImplBase implements PacienteDAO{
    private PacienteDTO paciente;
    
    public PacienteDAOImpl(){
        super("Persona");
    }
    
    @Override
    protected void configurarListaDeColumnas(){
        
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException{
        PersonaDTO cuentaVar = new PersonaDTO();
        cuentaVar = this.cargarLecturaPersona();
        paciente = new PacienteDTO();
        paciente.copiarDesde(cuentaVar);
        HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
        historia.setIdHistoriaClinica(this.resultSet.getInt("id_historia"));
        paciente.setHistoriaClinica(historia);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.paciente = null;
    }
    
    @Override
    public PacienteDTO buscarPorIdCuenta(int idCuenta){
        try {
            System.out.println("Buscando paciente...");
            this.abrirConexion();
            //String sql = "SELECT id_persona, id_historia FROM Persona WHERE id_cuenta = ?";
            String sql = "SELECT * FROM Persona WHERE id_cuenta = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCuenta);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar buscarPorIdCuenta en Paciente- " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        System.out.println("Paciente encontrada");
        return this.paciente;
    }
}
