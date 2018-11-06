/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Titular;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class TitularDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaTitular(Titular titular) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(titular);
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
    }  

    public void actualizaTitular(Titular titular) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(titular); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public void eliminaTitular(Titular titular) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(titular); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public Titular obtenTitular(int idTitular) throws HibernateException 
    { 
        Titular titular = null;  
        try 
        { 
            iniciaOperacion(); 
            titular = (Titular) sesion.get(Titular.class, idTitular); 
        } finally 
        { 
            sesion.close(); 
        }  

        return titular; 
    }  
     public Titular obtenTitular(String nombreTitular) throws HibernateException 
    { 
        Titular titular = null;  
        try 
        { 
            iniciaOperacion(); 
            titular = (Titular) sesion.createQuery("from Titular where nombre='"+nombreTitular.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return titular; 
    }   

    public List<Titular> obtenListaTitulares() throws HibernateException 
    { 
        List<Titular> listaTitulars = null;  

        try 
        { 
            iniciaOperacion(); 
            listaTitulars = sesion.createQuery("from Titular").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTitulars; 
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
