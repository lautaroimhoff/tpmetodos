/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lautaro
 */
public class UsuarioDAO
{  
    private Session sesion; 
    private Transaction tx;  
    
   

    public void guardaUsuario(Usuario usuario) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.persist(usuario);
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

    public void actualizaUsuario(Usuario usuario) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(usuario); 
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

    public void eliminaUsuario(Usuario usuario) throws HibernateException 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(usuario); 
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

    public Usuario obtenUsuario(int idUsuario) throws HibernateException 
    { 
        Usuario usuario = null;  
        try 
        { 
            iniciaOperacion(); 
            usuario = (Usuario) sesion.get(Usuario.class, idUsuario); 
        } finally 
        { 
            sesion.close(); 
        }  

        return usuario; 
    }  
     public Usuario obtenUsuario(String nombreUsuario) throws HibernateException 
    { 
        Usuario usuario = null;  
        try 
        { 
            iniciaOperacion(); 
            usuario = (Usuario) sesion.createQuery("from Usuario where nombreusuario='"+nombreUsuario.trim()+"'").uniqueResult();
        } finally 
        { 
            sesion.close(); 
        }  

        return usuario; 
    }  

    public List<Usuario> obtenListaUsuarios() throws HibernateException 
    { 
        List<Usuario> listaUsuarios = null;  

        try 
        { 
            iniciaOperacion(); 
            listaUsuarios = sesion.createQuery("from Usuario").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaUsuarios; 
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
