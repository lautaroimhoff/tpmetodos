/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

import ControladoresGestores.AltaTitularControlador;
import ControladoresGestores.EmitirLicenciaControlador;
import DAOs.HibernateUtil;;
import Vista.EmitirLicenciaVista;
import org.hibernate.Session;
import Entity.Licencia;
import Entity.Titular;
import Vista.AltaTitularVista;

/**
 *
 * @author lautaro
 */
public class Tpmetodos {
    
    private Session sesion = HibernateUtil.getSessionFactory().openSession();
   
    public static void main(String[] args) {       
   //  
     
     //Creacion vista emitir licencia
//     Licencia  licenciaModelo = new Licencia();
//     EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
//     EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(licenciaModelo, emitirLicenciaVista);
//     emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
//     emitirLicenciaControlador.iniciar();
//     emitirLicenciaVista.setVisible(true);
     
     
     //Creacion vista alta titular
    Titular titularModelo = new Titular();
     AltaTitularVista altaTitularVista = new AltaTitularVista();
     AltaTitularControlador altaTitularControlador = new AltaTitularControlador(titularModelo, altaTitularVista);
     altaTitularVista.concetaControlador(altaTitularControlador);
     altaTitularControlador.iniciar();
     altaTitularVista.setVisible(true);
    
    /*Date date = new Date(2000,10,25);
    Calendar calendar = CalcularVigencia.CalcularVigencia(date, "Renovación");
    int duracion = CalcularVigencia.getAñosVigencia(calendar);
    System.out.println(duracion);
    */
    
     //HibernateUtil.closeSessionFactory();
    }
    
}
