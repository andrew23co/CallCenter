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
 * Clase que simula un cliente, esta clase es la encargada de iniciar los hilos
 * de las llamdas que deben ser atendidas por los empleados
 *
 * @author Gamba
 */
public class Client extends Thread {

    private Call call;
    private Integer callDuration = 0;

    /**
     * Constructor de la clase
     *
     * @param call LLamada entrante y asignada
     */
    public Client(Call call) {
        this.call = call;
        this.callTime();
    }

    /**
     * Metodo que ejecuta el hilo de la llamada
     */
    public void run() {
        try {
            this.sleep(callDuration * 1000);// Tiempo de duración de la llamada
            this.call.getEmployee().setBusy(false); // Se libera el operador
            // Se imprime log
            System.out.println("Llamada: " + call.getId() + " Empleado: " + call.getEmployee().getCode() + " " + call.getEmployee().getName() + " Tiempo: " + callDuration + " " + call.getEmployee().getnCalls() + " - Finalizada");
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que establece el tiempo de la duración de la llamada (entre 5 y 10
     * segundos)
     */
    public void callTime() {
        Random rand = new Random();
        this.callDuration = rand.nextInt((10 - 5) + 1) + 5;
    }

}
