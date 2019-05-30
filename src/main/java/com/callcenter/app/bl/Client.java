/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.bl;

import com.callcenter.app.entities.Call;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gamba
 */
public class Client extends Thread {

    private Call call;
    private Integer callDuration = 0;

    public Client(Call call) {
        this.call = call;        
        this.callTime();
    }
    
    public void run() {
        try {
            this.sleep(callDuration*1000);
            this.call.getEmployee().setBusy(false);
            System.out.println("Call Nr: "+call.getId()+" Emp: "+call.getEmployee().getCode()+" "+call.getEmployee().getName()+" Call Tm: "+callDuration+" "+call.getEmployee().getnCalls()+" - Ended");
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void callTime() {
        Random rand = new Random();
        this.callDuration = rand.nextInt((10 - 5) + 1) + 5;        
    }

}
