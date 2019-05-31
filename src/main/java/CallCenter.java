
import com.callcenter.app.bl.Dispacher;
import com.callcenter.app.entities.Call;
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

        Dispacher dp = Dispacher.getInstance(10, 2, 1);

        Timer timer;
        timer = new Timer();

        int timeInterval = 1;

        TimerTask task = new TimerTask() {
            int nCall = 1;
            int nCallMax = 10;

            @Override
            public void run() {
                if (nCall <= nCallMax) {
                    Call c1 = new Call();
                    dp.dispatchCall(c1);
                    nCall = nCall + 1;
                } else {
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 10, timeInterval * 1000);

        dp.statistics();

//        for (int i = 0; i < 20; i++) {
//            Call c1 = new Call();
//            dp.dispatchCall(c1);
//        }
    }
}
