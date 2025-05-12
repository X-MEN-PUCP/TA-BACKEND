/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.HorarioDTO;
import com.mycompany.softmodel.Turno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softinv.softpersistence.dao.HorarioDAO;
import pe.edu.pucp.softinv.softpersistence.daoImp.Util.Columna;
import softdbmanager.DBManager;

public class HorarioDAOImpl extends DAOImplBase implements HorarioDAO {

    HorarioDTO cuenta;
    private final boolean retornarLlavePrimaria;

    public HorarioDAOImpl() {
        super("Horario");
        this.retornarLlavePrimaria = true;
        this.cuenta = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_horario", true, true));
        this.listaColumnas.add(new Columna("fecha", false, false));
        this.listaColumnas.add(new Columna("hora_inicio", false, false));
        this.listaColumnas.add(new Columna("turno", false, false));
    }

    @Override
    public Integer insertar(HorarioDTO cuenta) {
        this.cuenta = cuenta;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setDate(1, java.sql.Date.valueOf(this.cuenta.getFecha()));
            this.statement.setTime(2, java.sql.Time.valueOf(this.cuenta.getHoraInicio()));
            this.statement.setString(3,this.cuenta.getTurno().toString());
        } catch (SQLException ex) {
            Logger.getLogger(HorarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HorarioDTO obtenerPorId(Integer cuentaID) {
        System.out.println(cuentaID);
        HorarioDTO cuentaVar = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, cuentaID);
            System.out.println(sql);

            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                //System.out.println("Encontró");
                cuentaVar = new HorarioDTO();
                cuentaVar.setIdHorario(this.resultSet.getInt("id_horario"));
                cuentaVar.setFecha(this.resultSet.getDate("fecha").toLocalDate());
                cuentaVar.setHoraInicio(this.resultSet.getTime("hora_inicio").toLocalTime());
                cuentaVar.setTurno(Turno.valueOf(this.resultSet.getString("turno").toUpperCase()));
                return cuentaVar;

            } else {
                //System.out.println("No Encontró nada");
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
        return cuentaVar;
    }

    @Override
    public ArrayList<HorarioDTO> listarTodos() {
        ArrayList<HorarioDTO> lista = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                HorarioDTO cuentaVar = new HorarioDTO();
                cuentaVar.setIdHorario(this.resultSet.getInt("id_horario"));
                cuentaVar.setFecha(this.resultSet.getDate("fecha").toLocalDate());
                cuentaVar.setHoraInicio(this.resultSet.getTime("hora_inicio").toLocalTime());
                cuentaVar.setTurno(Turno.valueOf(this.resultSet.getString("turno").toUpperCase()));
                lista.add(cuentaVar);
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
    public Integer modificar(HorarioDTO cuenta) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement.setDate(1, java.sql.Date.valueOf(this.cuenta.getFecha()));
            this.statement.setTime(2, java.sql.Time.valueOf(this.cuenta.getHoraInicio()));
            this.statement.setString(3,this.cuenta.getTurno().toString());

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
                if (this.conexion != null) {
                    this.conexion.close();
                }
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
