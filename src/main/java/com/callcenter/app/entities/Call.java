/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.entities;

import java.util.UUID;

/**
 * Clase que actua como llamada entrante y que debe ser atendida por un empleado
 * @author Gamba
 */
public class Call {
    
    
    // Identificador unico para cada llamada
    private String id = "call-" + UUID.randomUUID().toString();    
    private Employee employee;

    /**
     * @return El id de la llamada
     */
    public String getId() {
        return id;
    }

    /**
     * @return El objeto del empleado que esta atendiendo la llamada
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee establece el objeto empleado que atenderá la llamada
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
