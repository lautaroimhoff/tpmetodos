

/**
 *
 * @author Lautaro
 */
package DAOs;

import Entity.Sexo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class SexoDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaSexo(Sexo sexo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(sexo);
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

    public void actualizaSexo(Sexo sexo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(sexo); 
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

    public void eliminaSexo(Sexo sexo) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(sexo); 
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

    public Sexo obtenSexo(int idSexo) throws HibernateException 
    { 
        Sexo sexo = null;  
        try 
        { 
            iniciaOperacion(); 
            sexo = (Sexo) sesion.get(Sexo.class, idSexo); 
        } finally 
        { 
            sesion.close(); 
        }  

        return sexo; 
    }  
     public Sexo obtenSexo(String nombreSexo) throws HibernateException 
    { 
        Sexo sexo = null;  
        try 
        { 
            iniciaOperacion(); 
            sexo = (Sexo) sesion.createQuery("from Sexo where nombreSexo='"+nombreSexo.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return sexo; 
    }  

    public List<Sexo> obtenListaSexos() throws HibernateException 
    { 
        List<Sexo> listaSexos = null;  

        try 
        { 
            iniciaOperacion(); 
            listaSexos = sesion.createQuery("from Sexo").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaSexos; 
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
