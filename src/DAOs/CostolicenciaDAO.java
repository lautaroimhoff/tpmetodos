/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Costolicencia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class CostolicenciaDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaCostolicencia(Costolicencia costolicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(costolicencia);
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

    public void actualizaCostolicencia(Costolicencia costolicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(costolicencia); 
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

    public void eliminaCostolicencia(Costolicencia costolicencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(costolicencia); 
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

    public Costolicencia obtenCostolicencia(int idCostolicencia) throws HibernateException 
    { 
        Costolicencia costolicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            costolicencia = (Costolicencia) sesion.get(Costolicencia.class, idCostolicencia); 
        } finally 
        { 
            sesion.close(); 
        }  

        return costolicencia; 
    }  
     public Costolicencia obtenCostolicencia(String nombreCostolicencia) throws HibernateException 
    { 
        Costolicencia costolicencia = null;  
        try 
        { 
            iniciaOperacion(); 
            costolicencia = (Costolicencia) sesion.createQuery("from Costolicencia where nombreCostolicencia='"+nombreCostolicencia.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return costolicencia; 
    }  

    public List<Costolicencia> obtenListaCostolicencias() throws HibernateException 
    { 
        List<Costolicencia> listaCostolicencias = null;  

        try 
        { 
            iniciaOperacion(); 
            listaCostolicencias = sesion.createQuery("from Costolicencia").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaCostolicencias; 
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
