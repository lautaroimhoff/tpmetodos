/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.CategorialicenciaDAO;
import DAOs.ClaselicenciaDAO;
import DAOs.UsuarioDAO;
import Entity.Categorialicencia;
import Entity.Claselicencia;
import Entity.Licencia;
import Entity.Usuario;
import Vista.EmitirLicenciaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


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
    
    private DefaultTableModel modeloTablaEmpleados;

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
                System.exit(0);
        }
    }
    
    public EmitirLicenciaControlador(Licencia modelo, EmitirLicenciaVista vista){
        this.licenciaModelo=modelo;
        this.emitirLicenciaVista=vista;
        this.categoriaLicenciaDAO = new CategorialicenciaDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public void iniciar(){
        emitirLicenciaVista.setTitle("EMITIR LICENCIA");
        
        for(Categorialicencia categoria: categoriaLicenciaDAO.obtenListaCategorialicencias()){
            emitirLicenciaVista.cbListaCategoria.addItem(categoria.getCategorialicencia());
        }
        
        for(Claselicencia clase: claseLicenciaDAO.obtenListaClaselicencias()){
            emitirLicenciaVista.cbListaClaseLicencia.addItem(clase.getClaselicencia());
        }
        
        modeloTablaEmpleados = (DefaultTableModel) emitirLicenciaVista.tablaEmpleados.getModel();
        for(Usuario empleado: usuarioDAO.obtenListaUsuarios()){
            Object filaNueva[] = {empleado.getNombreusuario(), empleado.getApellido(), empleado.getNombre(), empleado.getEmail()};
            modeloTablaEmpleados.addRow(filaNueva);
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


