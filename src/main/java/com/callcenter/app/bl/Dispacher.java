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
 * Clase de tipo Singleton para la administración de las llamadas del 
 * Call Center
 * @author Gamba
 */
public class Dispacher {

    private Operator[] nOperators = null;
    private Supervisor[] nSupervisors = null;
    private Director[] nDirectors = null;
    private Queue<Call> waitingCalls = new LinkedList<>();
    private static Dispacher dispacher = null;
    private int op;
    private int sp;
    private int dr;
    
    
    /**
     * Contructir unico y privado que crea la instacia de la clase, es privado 
     * para que el mismo objeto controle su creación.
     * @param op Número de operadores
     * @param sp Número supervisores
     * @param dr Número de directores
     */
    private Dispacher(int op, int sp, int dr) {
        nOperators = new Operator[op];
        nSupervisors = new Supervisor[sp];
        nDirectors = new Director[dr];
        hirePeople();
    }

    /**
     * Se utiliza este inicializador para configurar de manera personalizada la
     * ejecución del simulador
     *
     * @param op Cantidad de operarios inicial
     * @param sp Cantidad de supervisores inicial
     * @param dr Cantidad de directores inicial
     */
    public static Dispacher getInstance(int op, int sp, int dr) {
        if (dispacher == null) {
            dispacher = new Dispacher(op, sp, dr);
        }
        return dispacher;
    }

    /**
     * Se utiliza este inicializador por defecto con 22 Operarios 2 Supervisores
     * y 1 director
     */
    public static Dispacher getInstance() {
        if (dispacher == null) {
            dispacher = new Dispacher(10, 2, 1);
        }
        return dispacher;
    }
    
    /**
     * Metodo principal de la clase despachadora:
     * Metodo que se encarga de la administracion y gestión de las llamadas
     * Entrantes, todas las llamadas entran a una lista de espera y son antendidas
     * en orde de llegada pro los empleados
     * @param call Llamada entrante
     */
    public void dispatchCall(Call call) {
        Call inCall;     
        
        // Se agrega la llamada a la lista de llamadas en espera
        waitingCalls.add(call);
        Employee e = assingAgent();
        if (e == null) {
            System.out.println("No hay agentes disponibles..." + call.getId());
        } else {
            // Primera llamada de la lista
            inCall = waitingCalls.peek();
            System.out.println("Asignada... " + inCall.getId());
            inCall.setEmployee(e);
            Client c = new Client(inCall);
            // Se remueve la llamada de la lista 
            waitingCalls.remove();
            // Se inicia la llamada (da inicio al hilo de ejecución)
            c.start();
        }
        // Numero de llamdas aun sin atender
        System.out.println("Size: " + waitingCalls.size());

    }
    
    /**
     * Metodo que evalua la disponibilidad del los empleados, como prioridad 
     * selecciona a los operadores, si los operadores estan ocupados selecciona
     * a los supervisores y si estos estan ocupados selecciona al director
     * @return El empleado disponible, cambia su estado a ocupado e incrementa 
     * intermnamente el contador de llamadas de empleado
     */
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
    
    
    /**
     * Metodo que realiza la "contratación" de los empleados creando instancias
     * de empleados que atenderan las llamadas
     */
    private void hirePeople() {
        for (int i = 0; i < nOperators.length; i++) {
            nOperators[i] = new Operator(i + "");
            System.out.println("Hire: " + nOperators[i].getName() + " Code: " + nOperators[i].getCode());
        }
        for (int i = 0; i < nSupervisors.length; i++) {
            nSupervisors[i] = new Supervisor(i + "");
            System.out.println("Hire: " + nSupervisors[i].getName() + " Code: " + nSupervisors[i].getCode());
        }
        for (int i = 0; i < nDirectors.length; i++) {
            nDirectors[i] = new Director(i + "");
            System.out.println("Hire: " + nDirectors[i].getName() + " Code: " + nDirectors[i].getCode());
        }
    }

    
    /**
     * Metodo de estadisticas generales, debe controlarse la invocación de este
     * metodo para que se ejecute al final de la ejecución de las llamadas debido
     * a que cada llamada se ejecuta en hilos independientes.
     */
    public void statistics() {
        int counter = 0;
        System.out.println("**Estadisticas de Call center");
        System.out.println("**Numero de llamadas por Operador:");
        for (Employee e : this.nOperators) {
            counter = counter + e.getnCalls();
            System.out.println(e.getName() + " - " + e.getnCalls() + " llamadas");
        }
        System.out.println("Numero de llamadas por Supervisor:");
        for (Employee e : this.nSupervisors) {
            counter = counter + e.getnCalls();
            System.out.println(e.getName() + " - " + e.getnCalls() + " llamadas");
        }
        System.out.println("Numero de llamadas por Director:");
        for (Employee e : this.nDirectors) {
            counter = counter + e.getnCalls();
            System.out.println(e.getName() + " - " + e.getnCalls() + " llamadas");
        }
        System.out.println("**Numero de llamadas totales: " + counter);
        System.out.println("**Numero de en cola: " + waitingCalls.size());

    }

}
