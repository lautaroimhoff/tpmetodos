/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

import ControladoresGestores.MenuPrincipalControlador;
import DAOs.LicenciaDAO;
import Entity.Licencia;
import Vista.MenuPrincipalVista;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Session;
;

/**
 *
 * @author lautaro
 */
public class Tpmetodos {
    
    //private Session sesion = HibernateUtil.getSessionFactory().openSession();
   
    public static void main(String[] args) {     
        //Creacion vista alta titular
        MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
        MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
        menuPrincipalVista.conectaControlador(menuPrincipalControlador);
        menuPrincipalControlador.iniciar();
        menuPrincipalVista.setVisible(true);
       
        /*Date date = new Date(2000,10,25);
        Calendar calendar = CalcularVigencia.CalcularVigencia(date, "Renovación");
        int duracion = CalcularVigencia.getAñosVigencia(calendar);
        System.out.println(duracion);
        */

         //HibernateUtil.closeSessionFactory();
}
}