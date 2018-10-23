/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Gruposanguineo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class GruposanguineoDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaGruposanguineo(Gruposanguineo gruposanguineo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(gruposanguineo);
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

    public void actualizaGruposanguineo(Gruposanguineo gruposanguineo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(gruposanguineo); 
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

    public void eliminaGruposanguineo(Gruposanguineo gruposanguineo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(gruposanguineo); 
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

    public Gruposanguineo obtenGruposanguineo(int idGruposanguineo) throws HibernateException 
    { 
        Gruposanguineo gruposanguineo = null;  
        try 
        { 
            iniciaOperacion(); 
            gruposanguineo = (Gruposanguineo) sesion.get(Gruposanguineo.class, idGruposanguineo); 
        } finally 
        { 
            sesion.close(); 
        }  

        return gruposanguineo; 
    }  
     public Gruposanguineo obtenGruposanguineo(String nombreGruposanguineo) throws HibernateException 
    { 
        Gruposanguineo gruposanguineo = null;  
        try 
        { 
            iniciaOperacion(); 
            gruposanguineo = (Gruposanguineo) sesion.createQuery("from Gruposanguineo where nombreGruposanguineo='"+nombreGruposanguineo.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return gruposanguineo; 
    }  

    public List<Gruposanguineo> obtenListaGruposanguineos() throws HibernateException 
    { 
        List<Gruposanguineo> listaGruposanguineos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaGruposanguineos = sesion.createQuery("from Gruposanguineo").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaGruposanguineos; 
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
