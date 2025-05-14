/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.modelos.HorarioDTO;
import softmodel.util.Turno;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import softpersistence.dao.HorarioDAO;
import softpersistence.daoImp.Util.Columna;
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
        this.cuenta = cuenta;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setDate(1, java.sql.Date.valueOf(this.cuenta.getFecha()));
            this.statement.setTime(2, java.sql.Time.valueOf(this.cuenta.getHoraInicio()));
            this.statement.setString(3,this.cuenta.getTurno().toString());
            this.statement.setInt(5,this.cuenta.getIdHorario());

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
    
    private String generarSQLHoarario(){
        //SELECT * FROM tu_tabla WHERE fecha = ? AND turno = ? AND horaInicio = ?;
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = " fecha = ? and turno = ? and hora_inicio=?";
        for (Columna columna : this.listaColumnas) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }
    
    @Override
    public HorarioDTO buscarHorario(HorarioDTO horario){
        String sql = this.generarSQLHoarario();
        ArrayList<HorarioDTO> horar= (ArrayList<HorarioDTO>) super.listarTodos(sql, this::incluirValorDeParametros, horario);
        if(horar.size()>0)
            return horar.get(0);
        return horario;
    }
    
    private void incluirValorDeParametros(Object objetoParametros){
        HorarioDTO parametros = (HorarioDTO) objetoParametros;
        try {
            LocalDate fecha = parametros.getFecha();
            this.statement.setDate(1, java.sql.Date.valueOf(fecha));
            LocalTime horaInicio = parametros.getHoraInicio();
            this.statement.setTime(2, java.sql.Time.valueOf(horaInicio));
            this.statement.setString(3, parametros.getTurno().toString());
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
