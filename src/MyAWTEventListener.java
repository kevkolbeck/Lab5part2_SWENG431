
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wxw18
 */
public class MyAWTEventListener implements AWTEventListener {
    ArrayList<AWTEvent> alae;
    ArrayList<AWTEvent> al;
    
    boolean recording = false;

    MyAWTEventListener(ArrayList<AWTEvent> alae, ArrayList<AWTEvent> al) {
        this.alae = alae;
        this.al = al;
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        if(event.getID() == MouseEvent.MOUSE_CLICKED){
            Object o = event.getSource();
            if(o instanceof JButton){
                JButton button = (JButton)o;
                if(button.getText().equals("Record")){
                    recording = true;
                }
                
                if(button.getText().equals("Stop")){
                recording = false;
                }
            }
        }
        if(recording){
            //when  recording, if move mouved or pressed
            if (event.getID()==MouseEvent.MOUSE_MOVED || event.getID() == MouseEvent.MOUSE_PRESSED){
                //cast to mouse event (from lab video instruction) 
                MouseEvent me = (MouseEvent) event;
                
                //get the component of where me occured
                Component c = me.getComponent();
                
                //get the window of the component
                Window w = SwingUtilities.getWindowAncestor(c);
                
                //make sure window is found and is from GUI-1
                if (w!= null && w.getClass().getName().equals("gui.MyGUI")){
                    //recording mouse events
                    alae.add(event);
                    
                    // previous lab functionality for running tests
                    if (al.size() < 4) {
                        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
                            if (event.getSource() instanceof JTextField ||
                                event.getSource() instanceof JButton) {
                                al.add(event);
                            }
                        }
                    }    
                }
            }
        }
    }
}
