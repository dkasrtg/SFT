package listener;

import client.Front;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class ClientWindow implements WindowListener {
    Front front;
    public ClientWindow(Front front){
        setFront(front);
        getFront().addWindowListener(this);
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            System.out.println("q1");
            getFront().getBack().getClient().getDataOutputStream().writeUTF("QUIT-"+getFront().getBack().getClient().getName());
            System.out.println("q2");
            getFront().dispose();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
