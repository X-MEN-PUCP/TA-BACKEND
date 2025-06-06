/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.util.Turno;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softpersistence.dao.HorarioDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;
import pe.edu.pucp.softdbmanager.db.DBManager;

public class HorarioDAOImpl extends DAOImplBase implements HorarioDAO {

    private HorarioDTO horario;
    private final boolean retornarLlavePrimaria;

    public HorarioDAOImpl() {
        super("horario");
        this.retornarLlavePrimaria = true;
        this.horario = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_horario", true, true));
        this.listaColumnas.add(new Columna("fecha", false, false));
        this.listaColumnas.add(new Columna("hora_inicio", false, false));
        this.listaColumnas.add(new Columna("turno", false, false));
    }

    @Override
    public Integer insertar(HorarioDTO horario) {
        this.horario = horario;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
            this.statement.setDate(1, java.sql.Date.valueOf(this.horario.getFecha()));
            this.statement.setTime(2, java.sql.Time.valueOf(this.horario.getHoraInicio()));
            this.statement.setString(3, this.horario.getTurno().toString());
        } catch (SQLException ex) {
            Logger.getLogger(HorarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void incluirValorDeParametrosParaModificacion() {
        try {
            this.statement.setDate(1, java.sql.Date.valueOf(this.horario.getFecha()));
            this.statement.setTime(2, java.sql.Time.valueOf(this.horario.getHoraInicio()));
            this.statement.setString(3, this.horario.getTurno().toString());
            this.statement.setInt(4, this.horario.getIdHorario());
        } catch (SQLException ex) {
            Logger.getLogger(HorarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.horario.getIdHorario());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.horario.getIdHorario());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.horario = new HorarioDTO();
        this.horario.setIdHorario(this.resultSet.getInt("id_horario"));
        this.horario.setFecha(this.resultSet.getDate("fecha").toLocalDate());
        this.horario.setHoraInicio(this.resultSet.getTime("hora_inicio").toLocalTime());
        
        String turno = this.resultSet.getString("turno");
        this.horario.setTurno(Turno.valueOf(turno.toUpperCase()));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.horario = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.horario);
    }

    @Override
    public HorarioDTO obtenerPorId(Integer horarioID) {
        this.horario = new HorarioDTO();
        this.horario.setIdHorario(horarioID);
        super.obtenerPorId();
        return this.horario;
    }

    @Override
    public ArrayList<HorarioDTO> listarTodos() {
        return (ArrayList<HorarioDTO>) super.listarTodos();

    }

    @Override
    public Integer modificar(HorarioDTO horario) {
        this.horario = horario;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Integer id) {
        this.horario = new HorarioDTO();
        this.horario.setIdHorario(id);
        return super.eliminar();
    }

    private String generarSQLHoarario() {
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
    public HorarioDTO buscarHorario(HorarioDTO horario) {
        String sql = this.generarSQLHoarario();
        ArrayList<HorarioDTO> horar = (ArrayList<HorarioDTO>) super.listarTodos(sql, this::incluirValorDeParametros, horario);
        if (horar.size() > 0) {
            return horar.get(0);
        }
        return horario;
    }

    private void incluirValorDeParametros(Object objetoParametros) {
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
