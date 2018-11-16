/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import Entity.Licencia;
import Vista.ImprimirLicenciaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Elias Capasso
 */
public class ImprimirLicenciaControlador implements ActionListener{
    ImprimirLicenciaVista imprimirLicenciaVista;
    Licencia licencia;
    
    public ImprimirLicenciaControlador(ImprimirLicenciaVista vista, Licencia licencia){
        this.licencia = licencia;
        this.imprimirLicenciaVista = vista;
    }
    
    public void iniciar(){
        imprimirLicenciaVista.setTitle("IMPRIMIR LICENCIA");
        imprimirLicenciaVista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        switch(comando){
            case "IMPRIMIR":
                imprimirLicencia();
                
                System.out.println("Se imprime la licencia");
                break;
            case "CANCELAR":
                this.imprimirLicenciaVista.setVisible(false);
                break;
        }
    }
    
    private void imprimirLicencia(){
        //Implementar
    }
}
