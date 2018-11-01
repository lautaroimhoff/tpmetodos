/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAOs.TitularDAO;
import Entity.Titular;
import Vista.AltaTitularVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jaque
 */
public class AltaTitularControlador implements ActionListener{
    
    private Titular titularModelo;
    private AltaTitularVista altaTitularVista;
    private TitularDAO titularDAO;
   

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FINALIZAR CARGA":
                if(!validarCamposVista()){
                    System.out.println("completar los campos"); 
                }
                else{
                    //Crear un objeto titular e inicializarlo con los datos ingresados en la pantall
                    
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
    }
    
    public void iniciar(){
        altaTitularVista.setTitle("ALTA DEL TITULAR");
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
        
        return false;
    }
}
