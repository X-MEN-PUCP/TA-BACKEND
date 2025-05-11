/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.EspecialidadDTO;
import com.mycompany.softmodel.Genero;
import com.mycompany.softmodel.MedicoDTO;
import java.sql.SQLException;
import pe.edu.pucp.softinv.softpersistence.dao.MedicoDAO;
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
        medico.setIdPersona(this.resultSet.getInt("id_persona"));
//                cuentaVar.setNombres(this.resultSet.getString("NOMBRES"));
//                cuentaVar.setApellidos(this.resultSet.getString("APELLIDOS"));
//                cuentaVar.setFechaNaciemiento(this.resultSet.getDate("FECHA_NACIMIENTO"));
//                cuentaVar.setNumCelular(this.resultSet.getString("NUM_CELUALAR"));
//                cuentaVar.setGenero(Genero.valueOf(this.resultSet.getString("GENERO").toUpperCase()));
        //id especialidad es null
        //cod medico es null
        //id historia es null
        //cuentaVar.setGenero.valueOf(this.resultSet.getString("GENERO").toUpperCase()));
        EspecialidadDTO especialidad= new EspecialidadDTO();
        especialidad.setIdEspecialidad(this.resultSet.getInt("id_especialidad"));
        medico.setEspecialidad(especialidad);
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
