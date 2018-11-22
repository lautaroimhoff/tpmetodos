/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.LicenciaDAO;
import Entity.Licencia;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tinch
 */
public class FiltrarLicenciasVencidasControlador {
    public static ArrayList<Licencia> buscarPorCriterios(String criterio) {
        ArrayList<Licencia> lista = new ArrayList<>();
        lista = LicenciaDAO.buscarPorFechadeVencimiento(criterio);
        return lista;
    }
}
