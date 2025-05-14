/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.dao;

import java.util.ArrayList;
import softmodel.modelos.NotificacionesDTO;

/**
 *
 * @author Cesar
 */
public interface NotificacionDAO {
    public NotificacionesDTO buscarPorIdNotificacion(int idNotificacion);
    public ArrayList<NotificacionesDTO> listarPorCuenta(int idCuenta);
    public int EliminarAnterioresAFecha(int dia, int mes, int año);
}

