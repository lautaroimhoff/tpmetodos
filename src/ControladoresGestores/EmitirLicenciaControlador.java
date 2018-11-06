/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.CategorialicenciaDAO;
import DAOs.ClaselicenciaDAO;
import DAOs.TitularDAO;
import DAOs.UsuarioDAO;
import Entity.Categorialicencia;
import Entity.Claselicencia;
import Entity.Licencia;
import Entity.Titular;
import Entity.Usuario;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author jaque
 */
public class EmitirLicenciaControlador implements ActionListener{
    private Licencia licenciaModelo;
    private EmitirLicenciaVista emitirLicenciaVista;
    private CategorialicenciaDAO categoriaLicenciaDAO;
    private ClaselicenciaDAO claseLicenciaDAO;
    private UsuarioDAO usuarioDAO;
    private TitularDAO titularDAO;
    
    private Titular titular;
    
    private DefaultTableModel modeloTablaEmpleados;
    private DefaultTableModel modeloTablaTitulares;
    
    public EmitirLicenciaControlador(EmitirLicenciaVista vista){
        this.licenciaModelo = new Licencia();
        this.emitirLicenciaVista = vista;
        this.categoriaLicenciaDAO = new CategorialicenciaDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.titularDAO = new TitularDAO();
    }
    
    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "ACEPTAR":
                if(!validarCamposVista()){
                    System.out.println("completar los campos"); 
                    
                    JOptionPane.showMessageDialog(null, "Campos inválidos o incompletos");
                }
                else{
                    //Crear un objeto Licencia e inicializarlo con los datos ingresados en la pantalla
                    
                    JOptionPane.showMessageDialog(null, "Titular creado con éxito");
                }
                break;
            case "CANCELAR":
                this.emitirLicenciaVista.setVisible(false);
                MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
                MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
                menuPrincipalVista.conectaControlador(menuPrincipalControlador);
                menuPrincipalControlador.iniciar();
                menuPrincipalVista.setVisible(true);
        }
    }
    
    public void iniciar(){
        emitirLicenciaVista.setTitle("EMITIR LICENCIA");
        
        for(Categorialicencia categoria: categoriaLicenciaDAO.obtenListaCategorialicencias()){
            emitirLicenciaVista.cbListaCategoria.addItem(categoria.getCategorialicencia());
        }
        
        for(Claselicencia clase: claseLicenciaDAO.obtenListaClaselicencias()){
            emitirLicenciaVista.cbListaClaseLicencia.addItem(clase.getClaselicencia());
        }
        
        //Tabla de empleados
        modeloTablaEmpleados = (DefaultTableModel) emitirLicenciaVista.tablaEmpleados.getModel();
        for(Usuario empleado: usuarioDAO.obtenListaUsuarios()){
            Object filaNueva[] = {empleado.getNombreusuario(), empleado.getApellido(), empleado.getNombre(), empleado.getEmail()};
            modeloTablaEmpleados.addRow(filaNueva);
        }
        
        //Tabla de titulares
        modeloTablaTitulares = (DefaultTableModel) emitirLicenciaVista.tablaTitulares.getModel();
        if(titular == null){
            for(Titular t: titularDAO.obtenListaTitulars()){
                Object filaNueva[] = {t.getNumerodocumento(), t.getApellido(), t.getNombre()};
                modeloTablaTitulares.addRow(filaNueva);
            }
        }
        else{
            Object filaNueva[] = {titular.getNumerodocumento(), titular.getApellido(), titular.getNombre()};
            modeloTablaTitulares.addRow(filaNueva);
        }
        
        
        emitirLicenciaVista.setLocationRelativeTo(null);
    }
    
    public boolean validarCamposVista(){
        if(emitirLicenciaVista.tfObservacion.getText().length()==0){
            return false;
        }
        
        return true;
    }
}


