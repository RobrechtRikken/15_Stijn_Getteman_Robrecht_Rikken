/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTAsk extends TimerTask {

    HelloTVXlet xlet;
    
    public MijnTimerTAsk(HelloTVXlet x)
    {
    xlet = x;
    }
    
    
    public void run() {
       
        //System.out.println("tick...");
        xlet.callBack();
    }

}
