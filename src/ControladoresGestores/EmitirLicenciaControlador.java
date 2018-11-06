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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author jaque
 */
public class EmitirLicenciaControlador implements ActionListener, MouseListener{
    private Licencia licenciaModelo;
    private EmitirLicenciaVista emitirLicenciaVista;
    private CategorialicenciaDAO categoriaLicenciaDAO;
    private ClaselicenciaDAO claseLicenciaDAO;
    private UsuarioDAO usuarioDAO;
    private TitularDAO titularDAO;
    
    private Titular titular;
    
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
                if(titular.getLicencias().isEmpty()){
                    titularDAO.eliminaTitular(titular);
                    
                    JOptionPane.showMessageDialog(null, "El titular " + titular.getApellido() + " fue eliminado, ya que no posee licencias registradas");
                }
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
        
        if(!(titular == null)){
            Usuario empleado = usuarioDAO.obtenUsuario(titular.getIdempleadogestor());
            setearDatosEmpleado(empleado.getApellido(), empleado.getNombre(), empleado.getEmail(), empleado.getNumerotelefono());
        }
        
        //Tabla de titulares
        modeloTablaTitulares = (DefaultTableModel) emitirLicenciaVista.tablaTitulares.getModel();
        if(titular == null){
            for(Titular t: titularDAO.obtenListaTitulares()){
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
    
    private void setearDatosEmpleado(String apellido, String nombre, String email, String tel){
        emitirLicenciaVista.lblNombreApellidoEmpleado.setText(apellido + " " + nombre);
        emitirLicenciaVista.lblEmailEmpleado.setText(email);
        emitirLicenciaVista.lblTelefonoEmpleado.setText(tel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Obtenemos el primer dato del renglon seleccionado
        if (emitirLicenciaVista.tablaTitulares.getSelectedRow() != -1) {
            String dni = (String) modeloTablaTitulares.getValueAt(emitirLicenciaVista.tablaTitulares.getSelectedRow(), 0);

            // Lo imprimimos en pantalla
            System.out.println("DNI: " + dni);
            
            //Busca y setea el titular con el dni seleccionado
            titular = titularDAO.obtenTitular(dni);
            
            //Setea los datos del empleado asociado al titular seleccionado
            Usuario empleado = usuarioDAO.obtenUsuario(titular.getIdempleadogestor());
            setearDatosEmpleado(empleado.getApellido(), empleado.getNombre(), empleado.getEmail(), empleado.getNumerotelefono());            
        } else {
            System.out.println("Seleccione un renglon primero");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}


