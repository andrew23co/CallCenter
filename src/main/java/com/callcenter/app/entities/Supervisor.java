/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.entities;

/**
 *
 * @author Gamba
 */
public class Supervisor extends Employee {

    public Supervisor(String c) {
        super(c);
        this.code = "SP-" + this.code;
    }

}
