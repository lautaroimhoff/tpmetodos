/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

import Controlador.EmitirLicenciaControlador;
import DAOs.SexoDAO;
import DAOs.UsuarioDAO;
import Entity.Sexo;
import Entity.Usuario;
import DAOs.HibernateUtil;
import Modelo.EmitirLicenciaModelo;
import Vista.EmitirLicenciaVista;
import org.hibernate.Session;

/**
 *
 * @author lautaro
 */
public class Tpmetodos {
    
    private Session sesion = HibernateUtil.getSessionFactory().openSession();
   
    public static void main(String[] args) {      
      /*SexoDAO sexo = new SexoDAO();
      Sexo sex = new Sexo();
      sex = sexo.obtenSexo(1);
      System.out.print(sex.getSexo());
      sex = sexo.obtenSexo(2);
      System.out.print(sex.getSexo());
      
     HibernateUtil.closeSessionFactory();*/
     
     //Creacion vista emitir licencia
     EmitirLicenciaModelo  emitirLicenciaModelo = new EmitirLicenciaModelo();
     EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
     EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(emitirLicenciaModelo, emitirLicenciaVista);
     
     emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
     emitirLicenciaControlador.iniciar();
     emitirLicenciaVista.setVisible(true);
    }
    
}
