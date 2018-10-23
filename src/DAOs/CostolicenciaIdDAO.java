/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.CostolicenciaId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class CostolicenciaIdDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaCostolicenciaId(CostolicenciaId costolicenciaId) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(costolicenciaId);
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

    public void actualizaCostolicenciaId(CostolicenciaId costolicenciaId) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(costolicenciaId); 
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

    public void eliminaCostolicenciaId(CostolicenciaId costolicenciaId) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(costolicenciaId); 
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

    public CostolicenciaId obtenCostolicenciaId(int idCostolicenciaId) throws HibernateException 
    { 
        CostolicenciaId costolicenciaId = null;  
        try 
        { 
            iniciaOperacion(); 
            costolicenciaId = (CostolicenciaId) sesion.get(CostolicenciaId.class, idCostolicenciaId); 
        } finally 
        { 
            sesion.close(); 
        }  

        return costolicenciaId; 
    }  
     public CostolicenciaId obtenCostolicenciaId(String nombreCostolicenciaId) throws HibernateException 
    { 
        CostolicenciaId costolicenciaId = null;  
        try 
        { 
            iniciaOperacion(); 
            costolicenciaId = (CostolicenciaId) sesion.createQuery("from CostolicenciaId where nombreCostolicenciaId='"+nombreCostolicenciaId.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return costolicenciaId; 
    }  

    public List<CostolicenciaId> obtenListaCostolicenciaIds() throws HibernateException 
    { 
        List<CostolicenciaId> listaCostolicenciaIds = null;  

        try 
        { 
            iniciaOperacion(); 
            listaCostolicenciaIds = sesion.createQuery("from CostolicenciaId").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaCostolicenciaIds; 
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
