/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.CategorialicenciaDAO;
import DAOs.ClaselicenciaDAO;
import Entity.Licencia;
import Entity.Usuario;
import Vista.EmitirLicenciaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 *
 * @author jaque
 */
public class EmitirLicenciaControlador implements ActionListener {
    private Licencia licenciaModelo;
    private EmitirLicenciaVista emitirLicenciaVista;
    private CategorialicenciaDAO categoriaLicenciaDAO;
    private ClaselicenciaDAO claseLicenciaDAO;

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "ACEPTAR":
                //Crear un objeto Licencia e inicializarlo con los datos ingresados en la pantalla
                licenciaModelo.setObservacion(emitirLicenciaVista.tfObservacion.getText().toString());
                licenciaModelo.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedIndex())); //TODO:Revisar
                licenciaModelo.setClaselicencia(claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedIndex())); //TODO: revisar
                licenciaModelo.setUsuario(new Usuario()); //TODO: implementar
                licenciaModelo.setFechavencimiento(new Date()); //TODO: implementar
                break;
            case "CANCELAR":
                this.emitirLicenciaVista.setVisible(false);
                System.exit(0);
        }
    }
    
    public EmitirLicenciaControlador(Licencia modelo, EmitirLicenciaVista vista){
        this.licenciaModelo=modelo;
        this.emitirLicenciaVista=vista;
        this.categoriaLicenciaDAO = new CategorialicenciaDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
    }
    
    public void iniciar(){
        emitirLicenciaVista.setTitle("EMITIR LICENCIA");
        emitirLicenciaVista.setLocationRelativeTo(null);
    }
}


