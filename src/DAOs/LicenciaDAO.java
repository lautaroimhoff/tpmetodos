/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Licencia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class LicenciaDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaLicencia(Licencia licencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(licencia);
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

    public void actualizaLicencia(Licencia licencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(licencia); 
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

    public void eliminaLicencia(Licencia licencia) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(licencia); 
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

    public Licencia obtenLicencia(int idLicencia) throws HibernateException 
    { 
        Licencia licencia = null;  
        try 
        { 
            iniciaOperacion(); 
            licencia = (Licencia) sesion.get(Licencia.class, idLicencia); 
        } finally 
        { 
            sesion.close(); 
        }  

        return licencia; 
    }  
    public Licencia obtenLicencia(String nombreLicencia) throws HibernateException 
    { 
        Licencia licencia = null;  
        try 
        { 
            iniciaOperacion(); 
            licencia = (Licencia) sesion.createQuery("from Licencia where nombreLicencia='"+nombreLicencia.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return licencia; 
    }  
    public Licencia obtenLicencia(int idTitular, int idClaseLicencia) throws HibernateException 
    { 
        Licencia licencia = null;  
        try 
        { 
            iniciaOperacion(); 
            licencia = (Licencia) sesion.createQuery("from Licencia where idtitularreceptor='"+idTitular+"' and idclaselicencia='"+idClaseLicencia+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return licencia; 
    }

    public List<Licencia> obtenListaLicencias() throws HibernateException 
    { 
        List<Licencia> listaLicencias = null;  

        try 
        { 
            iniciaOperacion(); 
            listaLicencias = sesion.createQuery("from Licencia").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaLicencias; 
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
