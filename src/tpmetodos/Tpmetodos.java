/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodos;

import DAOs.SexoDAO;
import DAOs.UsuarioDAO;
import Entity.Sexo;
import Entity.Usuario;
import DAOs.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author lautaro
 */
public class Tpmetodos {
    
    private Session sesion = HibernateUtil.getSessionFactory().openSession();
   
    public static void main(String[] args) {
        // TODO code application logic here
      
      SexoDAO sexo = new SexoDAO();
      Sexo sex = new Sexo();
      sex = sexo.obtenSexo(1);
      System.out.print(sex.getSexo());
      sex = sexo.obtenSexo(2);
      System.out.print(sex.getSexo());
      
     HibernateUtil.closeSessionFactory();
    }
    
}
