package com.callcenter.app.entities;

import com.callcenter.app.misc.LastNames;
import com.callcenter.app.misc.Names;
import java.util.Random;

/**
 *
 * @author Gamba
 */
public class Employee {

    protected String code;
    protected String name;
    private Integer nCalls = 0;
    private boolean busy = false;

    public Employee(String c) {
        Random rand = new Random();
        this.code = c; // UUID.randomUUID().toString();
        int numN = rand.nextInt((49 - 0) + 1) + 0;
        int numL = rand.nextInt((49 - 0) + 1) + 0;
        this.name = Names.values()[numN].toString()+" "+LastNames.values()[numL].toString();
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }
    
     /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the busy
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * @param busy the busy to set
     */
    public void setBusy(boolean busy) {                 
        this.busy = busy;
    }

    /**
     * @return the nCalls
     */
    public Integer getnCalls() {
        return nCalls;
    }
    
    /**
     * @return the nCalls
     */
    public void callAssined() {
        this.nCalls = this.nCalls + 1;
    }

}
