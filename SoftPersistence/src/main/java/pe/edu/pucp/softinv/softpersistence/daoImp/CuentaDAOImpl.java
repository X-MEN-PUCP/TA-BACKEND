/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.CuentaDTO;
import com.mycompany.softmodel.Rol;
import com.mycompany.softmodel.TipoDocumento;
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
//        this.listaColumnas.add(new Columna("id_cuenta", true, true));
//        this.listaColumnas.add(new Columna("tipo_documento", true, true));
//        this.listaColumnas.add(new Columna("nro_documento", false, false));
//        this.listaColumnas.add(new Columna("contrasena", false, false));
//        this.listaColumnas.add(new Columna("rol", false, false));
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cuenta = new CuentaDTO();
        this.cuenta.setIdCuenta(this.resultSet.getInt("id_cuenta"));
        String tipoDoc = this.resultSet.getString("tipo_documento");
        this.cuenta.setTipoDocumento(TipoDocumento.valueOf(tipoDoc.toUpperCase()));                
        this.cuenta.setNumeroDocumento(this.resultSet.getString("nro_documento"));
        this.cuenta.setContrasenha(this.resultSet.getString("contrasena"));
        String rol= this.resultSet.getString("rol");
        this.cuenta.setRol(Rol.valueOf(rol.toUpperCase())); //por si algun valor en la bd está en minuscula
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.cuenta = null;
    }
    
    @Override
    public CuentaDTO buscarPorNumeroDocumento(String numeroDoc){
        try {
            this.abrirConexion();
            String sql = "SELECT id_cuenta, tipo_documento, nro_documento, "
                    + "contrasena, rol FROM Cuenta WHERE nro_documento = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setString(1, numeroDoc);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar buscarPorNumeroDocumento - " + ex);
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
