/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import Calculos.CalcularCosto;
import Calculos.CalcularVigencia;
import DAOs.CategorialicenciaDAO;
import DAOs.ClaselicenciaDAO;
import DAOs.LicenciaDAO;
import DAOs.TitularDAO;
import DAOs.UsuarioDAO;
import Entity.Categorialicencia;
import Entity.Claselicencia;
import Entity.Costolicencia;
import Entity.Licencia;
import Entity.Titular;
import Entity.Usuario;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
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
    private LicenciaDAO licenciaDAO;
    
    private Titular titular;
    private Date fechaVencimiento = new Date();
    private int costo = 0;
    
    private DefaultTableModel modeloTablaTitulares;
    
    public EmitirLicenciaControlador(EmitirLicenciaVista vista){
        this.licenciaModelo = new Licencia();
        this.emitirLicenciaVista = vista;
        this.categoriaLicenciaDAO = new CategorialicenciaDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.titularDAO = new TitularDAO();
        this.licenciaDAO = new LicenciaDAO();
    }
    
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    
    private boolean validarClaseLicenciaProfesional(){
        /*VALIDA LA RESTRICCION QUE PARA PEDIR UNA LICENCIA PROFESIONAL EL TITULAR TENGA UNA LICENCIA B CON UN AÑO DE EMISION NO MENOR A 1 AÑO*/
        Boolean tieneLicenciaB = false;    
        
         String claselicenciaseleccionada = emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString();
        
        //obtener id clase licencia b o ver como manejar eso (idClaseLicenciaB)
        for(Licencia l: titular.getLicencias()) {
           
            if (l.getClaselicencia().getIdclaselicencia() == 2 && (claselicenciaseleccionada.equals("C") || claselicenciaseleccionada.equals("D") || claselicenciaseleccionada.equals("E"))) {
                
                tieneLicenciaB = true;
                Date fechaActual = new Date();
                int edad = (int) ((fechaActual.getTime() - titular.getFechanacimiento().getTime()) / 86400000 / 365);
                Integer anioAntiguedad = (int) ((fechaActual.getTime() - titular.getFechagestion().getTime()) / 86400000 / 365);
                
                if (anioAntiguedad < 1 && edad >= 21) {
                    JOptionPane.showMessageDialog(null, "Para licencias profesionales (C, D o E) debe tener una licencia B con una fecha de emisión de un (1) año como mínimo y el titular debe ser mayor a 21 años");
                    return false;
                }
            }
            
        }
       if (!(tieneLicenciaB) && (claselicenciaseleccionada.equals("C") || claselicenciaseleccionada.equals("D") || claselicenciaseleccionada.equals("E"))) {
            JOptionPane.showMessageDialog(null, "Para licencias profesionales (C, D o E) debe tener una licencia B con una fecha de emisión de un (1) año como mínimo");
            return false;
        }
        
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "ACEPTAR":
                if(!validarCamposVista()){
                    JOptionPane.showMessageDialog(null, "Campos inválidos o incompletos");
                }
                
                if(validarClaseLicenciaProfesional()){
                    //Crear un objeto Licencia e inicializarlo con los datos ingresados en la pantalla
                    Licencia licencia = new Licencia();
                    licencia.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString()));
                    licencia.setClaselicencia(claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString()));
                    licencia.setFechaemision(titular.getFechagestion());
                    licencia.setFechavencimiento(fechaVencimiento);
                    licencia.setHoraemision(new Date()); //TODO:ver
                    licencia.setNumerolicencia(0); //TODO:Ver
                    licencia.setObservacion(emitirLicenciaVista.tfObservacion.getText().toString());
                    licencia.setTitular(titular);
                    licencia.setUsuario(usuarioDAO.obtenUsuario(titular.getIdempleadogestor()));
                    licencia.setVigencia((short)calcularVencimiento());
                    licencia.setVigente(true);
                    
                    licenciaDAO.guardaLicencia(licencia);
                    
                    calcularVencimiento();
                    calcularCosto();
                    JOptionPane.showMessageDialog(null, "Titular creado con éxito\n "
                                                    + "Fecha de vencieminto: " + fechaVencimiento.toString() + "\n "
                                                    + "Costo: $" + costo);
                    
                    this.emitirLicenciaVista.setVisible(false);
                    MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
                    MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
                    menuPrincipalVista.conectaControlador(menuPrincipalControlador);
                    menuPrincipalControlador.iniciar();
                    menuPrincipalVista.setVisible(true);
                }
                break;
            case "CANCELAR":
                if(!(titular == null) && titular.getLicencias().isEmpty()){
                    titularDAO.eliminaTitular(titular);
                    
                    JOptionPane.showMessageDialog(null, "El titular " + titular.getApellido() + " fue eliminado, ya que no posee licencias registradas");
                }
                this.emitirLicenciaVista.setVisible(false);
                MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
                MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
                menuPrincipalVista.conectaControlador(menuPrincipalControlador);
                menuPrincipalControlador.iniciar();
                menuPrincipalVista.setVisible(true);    
                break;
        }
    }
    
    private int calcularVencimiento(){
        Calendar vigencia = CalcularVigencia.CalcularVigencia(titular.getFechanacimiento(), emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString());
        int aniosVigencia = CalcularVigencia.getAñosVigencia(vigencia);
        
        Calendar calendar = Calendar.getInstance();
        titular.setFechagestion(emitirLicenciaVista.dccFechaEmision.getDate());
        calendar.setTime(titular.getFechagestion());
        calendar.add(Calendar.YEAR, aniosVigencia);
        
        fechaVencimiento = calendar.getTime();
        
        return aniosVigencia;
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
        
        validarRestriccionEdadClaseLicencia();
        
        return true;
    }
    
    private Boolean validarRestriccionEdadClaseLicencia(){
        //VALIDA QUE PARA LAS LICENCIAS NO PROFESIONALES SE TENGA MAS DE 17 AÑOS
        Boolean valido = false;
        String clase = emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString();
        java.util.Date fechaActual = new java.util.Date();
        int edad = (int) ((fechaActual.getTime() - titular.getFechanacimiento().getTime()) / 86400000 / 365);
        if(edad >= 17 && (clase.equals("A") || clase.equals("B") || clase.equals("F") || clase.equals("G"))){
            valido = true;
        }
        else if (edad >= 21 && (clase.equals("C") || clase.equals("D") || clase.equals("E"))){
            valido = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "La edad mínima para obtener una licencia de clase A, B, F o G es de 17 años y para clases C, D o E es de 21 años.", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        return valido;
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
            System.out.println("DNI: " + dni);
            
            //Busca y setea el titular con el dni seleccionado
            titular = titularDAO.obtenTitular(dni);
            
            //Setea los datos del empleado asociado al titular seleccionado
            Usuario empleado = usuarioDAO.obtenUsuario(titular.getIdempleadogestor());
            setearDatosEmpleado(empleado.getApellido(), empleado.getNombre(), empleado.getEmail(), empleado.getNumerotelefono());            
        }else{
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

    private void calcularCosto() {
        costo = CalcularCosto.CalcularCosto(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString(), this.calcularVencimiento());
        
        
    }
}


