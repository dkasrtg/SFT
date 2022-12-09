package listener;

import server.Front;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerTimerAction implements ActionListener {
    Front front;
    public ServerTimerAction(Front front){
        setFront(front);
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getFront().getMainPanel().repaint();
    }
}
