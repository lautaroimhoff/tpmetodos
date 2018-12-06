/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculos;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lautaro
 */
public class CalcularCostoTest {
    
    public CalcularCostoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of CalcularCosto method, of class CalcularCosto.
     */
    @Test
    public void testCalcularCosto() {
        System.out.println("CalcularCosto");
        int CostoAdministrativo = 8;
        String claseLicencia = "A";
        int vigencia = 1;
        int expResult = 28;
        int result = CalcularCosto.CalcularCosto(claseLicencia, vigencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
        
        //Sabemos que el costo de la licena clase "A" es de $20 y al mismo se le suman $8 de costo administrativo. 
    }
    
}
