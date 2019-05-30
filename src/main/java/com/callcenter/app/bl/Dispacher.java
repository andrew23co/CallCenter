/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.bl;

import com.callcenter.app.entities.Call;
import com.callcenter.app.entities.Director;
import com.callcenter.app.entities.Employee;
import com.callcenter.app.entities.Operator;
import com.callcenter.app.entities.Supervisor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Gamba
 */
public class Dispacher {

    private Operator[] nOperators = new Operator[22];
    private Supervisor[] nSupervisors = new Supervisor[2];
    private Director[] nDirectors = new Director[1];
    private Queue<Call> waitingCalls = new LinkedList<>(); 
    private static Dispacher dispacher = new Dispacher();

    private Dispacher() {
        hirePeople();
    }

    public static Dispacher getInstance() {
        return dispacher;
    }

    public void dispatchCall(Call call) {
        Call inCall;
        
        waitingCalls.add(call);
        Employee e = assingAgent();
        if (e == null) {
            System.out.println("No hay agentes disponibles..."+call.getId());            
        } else {            
            inCall = waitingCalls.peek();
            System.out.println("Asignada... "+inCall.getId());
            inCall.setEmployee(e);
            Client c = new Client(inCall);
            waitingCalls.remove();
            c.start();
        }
        
        System.out.println("Size: "+waitingCalls.size());

    }

    private Employee assingAgent() {
        for (Employee eO : nOperators) {
            if (!eO.isBusy()) {
                eO.callAssined();
                eO.setBusy(true);
                return eO;
            }
        }
        for (Employee eS : nSupervisors) {
            if (!eS.isBusy()) {
                eS.callAssined();
                eS.setBusy(true);
                return eS;
            }
        }

        for (Employee eD : nDirectors) {
            if (!eD.isBusy()) {
                eD.callAssined();
                eD.setBusy(true);
                return eD;
            }
        }
        return null;
    }

    private void hirePeople() {
        for (int i = 0; i < nOperators.length; i++) {
            nOperators[i] = new Operator(i+"");
            System.out.println("Hire: " + nOperators[i].getName()+" Code: "+nOperators[i].getCode());
        }
        for (int i = 0; i < nSupervisors.length; i++) {
            nSupervisors[i] = new Supervisor(i+"");
            System.out.println("Hire: " + nSupervisors[i].getName()+" Code: "+nSupervisors[i].getCode());
        }
        for (int i = 0; i < nDirectors.length; i++) {
            nDirectors[i] = new Director(i+"");
            System.out.println("Hire: " + nDirectors[i].getName()+" Code: "+nDirectors[i].getCode());
        }
    }

}
