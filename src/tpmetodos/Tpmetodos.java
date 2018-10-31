/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

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
      SexoDAO sexo = new SexoDAO();
      Sexo sex = new Sexo();
      sex = sexo.obtenSexo(1);
      System.out.print(sex.getSexo());
      sex = sexo.obtenSexo(2);
      System.out.print(sex.getSexo());
      
   //  HibernateUtil.closeSessionFactory();
     
     //Creacion vista emitir licencia
     Licencia  licenciaModelo = new Licencia();
     EmitirLicenciaVista emitirLicenciaVista = new EmitirLicenciaVista();
     EmitirLicenciaControlador emitirLicenciaControlador = new EmitirLicenciaControlador(licenciaModelo, emitirLicenciaVista);
     emitirLicenciaVista.concetaControlador(emitirLicenciaControlador);
     emitirLicenciaControlador.iniciar();
     emitirLicenciaVista.setVisible(true);
     
     
     //Como guardar costos licencia 
     Costolicencia costo = new Costolicencia(); 
     CostolicenciaId id = new CostolicenciaId(); 
     id.setClaselicencia((short)1); //supongo qe 1 es la "A" 
     id.setVigencia(5); // vigencia 5 años
     costo.setId(id); // aca seteo la clave compuesta. 
     costo.setPrecio(40); //para 5 años cuesta $40
     CostolicenciaDAO dao = new CostolicenciaDAO();
     dao.guardaCostolicencia(costo);
     System.out.println("Licencia guardada");
     //Para obtener una licencia. 
     CostolicenciaId id2 = new CostolicenciaId((short)1,5); // clase "A" = 1 , 5 Años de vigencia. 
     Costolicencia ccosto = dao.obtenCostolicencia(id);
     System.out.println("Costo licencia: " +  ccosto.getPrecio());
    }
    
}
