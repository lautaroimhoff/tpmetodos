/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Nacionalidad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Elias Capasso
 */
public class NacionalidadDAO {
    private Session sesion; 
    private Transaction tx;  
    
    public List<Nacionalidad> obtenListaNacionalidades() throws HibernateException 
    { 
        List<Nacionalidad> listaNacionalidades = new ArrayList();  

        try 
        { 
            iniciaOperacion(); 
            listaNacionalidades = sesion.createQuery("from Nacionalidad").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaNacionalidades; 
    }  

    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  

    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 
}
