/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Licencia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private static Session sesion; 
    private static Transaction tx;  
    
   

    public static void guardaLicencia(Licencia licencia) throws HibernateException 
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

    public static void actualizaLicencia(Licencia licencia) throws HibernateException 
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

    public static void eliminaLicencia(Licencia licencia) throws HibernateException 
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

    public static Licencia obtenLicencia(int idLicencia) throws HibernateException 
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
    public static Licencia obtenLicencia(String nombreLicencia) throws HibernateException 
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
    public static Licencia obtenLicencia(int idTitular, int idClaseLicencia) throws HibernateException 
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

    public static List<Licencia> obtenListaLicencias() throws HibernateException 
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
    
    public static ArrayList<Licencia> buscarPorCriterios(String nombre, String apellido, String numerodocumento, String gruposanguineo, String factor, String claselicencia, Boolean donante ) throws HibernateException {
        List<Object> licencias = new ArrayList<>();
        try {
            iniciaOperacion();
            String query = new String();
            
            if (!nombre.isEmpty()) {
                query += "l.titular.nombre = '" + nombre + "' AND ";
            }
            if (numerodocumento != null) {
                query += "l.titular.numerodocumento = '" + numerodocumento.toString() + "' AND ";
            }
            if (!apellido.isEmpty()) {
                query += "l.titular.apellido = '" + apellido + "' AND ";
            }
            if (!claselicencia.equals("-")) {
                query += "l.claselicencia.claselicencia = '" + claselicencia + "' AND ";
            }
            if (!gruposanguineo.equals("-")) {
                query += "l.titular.gruposanguineo.gruposanguineo = '" + gruposanguineo + "' AND ";
            }
            if (!factor.equals("-")) {
                query += "l.titular.gruposanguineo.factor = '" + factor + "' AND ";
            }
            if (donante != false) {
                query += "l.titular.donante = " + ((donante) ? "1" : "0") + " AND ";
            }
            if(true){
                query += "l.fechavencimiento > NOW()" + " AND ";
            }
            
            if(!query.isEmpty()){
                query = query.substring(0, query.length()-4);
            }
            query = "FROM Licencia l" + ((query.isEmpty())?"":" WHERE ") + query;
            
            licencias = sesion.createQuery(query).list();
        } finally 
        { 
            sesion.close(); 
        } 
        ArrayList<Licencia> result = new ArrayList<>();
        for (Object o : licencias) {
            result.add((Licencia) o);
        }
        
        return result;
        
    }
    public static ArrayList<Licencia> buscarPorFechadeVencimiento(String criterio) throws HibernateException{
        List<Object> licencias = new ArrayList<>();
        try {
            iniciaOperacion();
            String query = new String();
            query = "FROM Licencia l WHERE l.fechavencimiento < to_date('" +criterio+ "', 'DD/MM/YYYY')";
            licencias = sesion.createQuery(query).list();
        }finally 
        { 
            sesion.close(); 
        }
        ArrayList<Licencia> result = new ArrayList<>();
        for (Object o : licencias) {
            result.add((Licencia) o);
        }
        return result;
    }
    

    private static void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  

    private static void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 

    
}
