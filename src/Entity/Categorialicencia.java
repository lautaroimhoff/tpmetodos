package Entity;
// Generated 21/10/2018 17:24:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categorialicencia generated by hbm2java
 */
public class Categorialicencia  implements java.io.Serializable {


     private int idcategorialicencia;
     private String categorialicencia;
     private Set licencias = new HashSet(0);

    public Categorialicencia() {
    }

	
    public Categorialicencia(int idcategorialicencia, String categorialicencia) {
        this.idcategorialicencia = idcategorialicencia;
        this.categorialicencia = categorialicencia;
    }
    public Categorialicencia(int idcategorialicencia, String categorialicencia, Set licencias) {
       this.idcategorialicencia = idcategorialicencia;
       this.categorialicencia = categorialicencia;
       this.licencias = licencias;
    }
   
    public int getIdcategorialicencia() {
        return this.idcategorialicencia;
    }
    
    public void setIdcategorialicencia(int idcategorialicencia) {
        this.idcategorialicencia = idcategorialicencia;
    }
    public String getCategorialicencia() {
        return this.categorialicencia;
    }
    
    public void setCategorialicencia(String categorialicencia) {
        this.categorialicencia = categorialicencia;
    }
    public Set getLicencias() {
        return this.licencias;
    }
    
    public void setLicencias(Set licencias) {
        this.licencias = licencias;
    }




}

