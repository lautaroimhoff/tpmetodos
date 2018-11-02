/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.ClaselicenciaDAO;
import DAOs.NacionalidadDAO;
import DAOs.TitularDAO;
import Entity.Claselicencia;
import Entity.Nacionalidad;
import Entity.Titular;
import Vista.AltaTitularVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                System.exit(0);
        }
    }
    
    public AltaTitularControlador(Titular modelo, AltaTitularVista vista){
        this.titularModelo=modelo;
        this.altaTitularVista=vista;
        this.titularDAO = new TitularDAO();
        this.nacionalidadDAO = new NacionalidadDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
    }
    
    public void iniciar(){
        altaTitularVista.setTitle("ALTA DEL TITULAR");
        
        for(Nacionalidad nacionalidad: nacionalidadDAO.obtenListaNacionalidades()){
            altaTitularVista.cbNacionalidadTitular.addItem(nacionalidad.getNacionalidad());
        }
        
        for(Claselicencia claseLicencia: claseLicenciaDAO.obtenListaClaselicencias()){
            altaTitularVista.cbClaseLicenciaTitular.addItem(claseLicencia.getClaselicencia());
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
        if(altaTitularVista.tfNumDocumetoTitular.getText().length()==0){
            return false;
        }
        if(!altaTitularVista.optFemeninoTitular.isSelected() && !altaTitularVista.optMasculinoTitular.isSelected()){
            return false;
        }
        if(!altaTitularVista.optDonanteSiTitular.isSelected() && !altaTitularVista.optDonanteNoTitular.isSelected()){
            return false;
        }
        
        for(Titular titular: titularDAO.obtenListaTitulars()){
            if(titular.getNumerodocumento().equals(Integer.parseInt(altaTitularVista.tfNumDocumetoTitular.getText().toString()))){
                return false;
            }
        }
        
        return true;
    }
}
