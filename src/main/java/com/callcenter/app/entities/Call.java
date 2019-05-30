/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.entities;

import java.util.UUID;

/**
 *
 * @author Gamba
 */
public class Call {

    private String id = "call-"+UUID.randomUUID().toString();
    private Employee employee;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
