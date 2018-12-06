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
         //Pruebas verdadera
        assertTrue(Calculos.CalcularCosto.CalcularCosto("A", 5)==48);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("B", 4)==38);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("C", 3)==38);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("D", 1)==31);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("E", 3)==47);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("F", 4)==52);
        assertTrue(Calculos.CalcularCosto.CalcularCosto("G", 5)==48);
        
        
        //Pruebas falsas
        assertTrue(Calculos.CalcularCosto.CalcularCosto("A", 1)!=20); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("B", 3)!=25); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("C", 4)!=35); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("D", 5)!=47); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("E", 4)!=44); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("F", 3)!=39); 
        assertTrue(Calculos.CalcularCosto.CalcularCosto("G", 1)!=20); 

    }
    
}
