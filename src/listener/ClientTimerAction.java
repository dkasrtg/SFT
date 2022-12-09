package listener;

import client.Front;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientTimerAction implements ActionListener {
    Front front;
    public ClientTimerAction(Front front){
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
        getFront().getReceivePanel().repaint();
        getFront().getSendPanel().repaint();
    }
}
