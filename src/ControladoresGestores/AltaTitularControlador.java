/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.ClaselicenciaDAO;
import DAOs.ContribuyenteDAO;
import DAOs.GruposanguineoDAO;
import DAOs.NacionalidadDAO;
import DAOs.SexoDAO;
import DAOs.TipodocumentoDAO;
import DAOs.TitularDAO;
import Entity.Claselicencia;
import Entity.Contribuyente;
import Entity.Gruposanguineo;
import Entity.Nacionalidad;
import Entity.Titular;
import Vista.AltaTitularVista;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jaque
 */
public class AltaTitularControlador implements ActionListener, MouseListener{
    
    private Titular titularModelo;
    private AltaTitularVista altaTitularVista;
    private TitularDAO titularDAO;
    private NacionalidadDAO nacionalidadDAO;   
    private ClaselicenciaDAO claseLicenciaDAO;
    private TipodocumentoDAO tipoDocumentoDAO;
    private ContribuyenteDAO contribuyenteDAO;
    private Contribuyente contribuyente;
        
    private DefaultTableModel modeloTablaContribuyentes;
    private TableRowSorter trs;

    public AltaTitularControlador(AltaTitularVista vista){
        this.titularModelo= new Titular();
        this.altaTitularVista = vista;
        this.titularDAO = new TitularDAO();
        this.nacionalidadDAO = new NacionalidadDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.tipoDocumentoDAO = new TipodocumentoDAO();
        this.contribuyenteDAO = new ContribuyenteDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FILTRO_TITULARES":
                filtrar();
                break;
            case "FINALIZAR CARGA":
                if(validarRestriccionEdadClaseLicencia() && validarCamposVista()){
                    //Crear un objeto titular e inicializarlo con los datos ingresados en la pantalla
                    Titular titularDTO = new Titular();
                    Calendar fechaActual = Calendar.getInstance();
                    TipodocumentoDAO tipoDocumentoDAO = new TipodocumentoDAO();
                    SexoDAO sexoDAO = new SexoDAO();
                    titularDTO.setNombre(contribuyente.getNombre().trim());
                    titularDTO.setApellido(contribuyente.getApellido().trim());
                    titularDTO.setTipodocumento(tipoDocumentoDAO.obtenTipodocumento(contribuyente.getTipoDocumento().trim()));
                    titularDTO.setNumerodocumento(contribuyente.getDocumento().trim());
                    if (altaTitularVista.optMasculinoTitular.isSelected()){
                        titularDTO.setSexo(sexoDAO.obtenSexo("Masculino"));
                    }
                    else {
                        titularDTO.setSexo(sexoDAO.obtenSexo("Femenino"));
                    }
                    Nacionalidad nacionalidad = obtenerNacionalidad(altaTitularVista.cbNacionalidadTitular.getSelectedItem().toString());
                    titularDTO.setNacionalidad(nacionalidad);
                    //java.sql.Date se instancia pasándole un tipo de dato long a su constructor. ".getTime()" devuelve un long el cual se pasa al constructor new java.sql.Date(long)
                    titularDTO.setFechanacimiento(new java.sql.Date(altaTitularVista.jDateChooser_FechaNacimiento.getDate().getTime()));
                    titularDTO.setDireccion(altaTitularVista.tfDireccionTitular.getText());
                    Gruposanguineo grupoSanguineo = obtenerGrupoSanguineo(altaTitularVista.cbGrupoSanguineoTitular.getSelectedItem().toString(), altaTitularVista.cbRHTitular.getSelectedItem().toString());
                    titularDTO.setGruposanguineo(grupoSanguineo);
                    if (altaTitularVista.optDonanteSiTitular.isSelected()) {
                        titularDTO.setDonante((short) 1);
                    } else {
                        titularDTO.setDonante((short) 0);
                    }
                    titularDTO.setIdempleadogestor((short) 1);
                    titularDTO.setFechagestion(new java.sql.Date(fechaActual.getTime().getTime()));
                    
                    titularDAO.guardaTitular(titularDTO);
                    
                    JOptionPane.showMessageDialog(null, "Titular creado con éxito");
                    
                    EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
                    EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(emitirLicenciaVista);
                    emitirLicenciaControlador.setTitular(titularDTO);
                    emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
                    emitirLicenciaControlador.iniciar();
                    emitirLicenciaVista.setVisible(true);                                                                                                               
                    this.altaTitularVista.setVisible(false);
                }
                break;
            case "CANCELAR":
                this.altaTitularVista.setVisible(false);
                MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
                MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
                menuPrincipalVista.conectaControlador(menuPrincipalControlador);
                menuPrincipalControlador.iniciar();
                menuPrincipalVista.setVisible(true);
        }
    }
    
    private void filtrar() {
        trs = new TableRowSorter(modeloTablaContribuyentes);
        trs.setRowFilter(RowFilter.regexFilter(altaTitularVista.tfFiltroContribuyente.getText(), 0));
        altaTitularVista.tablaContribuyentes.setRowSorter(trs);
    }
    
    public void iniciar(){
        altaTitularVista.setTitle("ALTA DEL TITULAR");
        
        for(Nacionalidad nacionalidad: nacionalidadDAO.obtenListaNacionalidades()){
            altaTitularVista.cbNacionalidadTitular.addItem(nacionalidad.getNacionalidad());
        }
        
        for(Claselicencia claseLicencia: claseLicenciaDAO.obtenListaClaselicencias()){
            altaTitularVista.cbClaseLicenciaTitular.addItem(claseLicencia.getClaselicencia());
        }
        
        modeloTablaContribuyentes = (DefaultTableModel) altaTitularVista.tablaContribuyentes.getModel();
        for(Contribuyente c: contribuyenteDAO.obtenListaContribuyentes()){
            Object filaNueva[] = {c.getDocumento().trim(), c.getApellido().trim(), c.getNombre().trim()};
                modeloTablaContribuyentes.addRow(filaNueva);
        }
                
        altaTitularVista.setLocationRelativeTo(null);
    }
    
    public boolean validarCamposVista(){
        
        if(altaTitularVista.tfDireccionTitular.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe ingresar una dirección");
            return false;
        }
        if(!altaTitularVista.optFemeninoTitular.isSelected() && !altaTitularVista.optMasculinoTitular.isSelected()){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un sexo");
            return false;
        }
        if(!altaTitularVista.optDonanteSiTitular.isSelected() && !altaTitularVista.optDonanteNoTitular.isSelected()){
            JOptionPane.showMessageDialog(null, "Debe seleccionar si el titular es donante o no");
            return false;
        }
        String dni = (String) modeloTablaContribuyentes.getValueAt(altaTitularVista.tablaContribuyentes.getSelectedRow(), 0);
        for(Titular titular: titularDAO.obtenListaTitulares()){
            if(titular.getNumerodocumento().equals(dni)){
                JOptionPane.showMessageDialog(null, "El contribuyente ya se encuentra registrado como titular");
                return false;
            }
        }
        return true;
    }
    
    private Boolean validarRestriccionEdadClaseLicencia(){
     //VALIDA QUE PARA LAS LICENCIAS NO PROFESIONALES SE TENGA MAS DE 17 AÑOS
        Boolean valido = false;
        String clase = altaTitularVista.cbClaseLicenciaTitular.getSelectedItem().toString();
        java.util.Date fechaActual = new java.util.Date();
        int edad = (int) ((fechaActual.getTime() - altaTitularVista.jDateChooser_FechaNacimiento.getDate().getTime()) / 86400000 / 365);
        if(edad >= 17 && (clase.equals("A") || clase.equals("B") || clase.equals("F") || clase.equals("G"))){
            valido = true;
        }
        else if (edad >= 21 && (clase.equals("C") || clase.equals("D") || clase.equals("E"))){
            valido = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "La edad mínima para obtener una licencia de clase A, B, F o G es de 17 años y para clases C, D o E es de 21 años.", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        return valido;
    }
    
    public Gruposanguineo obtenerGrupoSanguineo (String tipoSangre, String factor){
       GruposanguineoDAO gruposanguineoDAO= new GruposanguineoDAO();
       Gruposanguineo grupoSanguineoAux = null;
       for (Gruposanguineo grupoSanguineo: gruposanguineoDAO.obtenListaGruposanguineos()){
           if(grupoSanguineo.getGruposanguineo().equals(tipoSangre) && grupoSanguineo.getFactor().equals(factor)){
               grupoSanguineoAux = grupoSanguineo;
           }
       }
        
       return grupoSanguineoAux;
    }
    
    public Nacionalidad obtenerNacionalidad (String nacionalidadElegida){
        NacionalidadDAO nacionalidadDAO = new NacionalidadDAO();
        Nacionalidad nacionalidadAux = null;
        for (Nacionalidad nacionalidad: nacionalidadDAO.obtenListaNacionalidades()){
            if(nacionalidad.getNacionalidad().equals(nacionalidadElegida)){
                nacionalidadAux = nacionalidad;
            }
        }        
        
        return nacionalidadAux;
    }
    
    public void mouseClicked(MouseEvent e) {
        // Obtenemos el primer dato del renglon seleccionado
        if (altaTitularVista.tablaContribuyentes.getSelectedRow() != -1) {
            String dni = (String) modeloTablaContribuyentes.getValueAt(altaTitularVista.tablaContribuyentes.getSelectedRow(), 0);
            System.out.println("DNI: " + dni);
            
            //Busca y setea el contribuyente con el dni seleccionado
            contribuyente = contribuyenteDAO.obtenContribuyente(dni);            
        }else{
            System.out.println("Seleccione un renglon primero");
        }
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
}
