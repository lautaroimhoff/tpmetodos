/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import Entity.Licencia;
import Entity.Titular;
import Vista.AltaTitularVista;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Elias Capasso
 */
public class MenuPrincipalControlador implements ActionListener{

    private MenuPrincipalVista menuPrincipalVista;
    
    public MenuPrincipalControlador(MenuPrincipalVista menuPrincipalVista) {
        this.menuPrincipalVista = menuPrincipalVista;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "EMITIR_LICENCIA":
                EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
                EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(emitirLicenciaVista);
                emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
                emitirLicenciaControlador.iniciar();
                emitirLicenciaVista.setVisible(true);                                                                                                               
                this.menuPrincipalVista.setVisible(false);
                break;
            case "ALTA_TITULAR":
                AltaTitularVista altaTitularVista = new AltaTitularVista();
                AltaTitularControlador altaTitularControlador = new AltaTitularControlador(altaTitularVista);
                altaTitularVista.concetaControlador(altaTitularControlador);
                altaTitularControlador.iniciar();
                altaTitularVista.setVisible(true);                                                                                                     
                this.menuPrincipalVista.setVisible(false);
                break;
        }
    }
    
    public void iniciar(){
        menuPrincipalVista.setTitle("MENU PRINCIPAL");
        menuPrincipalVista.setLocationRelativeTo(null);
    }
    
}
