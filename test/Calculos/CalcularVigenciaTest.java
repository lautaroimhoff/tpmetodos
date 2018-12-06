/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculos;

import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tinch
 */
public class CalcularVigenciaTest {
    
    public CalcularVigenciaTest() {
    }
    
    @Test
    public void testCalcularVigencia() {
        System.out.println("CalcularVigencia");
        //Pruebas verdadera
        
        Date fechaNacimiento = new Date(1995,25,10);
        String categoriaLicencia = "Renovacion";
        
        Calendar expResult = Calendar.getInstance();
        expResult.add(Calendar.YEAR, 5);
        expResult.set(Calendar.DAY_OF_MONTH,fechaNacimiento.getDate());
        expResult.set(Calendar.MONTH,fechaNacimiento.getMonth());
        
        Calendar result = CalcularVigencia.CalcularVigencia(fechaNacimiento, categoriaLicencia);
        int año1 = expResult.get(Calendar.YEAR);
        int año2 =result.get(Calendar.YEAR);
        assertEquals(expResult.get(Calendar.YEAR), result.get(Calendar.YEAR));
        
        
        System.out.println("CalcularVigencia");
        Date fechaNacimiento1 = new Date(2000,15,9);
        String categoriaLicencia1 = "Primera vez";
        
        Calendar expResult1 = Calendar.getInstance();
        expResult1.add(Calendar.YEAR, 1);
        expResult1.set(Calendar.DAY_OF_MONTH,fechaNacimiento1.getDate());
        expResult1.set(Calendar.MONTH,fechaNacimiento1.getMonth());
        
        Calendar result1 = CalcularVigencia.CalcularVigencia(fechaNacimiento1, categoriaLicencia1);
        assertEquals(expResult1.get(Calendar.YEAR), result1.get(Calendar.YEAR));
        
        
        System.out.println("CalcularVigencia");
        Date fechaNacimiento2 = new Date(1940,10,9);
        String categoriaLicencia2 = "Renovacion";
        
        Calendar expResult2 = Calendar.getInstance();
        expResult2.add(Calendar.YEAR, 1);
        expResult2.set(Calendar.DAY_OF_MONTH,fechaNacimiento2.getDate());
        expResult2.set(Calendar.MONTH,fechaNacimiento2.getMonth());
        
        Calendar result2 = CalcularVigencia.CalcularVigencia(fechaNacimiento2, categoriaLicencia2);
        
        assertEquals(expResult2.get(Calendar.YEAR), result2.get(Calendar.YEAR));
        
        //Pruebas falsa
         System.out.println("CalcularVigencia");
        Date fechaNacimiento3 = new Date(1950,10,9);
        String categoriaLicencia3 = "Renovacion";
        
        Calendar expResult3 = Calendar.getInstance();
        expResult3.add(Calendar.YEAR, 10);
        expResult3.set(Calendar.DAY_OF_MONTH,fechaNacimiento3.getDate());
        expResult3.set(Calendar.MONTH,fechaNacimiento3.getMonth());
        
        Calendar result3 = CalcularVigencia.CalcularVigencia(fechaNacimiento3, categoriaLicencia3);
        
        assertTrue(expResult3.get(Calendar.YEAR)!= result3.get(Calendar.YEAR));
        
        
    }
    
}
