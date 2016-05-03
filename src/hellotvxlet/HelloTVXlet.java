package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, UserEventListener, HActionListener {
    
MijnComponent mc;
 HTextButton button1;
  HTextButton button2;
  HTextButton button3;
  HTextButton button4;
  boolean gameRunning = false;
    
   HScene scene;
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
  
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
       scene = HSceneFactory.getInstance().getDefaultHScene();
       
     //  mc = new MijnComponent(0,0,720,576);
     //  scene.add(mc);
       
        button1.setVisible(true);
       button2.setVisible(true);
      
       
       
      
       
       scene.validate();
       scene.setVisible(true);
    }

    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
        
        
        button1 = new HTextButton("Play!",200,200,200,100);
    button2 = new HTextButton("How To Play",200,300,200,100);
        button3 = new HTextButton("Back",400,200,200,100);
    button4 = new HTextButton("Back?",400,300,200,100);
        
     scene.add(button1);
     scene.add(button2);   
    button1.setFocusTraversal(button2, button2, null, null);
     button2.setFocusTraversal(button1, button1, null, null); //up down left right
     button1.requestFocus();
   
     
      button1.setActionCommand("button1klik");
     button1.addHActionListener((this)); //Bovenaan toevoegen bij implements
                                        //public class HelloTVXlet implements Clet, HActionListener
     button2.setActionCommand("button2klik");
     button2.addHActionListener( this);
       
       
        UserEventRepository rep = new UserEventRepository("naam");
        //Toevoegen van arrow keys aan de rep
        rep.addAllArrowKeys();
        rep.addKey(org.havi.ui.event.HRcEvent.VK_ENTER);
        EventManager m = EventManager.getInstance();
        m.addUserEventListener(this, rep);
        
        
        MijnTimerTAsk objMijnTimerTAsk = new MijnTimerTAsk(this);
        Timer t = new Timer();
        t.scheduleAtFixedRate(objMijnTimerTAsk, 0, 50);
        
     
          
     scene.validate();
     scene.setVisible(true);
        
    }
    
    public void callBack()
    {
        if(gameRunning)
        {
            mc.scroll();
        }
    
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
    
     public void actionPerformed(ActionEvent event) {
        System.out.println(event.getActionCommand());
        
        if(event.getActionCommand().equals("button1klik"))
        {
        button1.setBackground(Color.GREEN);
           mc = new MijnComponent(0,0,720,576);
           scene.add(mc);
           
           gameRunning = true;
           
           button1.setVisible(false);
           button2.setVisible(false);
        
        scene.repaint();
        }
          if(event.getActionCommand().equals("button2klik"))
        {
        button2.setBackground(Color.GREEN);
     
        
        scene.repaint();
        }
        
     }
}

