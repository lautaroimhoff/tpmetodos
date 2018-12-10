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
import Entity.Licencia;
import Entity.Titular;
import Entity.Usuario;
import Vista.EmitirLicenciaVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


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
    private TableRowSorter trs;
    
    public EmitirLicenciaControlador(EmitirLicenciaVista vista){
        this.licenciaModelo = new Licencia();
        this.emitirLicenciaVista = vista;
        this.categoriaLicenciaDAO = new CategorialicenciaDAO();
        this.claseLicenciaDAO = new ClaselicenciaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.titularDAO = new TitularDAO();
        this.licenciaDAO = new LicenciaDAO();
    }
    
    public void iniciar(){
        emitirLicenciaVista.setTitle("EMITIR LICENCIA");
        
        //ComboBox de las categorias
        for(Categorialicencia categoria: categoriaLicenciaDAO.obtenListaCategorialicencias()){
            emitirLicenciaVista.cbListaCategoria.addItem(categoria.getCategorialicencia());
        }
        
        //ComboBox de las clases de licencia
        for(Claselicencia clase: claseLicenciaDAO.obtenListaClaselicencias()){
            emitirLicenciaVista.cbListaClaseLicencia.addItem(clase.getClaselicencia());
        }
        
        //Setea datos del empleado en pantalla
        if(!(titular == null)){
            Usuario empleado = usuarioDAO.obtenUsuario(titular.getIdempleadogestor());
            setearDatosEmpleado(empleado.getApellido(), empleado.getNombre(), empleado.getEmail(), empleado.getNumerotelefono());
        }
        
        modeloTablaTitulares = (DefaultTableModel) emitirLicenciaVista.tablaTitulares.getModel();
        //Si proviene del menu principal
        if(titular == null){
            for(Titular t: titularDAO.obtenListaTitulares()){
                Object filaNueva[] = {t.getNumerodocumento(), t.getApellido(), t.getNombre()};
                modeloTablaTitulares.addRow(filaNueva);
            }
        }
        //Si proviene del alta del titular
        else{
            Object filaNueva[] = {titular.getNumerodocumento(), titular.getApellido(), titular.getNombre()};
            modeloTablaTitulares.addRow(filaNueva);
        }
        
        emitirLicenciaVista.dccFechaEmision.setDate(new Date());
        emitirLicenciaVista.dccFechaEmision.setEnabled(false);
        
        emitirLicenciaVista.setLocationRelativeTo(null);
    }
    
    private void setearDatosEmpleado(String apellido, String nombre, String email, String tel){
        emitirLicenciaVista.lblNombreApellidoEmpleado.setText(apellido + " " + nombre);
        emitirLicenciaVista.lblEmailEmpleado.setText(email);
        emitirLicenciaVista.lblTelefonoEmpleado.setText(tel);
    }
    
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    
    private boolean validarClaseLicenciaProfesional(){
        String claseLicenciaSeleccionada = emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString();
        Date fechaActual = new Date();
        int edadTitular = (int) ((fechaActual.getTime() - titular.getFechanacimiento().getTime()) / 86400000 / 365);
        int anioAntiguedad = 0;
        String categoriaSeleccionada = emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString();
        
        //Para licencias C, D y E
        if(claseLicenciaSeleccionada.equals("C") || claseLicenciaSeleccionada.equals("D") || claseLicenciaSeleccionada.equals("E")){
            //Si es menor a 21 años
            if(edadTitular < 21){
                JOptionPane.showMessageDialog(null, "El titular debe ser mayor a 21 años para la licencia seleccionada");
                return false;
            }
            
            //Si es mayor a 65 años y no renueva
            if(edadTitular > 65 && !categoriaSeleccionada.equals("Renovacion")){
                JOptionPane.showMessageDialog(null, "El titular debe ser menor a 65 años para la categoria y licencia seleccionada");
                return false;
            }
            
            boolean tieneB = false;
            for(Licencia l: titular.getLicencias()){
                if(l.getClaselicencia().getClaselicencia().equals("B")){
                    tieneB = true;
                    
                    //Si tiene licencia B calculo la antigüedad de esa licencia. Si no tiene no importa la antigüedad
                    anioAntiguedad = (int) ((fechaActual.getTime() - l.getFechaemision().getTime()) / 86400000 / 365);
                }
            }
            
            //Si no tiene licencia B o no alcanza la antiguedad
            if(!tieneB || (anioAntiguedad < 1)){
                JOptionPane.showMessageDialog(null, "El titular debe poseer una licencia B con un tiempo no menor a un (1) año para la licencia seleccionada");
                return false;
            }
        }
        
        //Para licencias A, B, F y G
        if(claseLicenciaSeleccionada.equals("A") || claseLicenciaSeleccionada.equals("B") || claseLicenciaSeleccionada.equals("F") || claseLicenciaSeleccionada.equals("G")){
            //Si es menor a 17 años
            if(edadTitular < 17){
                JOptionPane.showMessageDialog(null, "El titular debe ser mayor a 17 años para la licencia seleccionada");
                return false;
            }
        }
        
        return true;
    }
    
    public boolean validarCamposVista(){
        //Debe haber alguna observación
        if(emitirLicenciaVista.tfObservacion.getText().length() == 0){
            return false;
        }
        
        //Debe seeccionarse alguna fecha de emision
        if(emitirLicenciaVista.dccFechaEmision == null){
            return false;
        }
        
        if(titular == null){
            return false;
        }
        return true;
    }
    
    public boolean tieneLicenciaExistente(){
        Claselicencia claseLicenciaSeleccionada;
        claseLicenciaSeleccionada = claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString());
        for(Licencia l : licenciaDAO.obtenListaLicencias()){
            if(l.getClaselicencia().getIdclaselicencia() == claseLicenciaSeleccionada.getIdclaselicencia() && l.getTitular().getNumerodocumento().equals(titular.getNumerodocumento())){
                return true;
            }
        }
        return false;
    }
    
    private int calcularVencimiento(){
        Calendar vigencia = CalcularVigencia.CalcularVigencia(titular.getFechanacimiento(), emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString());
        int aniosVigencia = CalcularVigencia.getAñosVigencia(vigencia);
        
       /* Calendar calendar = Calendar.getInstance();
        titular.setFechagestion(emitirLicenciaVista.dccFechaEmision.getDate());
        calendar.setTime(titular.getFechagestion());
        calendar.add(Calendar.YEAR, aniosVigencia);*/
        
        fechaVencimiento = vigencia.getTime();
        
        return aniosVigencia;
    }    
    
    private void calcularCosto() {
        costo = CalcularCosto.CalcularCosto(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString(), this.calcularVencimiento());
    }
    
    private Licencia crearLicencia(){
        Licencia licencia = new Licencia();
        licencia.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString()));
        licencia.setClaselicencia(claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString()));
        licencia.setFechaemision(emitirLicenciaVista.dccFechaEmision.getDate());
        licencia.setFechavencimiento(fechaVencimiento);
        licencia.setHoraemision(emitirLicenciaVista.dccFechaEmision.getDate()); //TODO:ver
        licencia.setNumerolicencia(0); //TODO:Ver
        licencia.setObservacion(emitirLicenciaVista.tfObservacion.getText());
        licencia.setTitular(titular);
        licencia.setUsuario(usuarioDAO.obtenUsuario(titular.getIdempleadogestor()));
        licencia.setVigencia((short)calcularVencimiento());
        licencia.setVigente(true);
        
        return licencia;
    }
    
    private Licencia actualizarLicencia(Licencia l){
        l.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString()));
        l.setFechaemision(emitirLicenciaVista.dccFechaEmision.getDate());
        l.setHoraemision(emitirLicenciaVista.dccFechaEmision.getDate());
        l.setObservacion(emitirLicenciaVista.tfObservacion.getText());
        
        return l;
    }
    
    private Licencia actualizarLicenciaRenueva(Licencia l){
        l.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString()));
        l.setFechaemision(emitirLicenciaVista.dccFechaEmision.getDate());
        l.setFechavencimiento(fechaVencimiento);
        l.setHoraemision(emitirLicenciaVista.dccFechaEmision.getDate());
        l.setObservacion(emitirLicenciaVista.tfObservacion.getText());
        
        return l;
    }
    
    private Licencia licenciaCopia(Licencia l){
        l.setCategorialicencia(categoriaLicenciaDAO.obtenCategorialicencia(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString()));
        l.setObservacion(emitirLicenciaVista.tfObservacion.getText());
        
        return l;
    }
    
    private void volverMenuPrincipal(){
        //Cierra la pantalla actual y abre el menu principal
        MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
        MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
        menuPrincipalVista.conectaControlador(menuPrincipalControlador);
        menuPrincipalControlador.iniciar();
        emitirLicenciaVista.setVisible(false);
        menuPrincipalVista.setVisible(true);
    }
    
    private void filtrar() {
        trs = new TableRowSorter(modeloTablaTitulares);
        trs.setRowFilter(RowFilter.regexFilter(emitirLicenciaVista.tfFiltroTitular.getText(), 0));
        emitirLicenciaVista.tablaTitulares.setRowSorter(trs);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        switch(comando){
            case "FILTRO_TITULARES":
                filtrar();
                break;
            case "EMITIR":
                if(!validarCamposVista()){
                    JOptionPane.showMessageDialog(null, "Campos inválidos o incompletos");
                }
                else if(validarClaseLicenciaProfesional()){
                    boolean volver = false;
                    calcularVencimiento();
                    calcularCosto();
                    if(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString().equals("Primera vez")){
                        if(!tieneLicenciaExistente()){
                            //Crear un objeto Licencia e inicializarlo con los datos ingresados en la pantalla
                            Licencia licencia = crearLicencia();

                            //Guarda la licencia en la bd
                            licenciaDAO.guardaLicencia(licencia);

                            int seleccion = JOptionPane.showOptionDialog(
                                null,
                                "Licencia creada con éxito\n "
                                + "Fecha de vencieminto: " + new SimpleDateFormat("dd-MM-yyyy").format(fechaVencimiento) + "\n "
                                + "Costo: $" + costo, 
                                "Emisión",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,      // null para icono por defecto.
                                new Object[] { "Imprimir", "Aceptar" },
                                "Imprimir");
                            
                            if(seleccion == JOptionPane.OK_OPTION){
                                imprimirLicencia(licencia , costo);
                                volver = true;
                            }
                            else{
                                volver = true;
                            }

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "El titular ya posee esta licencia");
                            volver = false;
                        }                        
                    }
                    
                    //En caso de que la categoria sea "RENOVACION"
                    if(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString().equals("Renovacion")){
                        if(tieneLicenciaExistente()){
                            Claselicencia claseLicenciaSeleccionada;
                            claseLicenciaSeleccionada = claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString());
                            Licencia l = licenciaDAO.obtenLicencia(titular.getIdtitular(), claseLicenciaSeleccionada.getIdclaselicencia());
                            
                            //Busco las licencias vencidas
                            Date fechaActual = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
                            String criterio = sdf.format(fechaActual);
                            ArrayList<Licencia> vencidas = licenciaDAO.buscarPorFechadeVencimiento(criterio);
                            
                            //Veo si la licencia expiró
                            Licencia aux = null;
                            for(Licencia lic:vencidas){
                                if(lic.getNumerolicencia()==l.getNumerolicencia()){
                                    aux = lic;
                                }
                            }
                            
                            if(aux == null){ //licencia no venció -> se renueva porque se modificaron datos
                                actualizarLicencia(l);
                            }
                            else{ //licencia vencida -> se renueva porque expiró
                                actualizarLicenciaRenueva(l);
                            }
                            
                            //Actualizar la licencia en la bd
                            licenciaDAO.actualizaLicencia(l);
                            JOptionPane.showMessageDialog(null, "Renovación realizada con éxito\n "
                                + "Fecha de vencieminto: " + new SimpleDateFormat("dd-MM-yyyy").format(fechaVencimiento) + "\n "
                                + "Costo: $" + costo);

                            volver = true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "El titular no posee esta licencia");
                            volver = false;
                        }
                    }
                    
                    //En caso de que la categoria sea "COPIA"
                    if(emitirLicenciaVista.cbListaCategoria.getSelectedItem().toString().equals("Copia")){
                        if(tieneLicenciaExistente()){
                            Claselicencia claseLicenciaSeleccionada;
                            claseLicenciaSeleccionada = claseLicenciaDAO.obtenClaselicencia(emitirLicenciaVista.cbListaClaseLicencia.getSelectedItem().toString());
                            Licencia l = licenciaDAO.obtenLicencia(titular.getIdtitular(), claseLicenciaSeleccionada.getIdclaselicencia());
                            //Actualizar la vigencia de la licencia
                            licenciaCopia(l);

                            //Actualizar la licencia en la bd
                            licenciaDAO.actualizaLicencia(l);
                            JOptionPane.showMessageDialog(null, "Copia creada con éxito\n "
                                                            + "Costo: $50");

                            volver = true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "El titular no posee esta licencia");
                            volver = false;
                        }
                    }
                    
                    if(volver){
                        volverMenuPrincipal();
                    }
                }
                break;
            case "CANCELAR":
                if(!(titular == null) && titular.getLicencias().isEmpty()){
                    titularDAO.eliminaTitular(titular);
                    
                    JOptionPane.showMessageDialog(null, "El titular " + titular.getApellido() + " fue eliminado, ya que no posee licencias registradas");
                }
                
                volverMenuPrincipal();
                break;
        }
    }
    
    private void imprimirLicencia(Licencia licencia , int costo){
         
        ImprimirLicenciaControlador imprimirLicenciaControlador = new ImprimirLicenciaControlador(licencia , costo);
        imprimirLicenciaControlador.iniciar();
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
}
