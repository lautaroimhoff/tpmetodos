/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DAOs.ClaselicenciaDAO;
import Entity.Claselicencia;


/**
 *
 * @author tinch
 */
public class GestorClaseLicencia {
     public static int getIdClaseLicencia(String claseLicencia){
         ClaselicenciaDAO claselicenciaDAO = new ClaselicenciaDAO();
         Claselicencia claselicencia = claselicenciaDAO.obtenClaselicencia(claseLicencia);
        return claselicencia.getIdclaselicencia();
    }
}
