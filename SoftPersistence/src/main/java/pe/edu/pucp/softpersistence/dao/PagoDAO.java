/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;


import java.util.ArrayList;
import pe.edu.pucp.softmodel.modelos.PagoDTO;

/**
 *
 * @author salva
 */
public interface PagoDAO {
    

    
    public Integer insertar(PagoDTO pago);
    public Integer modificar(PagoDTO pago);
    public PagoDTO buscarPorId(int idPago);
    public PagoDTO buscarPorIdCita(int idCita);
    public ArrayList<PagoDTO> listarTodos();
    public Integer eliminar(int idPago);

    
}
