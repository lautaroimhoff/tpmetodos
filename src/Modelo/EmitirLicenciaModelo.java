/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entity.Categorialicencia;
import Entity.Claselicencia;
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
    private Categorialicencia categoriaLicencia;

    public EmitirLicenciaModelo() {
       
    }

    public Categorialicencia getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(Categorialicencia categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
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


