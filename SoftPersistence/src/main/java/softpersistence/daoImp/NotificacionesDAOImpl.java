/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;
import softdbmanager.DBManager;
import softpersistence.dao.NotificacionDAO;
import softmodel.modelos.NotificacionesDTO;
import softpersistence.daoImp.CuentaDAOImpl;
import softpersistence.daoImp.Util.Columna;
import softpersistence.daoImp.CitaDAOImpl;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.Month;

/**
 *
 * @author Cesar
 */
public class NotificacionesDAOImpl extends DAOImplBase implements NotificacionDAO {
    private NotificacionesDTO noti;
    
    public NotificacionesDAOImpl(){
        super("Notificaciones");
        noti=null;
    }
    
    @Override
    public void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_notificacion", true, true));
        this.listaColumnas.add(new Columna("id_cuenta", false, false));
        this.listaColumnas.add(new Columna("id_cita", false, false));
        this.listaColumnas.add(new Columna("tipo", false, false));
        this.listaColumnas.add(new Columna("mensaje", false, false));
        this.listaColumnas.add(new Columna("canal", false, false));
        this.listaColumnas.add(new Columna("fecha_envio", false, false));
        this.listaColumnas.add(new Columna("usuario_creacion", false, false));
        this.listaColumnas.add(new Columna("fecha_creacion", false, false));
        this.listaColumnas.add(new Columna("usuario_modificacion", false, false));
        this.listaColumnas.add(new Columna("fecha_modificacion", false, false));
    }
    
    @Override
    public void instanciarObjetoDelResultSet() throws SQLException {
        this.noti.setIdNotificacion(this.resultSet.getInt("id_notificacion"));
        this.noti.setCuenta(new CuentaDAOImpl().obtenerPorID(this.resultSet.getInt("id_cuenta")));
        this.noti.setCita(new CitaDAOImpl().obtenerPorId(this.resultSet.getInt("id_cita")));
        this.noti.setTipo(this.resultSet.getString("tipo"));
        this.noti.setMensaje(this.resultSet.getString("mensaje"));
        this.noti.setCanal(this.resultSet.getString("canal"));
        this.noti.setFechaEnvio(this.resultSet.getDate("fecha_envio"));
    }
    
    @Override
    public void limpiarObjetoDelResultSet() {
        this.noti=null;
    }
    
    @Override
    public void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.noti.getIdNotificacion());
    }
    
    @Override
    public NotificacionesDTO buscarPorIdNotificacion(int idNotificacion) {
        this.noti=new NotificacionesDTO();
        this.noti.setIdNotificacion(idNotificacion);
        super.obtenerPorId();
        return this.noti;
    }
    
    @Override
    public ArrayList<NotificacionesDTO> listarPorCuenta(int idCuenta){
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_cuenta");
        Consumer incluirValoresDeParametros = null;
        Object parametros = null;
        return (ArrayList<NotificacionesDTO>) super.listarTodos(sql,idCuenta,incluirValoresDeParametros,parametros);
    }
    
    @Override
    public int EliminarAnterioresAFecha(int dia, int mes, int año){
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = "DELETE FROM Notificaciones WHERE fecha_envio < ? AND id_notificacion = ?";; //poner sql correcto
            LocalDate fechaLocal = LocalDate.of(año, mes, dia);
            Timestamp fecha = Timestamp.valueOf(fechaLocal.atStartOfDay());
            this.statement.setTimestamp(1, fecha); // Parámetro de fecha
            this.statement.setInt(2, this.noti.getIdNotificacion()); // Parámetro de ID
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
