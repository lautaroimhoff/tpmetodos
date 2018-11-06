/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.ClaselicenciaDAO;
import DAOs.GruposanguineoDAO;
import DAOs.NacionalidadDAO;
import DAOs.SexoDAO;
import DAOs.TipodocumentoDAO;
import DAOs.TitularDAO;
import Entity.Claselicencia;
import Entity.Gruposanguineo;
import Entity.Licencia;
import Entity.Nacionalidad;
import Entity.Tipodocumento;
import Entity.Titular;
import Vista.AltaTitularVista;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author jaque
 */
public class AltaTitularControlador implements ActionListener{
    
    private Titular titularModelo;
    private AltaTitularVista altaTitularVista;
    private TitularDAO titularDAO;
    private NacionalidadDAO nacionalidadDAO;   
    private ClaselicenciaDAO claseLicenciaDAO;
    private TipodocumentoDAO tipoDocumentoDAO;

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FINALIZAR CARGA":
                if(validarRestriccionEdadClaseLicencia() && validarCamposVista()){
                    //Crear un objeto titular e inicializarlo con los datos ingresados en la pantalla
                    Titular titularDTO = new Titular();
                    Calendar fechaActual = Calendar.getInstance();
                    TipodocumentoDAO tipoDocumentoDAO = new TipodocumentoDAO();
                    SexoDAO sexoDAO = new SexoDAO();
                    titularDTO.setNombre(altaTitularVista.tfNombreTitular.getText());
                    titularDTO.setApellido(altaTitularVista.tfApellidoTitular.getText());
                    titularDTO.setTipodocumento(tipoDocumentoDAO.obtenTipodocumento(altaTitularVista.cbTipoDocumentoTitular.getSelectedItem().toString()));
                    titularDTO.setNumerodocumento(altaTitularVista.tfNumDocumentoTitular.getText());
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
                    
                    Licencia  licenciaModelo = new Licencia();
                    EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
                    EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(licenciaModelo, emitirLicenciaVista);
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
    
    public AltaTitularControlador(Titular modelo, AltaTitularVista vista){
        this.titularModelo=modelo;
        this.altaTitularVista=vista;
        this.titularDAO = new TitularDAO();
        this.nacionalidadDAO = new NacionalidadDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.tipoDocumentoDAO = new TipodocumentoDAO();
    }
    
    public void iniciar(){
        altaTitularVista.setTitle("ALTA DEL TITULAR");
        
        for(Nacionalidad nacionalidad: nacionalidadDAO.obtenListaNacionalidades()){
            altaTitularVista.cbNacionalidadTitular.addItem(nacionalidad.getNacionalidad());
        }
        
        for(Claselicencia claseLicencia: claseLicenciaDAO.obtenListaClaselicencias()){
            altaTitularVista.cbClaseLicenciaTitular.addItem(claseLicencia.getClaselicencia());
        }
        
        for(Tipodocumento tipoDocumento: tipoDocumentoDAO.obtenListaTipodocumentos()){
            altaTitularVista.cbTipoDocumentoTitular.addItem(tipoDocumento.getTipodocumento());
        }
        
        altaTitularVista.setLocationRelativeTo(null);
    }
    
    public boolean validarCamposVista(){
        if(altaTitularVista.tfNombreTitular.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            return false;
        }
        if(altaTitularVista.tfApellidoTitular.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe ingresar un apellido");
            return false;
        }
        if(altaTitularVista.tfDireccionTitular.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe ingresar una dirección");
            return false;
        }
        if(altaTitularVista.tfNumDocumentoTitular.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero de documento");
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
        for(Titular titular: titularDAO.obtenListaTitulars()){
            if(titular.getNumerodocumento().equals(altaTitularVista.tfNumDocumentoTitular.getText())){
                JOptionPane.showMessageDialog(null, "Documento ya existente");
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
}
