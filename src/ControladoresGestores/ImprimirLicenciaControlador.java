/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresGestores;

import DAOs.UsuarioDAO;
import Entity.Licencia;
import Entity.Usuario;
import Vista.ImprimirLicenciaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import net.sf.jasperreports.engine.*;
import java.util.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Elias Capasso
 */
public class ImprimirLicenciaControlador implements ActionListener{
    ImprimirLicenciaVista imprimirLicenciaVista;
    Licencia licencia;
    int costo;
    public ImprimirLicenciaControlador(ImprimirLicenciaVista vista, Licencia licencia , int costo){
        this.licencia = licencia;
        this.imprimirLicenciaVista = vista;
        this.costo = costo;
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
                imprimirLicencia(licencia);
                
                System.out.println("Se imprime la licencia");
                break;
            case "CANCELAR":
                this.imprimirLicenciaVista.setVisible(false);
                break;
        }
    }
    
    private void imprimirLicencia(Licencia licencia){
        //Implementar
        SimpleDateFormat sm = new SimpleDateFormat("dd/mm/yyyy");
        String path = System.getProperty("user.dir"); 
        String fileName = path.trim() + "\\src\\reportes\\ReporteDeLicencia.jasper";
        HashMap param = new HashMap();
        param.put("REPORTS_DIR", path.trim() +"\\src\\reportes" );
        param.put("NroLicencia", licencia.getNumerolicencia());
        param.put("Apellido", licencia.getTitular().getApellido());
        param.put("Nombre", licencia.getTitular().getNombre() ); 
        param.put("FechaNacimiento", sm.format(licencia.getTitular().getFechanacimiento())); 
        param.put("Domicilio", licencia.getTitular().getDireccion());
        param.put("Nacionalidad", licencia.getTitular().getNacionalidad().getNacionalidad()); 
        param.put("Validez", sm.format(licencia.getFechavencimiento()));
        param.put("Sexo", licencia.getTitular().getSexo().getSexo()); 
        param.put("Donante", "SI");
        param.put("GrupoSanguineo", licencia.getTitular().getGruposanguineo().getGruposanguineo()); 
        param.put("FactorRH", licencia.getTitular().getGruposanguineo().getFactor()); 
        param.put("Clase", licencia.getClaselicencia().getClaselicencia()); 
        param.put("IMG_DIR", path.trim() +"\\src\\fotocarnet\\"+ licencia.getTitular().getNumerodocumento().trim() +".jpg");
        UsuarioDAO daoU = new UsuarioDAO(); 
        Usuario usuario = daoU.obtenUsuario(licencia.getTitular().getIdempleadogestor()); 
        param.put("Empleado" , usuario.getNombre().trim() + " " + usuario.getApellido().trim());
        param.put("obs", licencia.getObservacion()); 
        param.put("FechaEmision" , sm.format(licencia.getFechaemision()));
        //Parametros reporte licencia. 
        
     
        HashMap paramCpago = new HashMap();
        String fileName2 = path.trim() + "\\src\\reportes\\Comprobante.jasper";
        paramCpago.put("NroLicencia", licencia.getTitular().getNumerodocumento().trim());
        paramCpago.put("Apellido", licencia.getTitular().getApellido().trim()); 
        paramCpago.put("Nombre", licencia.getTitular().getNombre().trim());
        paramCpago.put("FechaEmision" , sm.format(licencia.getFechaemision()));
        paramCpago.put("TipoLicencia" , licencia.getClaselicencia().getClaselicencia().trim());
        paramCpago.put("Vigencia", licencia.getVigencia().toString() + " AÑOS");
        paramCpago.put("Precio", costo);
        
                

try {
// llenamos el reporte con un origen de datos vacio donde le pasamos como argumentos
//el nombre del archvios jasper, el hasmap y una JREMptyDataSource que especifica que no habra conexion a
// base de datos
JasperPrint print = JasperFillManager.fillReport(fileName, param, new JREmptyDataSource());
JasperPrint print2 = JasperFillManager.fillReport(fileName2, paramCpago, new JREmptyDataSource());

//lanzamos ej jasper viewer recibiendo coo argumento el informe y un valor boolenano para indicar
// que una vez cerrado el visor, no termine la aplicacion principal
JasperViewer jviewer = new JasperViewer(print,false);
JasperViewer jviewer2 = new JasperViewer(print2,false);

//Establecemos el titulo del visor
jviewer.setTitle("Reporte licencia");
jviewer.setVisible(true);
jviewer2.setTitle("Reporte comprobante");
jviewer2.setVisible(true);


} catch (JRException e) {
e.printStackTrace();
System.exit(1);
}
    }
}
