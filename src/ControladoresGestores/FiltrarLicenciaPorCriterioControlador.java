/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.LicenciaDAO;
import Entity.Licencia;
import Vista.FiltrarLicenciaPorCriterioVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tinch
 */
public class FiltrarLicenciaPorCriterioControlador{
   
    public static ArrayList<Licencia> buscarPorCriterios(ArrayList<Object> criterios) {

        ArrayList<Object> criteriosArray = new ArrayList();

        criteriosArray.add(criterios.get(0)); //nombre
        criteriosArray.add(criterios.get(1)); //apellido
        
        //dni
        if (!criterios.get(2).equals("")) {
            criteriosArray.add((String) criterios.get(2));
        } else {
            criteriosArray.add(null);
        }
        
        criteriosArray.add(criterios.get(3)); //grupo
        criteriosArray.add(criterios.get(4)); //factor
        criteriosArray.add(criterios.get(5)); //clase
        criteriosArray.add((Boolean) criterios.get(6)); //donanteSi

        ArrayList<Licencia> lista = new ArrayList<>();
        lista = LicenciaDAO.buscarPorCriterios(
                (String) criteriosArray.get(0),
                (String) criteriosArray.get(1),
                (String) criteriosArray.get(2),
                (String) criteriosArray.get(3),
                (String) criteriosArray.get(4),
                (String) criteriosArray.get(5),
                (Boolean) criteriosArray.get(6)
        );
        return lista;
    }
}
