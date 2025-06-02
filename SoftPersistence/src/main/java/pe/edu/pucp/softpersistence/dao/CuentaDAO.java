/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.dao;

import pe.edu.pucp.softmodel.modelos.CuentaDTO;

/**
 *
 * @author Mcerv
 */
public interface CuentaDAO {
    public CuentaDTO buscarPorNumeroDocumento(String numeroDoc); 
    public CuentaDTO obtenerPorID(int idCuenta);
    public Integer eliminar(Integer id);
    public Integer modificar(CuentaDTO cuenta);
    public Integer insertar(CuentaDTO cuenta);
}
