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
public class FiltrarLicenciaPorCriterioControlador implements ActionListener{
    private FiltrarLicenciaPorCriterioVista filtrarLicenciaPorCriterioVista;
    private LicenciaDAO licenciaDAO;
    
    public FiltrarLicenciaPorCriterioControlador (FiltrarLicenciaPorCriterioVista vista){
        this.filtrarLicenciaPorCriterioVista = vista;
        this.licenciaDAO = new LicenciaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FILTRO_LICENCIAS":
                
            break;
        }
    }
}
