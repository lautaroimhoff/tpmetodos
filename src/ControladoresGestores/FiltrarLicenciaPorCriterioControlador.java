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
                llenarTabla(filtrarLicenciaPorCriterioVista.tfNroLicencia.getText(),filtrarLicenciaPorCriterioVista.tfNombre.getText(), filtrarLicenciaPorCriterioVista.tfApellido.getText(),filtrarLicenciaPorCriterioVista.ftfFechaEmisionIni.getText(), filtrarLicenciaPorCriterioVista.ftfFechaEmisionFin.getText(),filtrarLicenciaPorCriterioVista.ftfFechaExpiracionIni.getText(), filtrarLicenciaPorCriterioVista.ftfFechaExpiracionFin.getText());
            break;
        }
    }
     private void llenarTabla(String nroLicencia, String nombre, String apellido, String fechaEmisionIni, String fechaEmisionFin, String fechaExpiracionIni, String fechaExpiracionFin){
        try {
            llenarTablaLicenciasExpiradas(filtrarLicenciaPorCriterioVista.tabLicencias, getLicenciasExpiradas(nroLicencia, nombre, apellido, fechaEmisionIni, fechaEmisionFin, fechaExpiracionIni, fechaExpiracionFin));
        }
        catch(IndexOutOfBoundsException ex){
            filtrarLicenciaPorCriterioVista.tabLicencias.setModel(new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                 }
             });
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<Licencia> getLicenciasExpiradas(String nroLicencia, String nombre, String apellido, String fechaEmisionIni, String fechaEmisionFin, String fechaExpiracionIni, String fechaExpiracionFin) {
         try{
            List<Licencia> ls = new ArrayList<Licencia>();
            List<Licencia> licencias = licenciaDAO.obtenListaLicencias();
            
            //HACER FILTRADO BIEN, YA PROBÉ QUE ANDA
            for(Licencia lic: licencias){
                if(!nroLicencia.equals("") || !nombre.equals("") || !apellido.equals("") || !fechaEmisionIni.equals("") || !fechaEmisionFin.equals("") || !fechaExpiracionIni.equals("") || !fechaExpiracionFin.equals("")){
                    ls.add(lic);
                    if(!nroLicencia.equals("")){                     
                    }
                    if(!nombre.equals("")){                   
                    }
                    if(!apellido.equals("")){     
                    }
                    if(!fechaEmisionIni.equals("") && !fechaEmisionFin.equals("")){       
                    }
                    if(!fechaExpiracionIni.equals("") && !fechaExpiracionFin.equals("")){ 
                    }
                }
                    
            }
            return ls;
        }
        catch(Exception ex){
            throw ex;
        }
    }
     public static boolean licenciaExpirada(Licencia lic){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(new java.util.Date());
        java.sql.Date fechaAct = java.sql.Date.valueOf(fechaActual);
        return (fechaAct.after(lic.getFechavencimiento()));
    }
    
    
    public static void llenarTablaLicenciasExpiradas(JTable tabla, List<Licencia> list) throws Exception{
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                 }
            };
            tabla.setModel(tableModel);
            if(list.size() > 0){
                tableModel.addColumn("Nro");
                tableModel.addColumn("Nombre");
                tableModel.addColumn("Apellido");
                tableModel.addColumn("Clase");
                tableModel.addColumn("Fecha de Emision");
                tableModel.addColumn("Fecha de Expiracion");
//                tableModel.addColumn("Original");
                for(Licencia lic: list){
                    Vector<Object> rowData = new Vector<Object>();
                    rowData.add(lic.getNumerolicencia());
                    rowData.add(lic.getTitular().getNombre());
                    rowData.add(lic.getTitular().getApellido());
                    rowData.add(lic.getClaselicencia());
                    rowData.add(dateFormat.format(lic.getFechaemision()));
                    rowData.add(dateFormat.format(lic.getFechavencimiento()));
//                    rowData.add(!lic.isEsRenovacion());
                    tableModel.addRow(rowData);
                }
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }

}
