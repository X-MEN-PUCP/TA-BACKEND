/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.util.Rol;
import pe.edu.pucp.softmodel.util.TipoDocumento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;
import pe.edu.pucp.softpersistence.dao.CuentaDAO;

/**
 *
 * @author Mcerv
 */
public class CuentaDAOImpl extends DAOImplBase implements CuentaDAO{
    private CuentaDTO cuenta;
    
    public CuentaDAOImpl() {
        super("cuenta");
        this.retornarLlavePrimaria = true;
        this.cuenta = null;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_cuenta", true, true));
        this.listaColumnas.add(new Columna("tipo_documento", false, false));
        this.listaColumnas.add(new Columna("nro_documento", false, false));
        this.listaColumnas.add(new Columna("contrasena", false, false));
        this.listaColumnas.add(new Columna("rol", false, false));
    }
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, cuenta.getTipoDocumento().toString());
        this.statement.setString(2,cuenta.getNumeroDocumento());
        this.statement.setString(3,cuenta.getContrasenha());
        this.statement.setString(4,cuenta.getRol().toString());
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
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cuenta.getIdCuenta());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cuenta.getIdCuenta());
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
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.cuenta);
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
        this.cuenta = new CuentaDTO();
        this.cuenta.setIdCuenta(idCuenta);                
        super.obtenerPorId();
        return this.cuenta;
        
        
//        System.out.println(idCuenta);
//        try {
//            this.conexion = DBManager.getInstance().getConnection();
//            String sql = this.generarSQLParaObtenerPorId();
//            this.statement = this.conexion.prepareCall(sql);
//            this.statement.setInt(1, idCuenta);
//            System.out.println(sql);
//
//            this.resultSet = this.statement.executeQuery();
//            if (this.resultSet.next()) {
//                System.out.println("Encontró");
//                this.instanciarObjetoDelResultSet();
//                return cuenta;
//
//            } else {
//                System.out.println("No Encontró nada");
//                return null;
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error al intentar obtenerPorId - " + ex);
//        } finally {
//            try {
//                if (this.conexion != null) {
//                    this.conexion.close();
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error al cerrar la conexión - " + ex);
//            }
//        }
//        return cuenta;
        
    }
    
    
    @Override
    public ArrayList<CuentaDTO> listarTodos() {
        
        return (ArrayList<CuentaDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CuentaDTO cuenta) {
        this.cuenta = cuenta;              
        return super.modificar();
    }

    @Override
    public Integer eliminar(Integer id) {
        this.cuenta = new CuentaDTO();
        this.cuenta.setIdCuenta(id);                
        return super.eliminar();
    }
    
    
    @Override
    public Integer insertar(CuentaDTO cuenta) {
        this.cuenta = cuenta;
        return super.insertar();
    }
}
