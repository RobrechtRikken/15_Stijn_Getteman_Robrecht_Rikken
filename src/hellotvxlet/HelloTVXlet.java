package hellotvxlet;

import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
//import org.dvb.event.UserEventRepository;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;


public class HelloTVXlet implements Xlet, UserEventListener {
    
MijnComponent mc;
    
   HScene scene;
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
  
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
       scene = HSceneFactory.getInstance().getDefaultHScene();
       
       mc = new MijnComponent(0,0,720,576);
       scene.add(mc);
       
       
       
      
       
       scene.validate();
       scene.setVisible(true);
    }

    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
        
        UserEventRepository rep = new UserEventRepository("naam");
        //Toevoegen van arrow keys aan de rep
        rep.addAllArrowKeys();
        rep.addKey(org.havi.ui.event.HRcEvent.VK_ENTER);
        EventManager m = EventManager.getInstance();
        m.addUserEventListener(this, rep);
        
        
        MijnTimerTAsk objMijnTimerTAsk = new MijnTimerTAsk(this);
        Timer t = new Timer();
        t.scheduleAtFixedRate(objMijnTimerTAsk, 0, 50);
        
        
    }
    
    public void callBack()
    {
    mc.scroll();
    }
        

    public void userEventReceived(UserEvent e) {
        System.out.println(e.toString());
        
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_LEFT)
        {
            mc.moveleft();
        }
        
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_RIGHT)
        {
            mc.moveright();
        }
        
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_UP)
        {
            mc.moveup();
        }
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_DOWN)
        {
            mc.movedown();
        }
        
        if(e.getType()==KeyEvent.KEY_PRESSED && e.getCode()==HRcEvent.VK_ENTER)
        {
            mc.shoot();
        }
        
    }
    }

