/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.modelos.CuentaDTO;
import softmodel.util.Rol;
import softmodel.util.TipoDocumento;
import java.sql.SQLException;
import java.util.ArrayList;
import softdbmanager.DBManager;
import softmodel.modelos.CitaDTO;
import softmodel.modelos.MedicoDTO;
import softpersistence.daoImp.Util.Columna;
import softpersistence.dao.CuentaDAO;

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
        this.listaColumnas.add(new Columna("id_cuenta", true, true));
        this.listaColumnas.add(new Columna("tipo_documento", true, true));
        this.listaColumnas.add(new Columna("nro_documento", false, false));
        this.listaColumnas.add(new Columna("contrasena", false, false));
        this.listaColumnas.add(new Columna("rol", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        
        this.statement.setString(1, cuenta.getTipoDocumento().toString());
        this.statement.setString(2,cuenta.getNumeroDocumento());
        this.statement.setString(3,cuenta.getContrasenha());
        this.statement.setString(4,cuenta.getRol().toString());
        this.statement.setInt(5, cuenta.getIdCuenta());
        
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
            System.out.println("Buscando cuenta...");
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
        System.out.println("Cuenta encontrada");
        return this.cuenta;
    }
    
    
    public CuentaDTO obtenerPorID(int idCuenta){
        
        System.out.println(idCuenta);
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, idCuenta);
            System.out.println(sql);

            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return cuenta;

            } else {
                System.out.println("No Encontró nada");
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return cuenta;
        
    }
    
    
    @Override
    public ArrayList<CuentaDTO> listarTodos() {
        ArrayList<CuentaDTO> lista = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
                lista.add(cuenta);
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

    @Override
    public Integer modificar(CuentaDTO cuenta) {
        this.cuenta = cuenta;
        int resultado = 0;
        try {
            this.abrirConexion();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.incluirValorDeParametrosParaModificacion();
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar modificar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    @Override
    public Integer eliminar(Integer id) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar eliminar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }
    
}
