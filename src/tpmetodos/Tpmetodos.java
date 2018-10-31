/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

import Controlador.CalcularCosto;
import Controlador.EmitirLicenciaControlador;
import DAOs.CostolicenciaDAO;
import DAOs.SexoDAO;
import Entity.Sexo;
import DAOs.HibernateUtil;
import Entity.Costolicencia;
import Entity.CostolicenciaId;
import Entity.Licencia;
import Vista.EmitirLicenciaVista;
import org.hibernate.Session;

/**
 *
 * @author lautaro
 */
public class Tpmetodos {
    
    private Session sesion = HibernateUtil.getSessionFactory().openSession();
   
    public static void main(String[] args) {      
     
     //Creacion vista emitir licencia
     /*Licencia  licenciaModelo = new Licencia();
     EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
     EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(licenciaModelo, emitirLicenciaVista);
     emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
     emitirLicenciaControlador.iniciar();
     emitirLicenciaVista.setVisible(true);*/
     
     
    //int costo = CalcularCosto.CalcularCosto("A", 5);
    //System.out.println(costo);
    
    HibernateUtil.closeSessionFactory();
    }
    
}
