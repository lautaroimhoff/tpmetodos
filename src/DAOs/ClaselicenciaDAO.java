/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Claselicencia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class ClaselicenciaDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaClaselicencia(Claselicencia claselicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(claselicencia);
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

    public void actualizaClaselicencia(Claselicencia claselicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(claselicencia); 
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

    public void eliminaClaselicencia(Claselicencia claselicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(claselicencia); 
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

    public Claselicencia obtenClaselicencia(int idClaselicencia) throws HibernateException 
    { 
        Claselicencia claselicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            claselicencia = (Claselicencia) sesion.get(Claselicencia.class, idClaselicencia); 
        } finally 
        { 
            sesion.close(); 
        }  

        return claselicencia; 
    }  
     public Claselicencia obtenClaselicencia(String nombreClaselicencia) throws HibernateException 
    { 
        Claselicencia claselicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            claselicencia = (Claselicencia) sesion.createQuery("from Claselicencia where claselicencia='"+nombreClaselicencia.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return claselicencia; 
    }  

    public List<Claselicencia> obtenListaClaselicencias() throws HibernateException 
    { 
        List<Claselicencia> listaClaselicencias = null;  

        try 
        { 
            iniciaOperacion(); 
            listaClaselicencias = sesion.createQuery("from Claselicencia").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClaselicencias; 
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
