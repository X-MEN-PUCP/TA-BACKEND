/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.modelos.EspecialidadDTO;
import softmodel.util.Genero;
import softmodel.modelos.MedicoDTO;
import softmodel.modelos.PersonaDTO;

import java.sql.SQLException;
import softpersistence.dao.MedicoDAO;
import softdbmanager.DBManager;

/**
 *
 * @author salva
 */
public class MedicoDAOImpl extends DAOImplBase implements MedicoDAO {
    
    
    MedicoDTO medico;
    
    public MedicoDAOImpl() {
        super("Persona");
        this.retornarLlavePrimaria = true;
        this.medico = null;
    }
    

    @Override
    protected void configurarListaDeColumnas() {
        
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        medico = new MedicoDTO();
        PersonaDTO cuentaVar = new PersonaDTO();
        cuentaVar = this.cargarLecturaPersona();
        medico.copiarDesde(cuentaVar);
        EspecialidadDTO especialidad= new EspecialidadDTO();
        especialidad.setIdEspecialidad(this.resultSet.getInt("id_especialidad"));
        medico.setEspecialidad(especialidad);
        medico.setCodMedico(this.resultSet.getString("cod_medico"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.medico = null;
    }
    
    @Override
    public MedicoDTO buscarPorIdCuenta(int idCuenta) {
        MedicoDTO cuentaVar = null;
        try {
            this.abrirConexion();
            String sql = "SELECT id_persona, id_especialidad FROM Persona WHERE id_cuenta = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCuenta);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
            }else{
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId en Medico- " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return cuentaVar;
    }   
    
}
