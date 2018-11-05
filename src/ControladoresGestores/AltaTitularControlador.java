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
import Entity.Nacionalidad;
import Entity.Tipodocumento;
import Entity.Titular;
import Vista.AltaTitularVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
    private Titular titularDTO = new Titular();

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FINALIZAR CARGA":
                if(!validarCamposVista()){
                    System.out.println("completar los campos"); 
                    
                    JOptionPane.showMessageDialog(null, "Campos inválidos o incompletos");
                }
                else{
                    //Crear un objeto titular e inicializarlo con los datos ingresados en la pantalla
                    
                    
                    JOptionPane.showMessageDialog(null, "Titular creado con éxito");
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
            return false;
        }
        if(altaTitularVista.tfApellidoTitular.getText().length()==0){
            return false;
        }
        if(altaTitularVista.tfDireccionTitular.getText().length()==0){
            return false;
        }
        if(altaTitularVista.tfNumDocumentoTitular.getText().length()==0){
            return false;
        }
        if(!altaTitularVista.optFemeninoTitular.isSelected() && !altaTitularVista.optMasculinoTitular.isSelected()){
            return false;
        }
        if(!altaTitularVista.optDonanteSiTitular.isSelected() && !altaTitularVista.optDonanteNoTitular.isSelected()){
            return false;
        }
        
        for(Titular titular: titularDAO.obtenListaTitulars()){
            if(titular.getNumerodocumento().equals(Integer.parseInt(altaTitularVista.tfNumDocumentoTitular.getText().toString()))){
                return false;
            }
        }
 
        return true;
    }
    
    private void setTitularDTO(){
        //LLENA LOS DATOS EN EL OBJETO DTO PARA DESPUES EFECTUAR EL ALTA DEL TITULAR
       
        String obtenerSexo;
        Calendar fechaActual = Calendar.getInstance();
        TipodocumentoDAO tipoDocumentoDAO = new TipodocumentoDAO();
        SexoDAO sexoDAO= new SexoDAO();
        GruposanguineoDAO gruposanguineoDAO= new GruposanguineoDAO();
        titularDTO.setNombre(altaTitularVista.tfNombreTitular.getText());
        titularDTO.setApellido(altaTitularVista.tfApellidoTitular.getText());
        titularDTO.setTipodocumento((Tipodocumento) altaTitularVista.cbTipoDocumentoTitular.getSelectedItem());
        //titularDTO.setTipodocumento(tipoDocumentoDAO.obtenTipodocumento(cbTipoDocumentoTitular.getSelectedItem().toString()));
        titularDTO.setNumerodocumento(altaTitularVista.tfNumDocumentoTitular.getText());
        obtenerSexo = getSelectedButtonText(altaTitularVista.sexo);
        titularDTO.setSexo(sexoDAO.obtenSexo(obtenerSexo));
        titularDTO.setNacionalidad((Nacionalidad) altaTitularVista.cbNacionalidadTitular.getSelectedItem());
        //java.sql.Date se instancia pasándole un tipo de dato long a su constructor. ".getTime()" devuelve un long el cual se pasa al constructor new java.sql.Date(long)
        titularDTO.setFechanacimiento(new java.sql.Date(altaTitularVista.jDateChooser_FechaNacimiento.getDate().getTime()));
        titularDTO.setDireccion(altaTitularVista.tfDireccionTitular.getText());
        /*
        String grupoSanguineo = cbGrupoSanguineoTitular.getSelectedItem().toString();
        String factor = cbRHTitular.getSelectedItem().toString();
        
        Gruposanguineo gruposanguineo = new Gruposanguineo();
        
        titularDTO.setGrupoSanguineo(gruposanguineoDAO.));
        if (jRadioButtonDonanteSi.isSelected()) {
            titularDTO.setDonante(1);
        } else {
            titularDTO.setDonante(0);
        }
        titularDTO.setIdEmpeladoGestor(GestorUsuario.getUsuarioActivo().getIdUsuario());
        titularDTO.setFechaGestion(new java.sql.Date(fechaActual.getTime().getTime()));*/
    }
  
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
