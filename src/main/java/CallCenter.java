
import com.callcenter.app.bl.Dispacher;
import com.callcenter.app.entities.Call;
import com.callcenter.app.entities.Director;
import com.callcenter.app.entities.Employee;
import com.callcenter.app.entities.Operator;
import com.callcenter.app.entities.Supervisor;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gamba
 */
public class CallCenter {

    public static void main(String[] args) {
        
        Dispacher dp = Dispacher.getInstance();
        
        
        Timer timer;
        timer = new Timer();
        

        TimerTask task = new TimerTask() {
            int tic = 0;

            @Override
            public void run() {

                Call c1 = new Call();
                dp.dispatchCall(c1);
            }
        };
        timer.schedule(task, 10, 1000);


//        for (int i = 0; i < 20; i++) {
//            Call c1 = new Call();
//            dp.dispatchCall(c1);
//        }

    }
}
