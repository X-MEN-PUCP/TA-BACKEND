/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.CuentaDTO;
import com.mycompany.softmodel.Rol;
import java.sql.SQLException;
import pe.edu.pucp.softinv.softpersistence.daoImp.Util.Columna;
import pe.edu.pucp.softinv.softpersistence.dao.CuentaDAO;

/**
 *
 * @author Mcerv
 */
public class CuentaDAOImpl extends DAOImplBase implements CuentaDAO{
    private CuentaDTO cuenta;
    
    public CuentaDAOImpl() {
        super("Cuenta");
        this.retornarLlavePrimaria = true;
        this.cuenta = null;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_CUENTA", true, true));
        this.listaColumnas.add(new Columna("DNI", false, false));
        this.listaColumnas.add(new Columna("CONTRASENHA", false, false));
        this.listaColumnas.add(new Columna("ROL", false, false));
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cuenta = new CuentaDTO();
        this.cuenta.setIdCuenta(this.resultSet.getInt("ID_CUENTA"));
        this.cuenta.setDni(this.resultSet.getInt("DNI"));
        this.cuenta.setContrasenha(this.resultSet.getString("CONTRASENHA"));
        String rol= this.resultSet.getString("ROL");
        this.cuenta.setRol(Rol.valueOf(rol.toUpperCase())); //por si algun valor en la bd está en minuscula
    }
    
    @Override
    public CuentaDTO buscarPorDNI(Integer dni){
        try {
            this.abrirConexion();
            String sql = "SELECT ID_CUENTA, DNI, CONTRASENHA, ROL FROM Cuenta WHERE DNI = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, this.cuenta.getDni());
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return this.cuenta;
    }
}
