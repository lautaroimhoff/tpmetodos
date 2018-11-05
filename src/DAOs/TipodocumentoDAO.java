/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Tipodocumento;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class TipodocumentoDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaTipodocumento(Tipodocumento tipodocumento) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(tipodocumento);
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

    public void actualizaTipodocumento(Tipodocumento tipodocumento) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(tipodocumento); 
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

    public void eliminaTipodocumento(Tipodocumento tipodocumento) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(tipodocumento); 
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

    public Tipodocumento obtenTipodocumento(int idTipodocumento) throws HibernateException 
    { 
        Tipodocumento tipodocumento = null;  
        try 
        { 
            iniciaOperacion(); 
            tipodocumento = (Tipodocumento) sesion.get(Tipodocumento.class, idTipodocumento); 
        } finally 
        { 
            sesion.close(); 
        }  

        return tipodocumento; 
    }  
     public Tipodocumento obtenTipodocumento(String nombreTipodocumento) throws HibernateException 
    { 
        Tipodocumento tipodocumento = null;  
        try 
        { 
            iniciaOperacion(); 
            tipodocumento = (Tipodocumento) sesion.createQuery("from Tipodocumento where tipodocumento='"+nombreTipodocumento.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return tipodocumento; 
    }  

    public List<Tipodocumento> obtenListaTipodocumentos() throws HibernateException 
    { 
        List<Tipodocumento> listaTipodocumentos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaTipodocumentos = sesion.createQuery("from Tipodocumento").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTipodocumentos; 
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
