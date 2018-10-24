/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entity.Categorialicencia;
import Entity.Claselicencia;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jaque
 */
public class EmitirLicenciaModelo {
    
    private String observacion;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private Claselicencia claseLicencia;
    private ArrayList<Categorialicencia> listaCategoria;

    public EmitirLicenciaModelo() {
        this.listaCategoria=new ArrayList<Categorialicencia>();
       
    }
    
    public void emitir(){
        //implementar
    }
    
    public void cancelar(){
        
    }
    
    

    public ArrayList<Categorialicencia> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(ArrayList<Categorialicencia> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Claselicencia getClaseLicencia() {
        return claseLicencia;
    }

    public void setClaseLicencia(Claselicencia claseLicencia) {
        this.claseLicencia = claseLicencia;
    }
    
 
    
    
    
    
}


