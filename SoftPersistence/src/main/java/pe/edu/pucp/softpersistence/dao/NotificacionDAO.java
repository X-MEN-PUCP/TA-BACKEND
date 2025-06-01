/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import java.util.ArrayList;
import pe.edu.pucp.softmodel.modelos.NotificacionDTO;

/**
 *
 * @author Cesar
 */
public interface NotificacionDAO {
    public NotificacionDTO buscarPorIdNotificacion(int idNotificacion);
    public ArrayList<NotificacionDTO> listarPorCuenta(int idCuenta);
    public int EliminarAnterioresAFecha(int dia, int mes, int año);
}

