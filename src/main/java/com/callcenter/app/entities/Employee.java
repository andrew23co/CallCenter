package com.callcenter.app.entities;

import com.callcenter.app.misc.LastNames;
import com.callcenter.app.misc.Names;
import java.util.Random;

/**
 * Clase empleado que generaliza los diferentes actores del Call Center
 *
 * @author Gamba
 */
public class Employee {

    protected String code;
    protected String name;
    private Integer nCalls = 0;
    private boolean busy = false;

    /**
     * Constructor de la clase, Esta sección establece un nombre y apellido aleatorio para el empleado
     * que se configura al inicio del proceso de call center     
     * @param code Recibe el codigo asignado al empleado por el call center
     */
    public Employee(String code) {
        Random rand = new Random();
        this.code = code; // UUID.randomUUID().toString();
        int numN = rand.nextInt((49 - 0) + 1) + 0;
        int numL = rand.nextInt((49 - 0) + 1) + 0;
        this.name = Names.values()[numN].toString() + " " + LastNames.values()[numL].toString();
    }

    /**
     * @return El codigo del empleado
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return El nombre del empleado creado
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return true Si el empleado esta ocupado en una llamada para asignar o no una
     * llamada entrante
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * @param busy parametro que indica el estado actual del empleado
     */
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    /**
     * @return el número de llamadas total atendidas por el empleado hasta su invocación
     */
    public Integer getnCalls() {
        return nCalls;
    }

    /**
     * Método que indica que ha sido asignada una llamada al empleado
     */
    public void callAssined() {
        this.nCalls = this.nCalls + 1;
    }

}
