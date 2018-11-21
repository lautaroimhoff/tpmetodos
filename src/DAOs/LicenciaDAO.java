/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entity.Licencia;
import java.util.ArrayList;
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
    
    public ArrayList<Licencia> buscarPorCriterios(String nombre, String apellido, Integer dni, Integer numerolicencia, String grupo, String factor, String clase, Boolean donante ) throws HibernateException {
        List<Object> licencias = new ArrayList<>();
        try {
            iniciaOperacion();
            String query = new String();
            
            if (!nombre.isEmpty()) {
                query += "l.titular.nombre = '" + nombre + "' AND ";
            }
            if (dni != null) {
                query += "l.titular.dni = " + dni.toString() + " AND ";
            }
            if (!apellido.isEmpty()) {
                query += "l.titular.apellido = '" + apellido + "' AND ";
            }
            if(numerolicencia!=null)
            {
                query += "l.numerolicencia = " + numerolicencia.toString() + " AND ";
            }
            if (clase != "-") {
                query += "l.clase = '" + clase + "' AND ";
            }
            if (grupo != "-") {
                query += "l.titular.grupoSanguineo = '" + grupo + "' AND ";
            }
            if (factor != "-") {
                query += "l.titular.factorRh = '" + factor + "' AND ";
            }
            if (donante != null) {
                query += "l.titular.esDonante = " + ((donante) ? "1" : "0") + " AND ";
            }
            
            if(!query.isEmpty()){
                query = query.substring(0, query.length()-4);
            }
            query = "FROM Licencia l" + ((query.isEmpty())?"":" WHERE ") + query;

            //query += "l.titular.dni = t.dni";
            
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
