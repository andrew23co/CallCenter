/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.callcenter.app.bl;

import com.callcenter.app.entities.Call;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Se han entregado detalles de la ejecución del programa en cada una de las clases
 * y metodos, adicionalmente hay mas información de su implementación en el 
 * archivo Readme dentro del repositorio publico.
 * @author Gamba
 */
public class DispacherTest {

    public DispacherTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Prueba que se invoque solo una instacia del objeto despachador.
     */
    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Dispacher expResult = Dispacher.getInstance();
        Dispacher result = Dispacher.getInstance();
        assertEquals(expResult, result);

    }

    /**
     * Prueba del metodo dispatchCall, de la clase Dispacher.
     * Este metodo ejecuta Diez llamadas
     */
    @org.junit.jupiter.api.Test
    public void testDispatchCallTenCalls() {
        System.out.println("dispatchCall Diez llamadas");
        int numberCalls = 10; // Numero dellamadas a enviar
        Dispacher instance = Dispacher.getInstance();
        for (int i = 0; i < numberCalls; i++) {
            Call c1 = new Call();
            instance.dispatchCall(c1);
        }
    }

    /**
     * Prueba del metodo dispatchCall, de la clase Dispacher.
     * Este metodo se ejecuta en un intervalo de tiempo
     */
    //@org.junit.jupiter.api.Test
    public void testDispatchCall() {
        System.out.println("dispatchCall N seconds");
        Dispacher instance = Dispacher.getInstance(10, 2, 1);
        Timer timer;
        timer = new Timer();
        int timeInterval = 1;// en segundos

        TimerTask task = new TimerTask() {
            int nCall = 1;
            int nCallMax = 10;// Cantidad de llamadas que se desean realizar

            @Override
            public void run() {
                if (nCall <= nCallMax) {
                    Call c1 = new Call();
                    instance.dispatchCall(c1);
                    nCall = nCall + 1;
                } else {
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 10, timeInterval * 1000);
    }

}
