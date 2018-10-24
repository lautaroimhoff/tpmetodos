/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entity.Licencia;
import Modelo.EmitirLicenciaModelo;
import Vista.EmitirLicenciaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author jaque
 */
public class EmitirLicenciaControlador implements ActionListener {
    private EmitirLicenciaModelo emitirLicenciaModelo;
    private EmitirLicenciaVista emitirLicenciaVista;

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "ACEPTAR":
                //Crear un objeto Licencia e inicializarlo con los datos ingresados en la pantalla
                break;
            case "CANCELAR":
                this.emitirLicenciaVista.setVisible(false);
        }
    }
    
    public EmitirLicenciaControlador(EmitirLicenciaModelo modelo, EmitirLicenciaVista vista){
        this.emitirLicenciaModelo=modelo;
        this.emitirLicenciaVista=vista;
    }
    
    public void iniciar(){
        emitirLicenciaVista.setTitle("EMITIR LICENCIA");
        emitirLicenciaVista.setLocationRelativeTo(null);
    }
}


