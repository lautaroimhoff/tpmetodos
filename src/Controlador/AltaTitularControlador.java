/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
   

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "FINALIZAR CARGA":
                //Crear un objeto titular e inicializarlo con los datos ingresados en la pantalla
               
                break;
            case "CANCELAR":
                this.altaTitularVista.setVisible(false);
        }
    }
    
    public AltaTitularControlador(Titular modelo, AltaTitularVista vista){
        this.titularModelo=modelo;
        this.altaTitularVista=vista;
    }
    
    public void iniciar(){
        altaTitularVista.setTitle("ALTA DEL TITULAR");
        altaTitularVista.setLocationRelativeTo(null);
    }
    
}
