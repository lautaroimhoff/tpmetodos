/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculos;

import ControladoresGestores.ClaseLicenciaGestor;
import DAOs.CostolicenciaDAO;
import Entity.Costolicencia;
import Entity.CostolicenciaId;

/**
 *
 * @author tinch
 */
public class CalcularCosto {
     private static int CostoAdministrativo = 8;

    public static int CalcularCosto(String claseLicencia, int vigencia){
        int idClaseLicencia = ClaseLicenciaGestor.getIdClaseLicencia(claseLicencia);
        Costolicencia costo; 
        CostolicenciaId id = new CostolicenciaId((short)idClaseLicencia,vigencia); 
        CostolicenciaDAO dao = new CostolicenciaDAO();
        costo = dao.obtenCostolicencia(id);
        int costoTotal = costo.getPrecio() + CostoAdministrativo;

        return costoTotal;
    }
}
