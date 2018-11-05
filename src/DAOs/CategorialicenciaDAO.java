/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Categorialicencia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class CategorialicenciaDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaCategorialicencia(Categorialicencia categorialicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(categorialicencia);
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

    public void actualizaCategorialicencia(Categorialicencia categorialicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(categorialicencia); 
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

    public void eliminaCategorialicencia(Categorialicencia categorialicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(categorialicencia); 
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

    public Categorialicencia obtenCategorialicencia(int idCategorialicencia) throws HibernateException 
    { 
        Categorialicencia categorialicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            categorialicencia = (Categorialicencia) sesion.get(Categorialicencia.class, idCategorialicencia); 
        } finally 
        { 
            sesion.close(); 
        }  

        return categorialicencia; 
    }  
     public Categorialicencia obtenCategorialicencia(String nombreCategorialicencia) throws HibernateException 
    { 
        Categorialicencia categorialicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            categorialicencia = (Categorialicencia) sesion.createQuery("from Categorialicencia where categorialicencia='"+nombreCategorialicencia.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return categorialicencia; 
    }  

    public List<Categorialicencia> obtenListaCategorialicencias() throws HibernateException 
    { 
        List<Categorialicencia> listaCategorialicencias = null;  

        try 
        { 
            iniciaOperacion(); 
            listaCategorialicencias = sesion.createQuery("from Categorialicencia").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaCategorialicencias; 
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
