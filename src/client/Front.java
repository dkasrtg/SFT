package client;

import listener.ClientMainAction;
import listener.ClientTimerAction;
import listener.ClientWindow;

import javax.swing.*;
import java.awt.*;

public class Front extends JFrame {
    Back back;
    SendPanel sendPanel;
    ReceivePanel receivePanel;
    JPanel control;
    JButton send;
    JButton receive;
    ClientMainAction clientMainAction;
    ClientTimerAction clientTimerAction;
    ClientWindow clientWindow;
    public Front(){
        setBack(new Back(this));
        setSize(415,435);
        setLocation(600,50);
        setLayout(null);
        setControl(new JPanel());
        getControl().setSize(400,50);
        getControl().setBackground(Color.white);
        getControl().setLayout(null);
        setSend(new JButton("Send"));
        setReceive(new JButton("Receive"));
        getSend().setSize(100,40);
        getReceive().setSize(100,40);
        getSend().setLocation(67,5);
        getReceive().setLocation(227,5);
        getControl().add(getSend());
        getControl().add(getReceive());
        setReceivePanel(new ReceivePanel(this));
        setSendPanel(new SendPanel(this));
        add(getControl());
        add(getSendPanel());
        setClientMainAction(new ClientMainAction(this));
        setClientWindow(new ClientWindow(this));
        setResizable(false);
        setVisible(true);
        getBack().getClient().execute();
        setClientTimerAction(new ClientTimerAction(this));
        Timer timer = new Timer(10,getClientTimerAction());
        timer.start();
    }

    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    public ClientWindow getClientWindow() {
        return clientWindow;
    }

    public void setClientTimerAction(ClientTimerAction clientTimerAction) {
        this.clientTimerAction = clientTimerAction;
    }

    public ClientTimerAction getClientTimerAction() {
        return clientTimerAction;
    }

    public void setClientMainAction(ClientMainAction clientMainAction) {
        this.clientMainAction = clientMainAction;
    }

    public ClientMainAction getClientMainAction() {
        return clientMainAction;
    }

    public ReceivePanel getReceivePanel() {
        return receivePanel;
    }

    public SendPanel getSendPanel() {
        return sendPanel;
    }
    public void setReceive(JButton receive) {
        this.receive = receive;
    }

    public JButton getReceive() {
        return receive;
    }

    public void setSend(JButton send) {
        this.send = send;
    }

    public JButton getSend() {
        return send;
    }

    public void setControl(JPanel control) {
        this.control = control;
    }

    public JPanel getControl() {
        return control;
    }

    public void setSendPanel(SendPanel sendPanel) {
        this.sendPanel = sendPanel;
    }

    public void setReceivePanel(ReceivePanel receivePanel) {
        this.receivePanel = receivePanel;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public Back getBack() {
        return back;
    }
}
