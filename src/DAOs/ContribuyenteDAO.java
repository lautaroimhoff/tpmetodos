/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Contribuyente;
import Entity.Tipodocumento;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class ContribuyenteDAO {
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaContribuyente(Contribuyente contribuyente) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(contribuyente);
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

    public void actualizaContribuyente(Contribuyente contribuyente) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(contribuyente); 
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

    public void eliminaContribuyente(Contribuyente contribuyente) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(contribuyente); 
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

    public Contribuyente obtenContribuyente(int idContribuyente) throws HibernateException 
    { 
        Contribuyente contribuyente = null;  
        try 
        { 
            iniciaOperacion(); 
            contribuyente = (Contribuyente) sesion.get(Contribuyente.class, idContribuyente); 
        } finally 
        { 
            sesion.close(); 
        }  

        return contribuyente; 
    }  
     public Contribuyente obtenContribuyente(String documento) throws HibernateException 
    { 
        Contribuyente contribuyente = null;  
        try 
        { 
            iniciaOperacion(); 
            contribuyente = (Contribuyente) sesion.createQuery("from Contribuyente where documento='"+documento.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return contribuyente; 
    } 
     
       public Contribuyente obtenContribuyente(Tipodocumento tipoDocumento, String documento) throws HibernateException 
    { 
        Contribuyente contribuyente = null;  
        try 
        { 
            iniciaOperacion(); 
            contribuyente = (Contribuyente) sesion.createQuery("from Contribuyente where tipo_documento='"+tipoDocumento.getTipodocumento().trim()+
                    "' and documento='"+documento.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return contribuyente; 
    }

    public List<Contribuyente> obtenListaContribuyentes() throws HibernateException 
    { 
        List<Contribuyente> listaContribuyentes = null;  

        try 
        { 
            iniciaOperacion(); 
            listaContribuyentes = sesion.createQuery("from Contribuyente").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaContribuyentes; 
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
