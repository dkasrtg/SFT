package front;

import javax.swing.*;
import java.awt.*;
import listener.Action;
import transfer.Transfer;

public class Frame extends JFrame {
    int port;
    JPanel choice;
    JButton send;
    JButton receive;
    Action action;
    Transfer transfer;
    Send sendPanel;
    Receive receivePanel;

    public Frame() throws Exception{
        setPort();
        setTransfer(new Transfer());
        setReceivePanel(new Receive(this));
        setSendPanel(new Send(this));
        setSelf();
        setAction(new Action(this));
    }

    public void setReceivePanel(Receive receivePanel) {
        this.receivePanel = receivePanel;
    }

    public Receive getReceivePanel() {
        return receivePanel;
    }

    public void setSendPanel(Send sendPanel) {
        this.sendPanel = sendPanel;
    }

    public Send getSendPanel() {
        return sendPanel;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setSelf() throws Exception {
        setChoice(new JPanel());
        setSend(new JButton());
        setReceive(new JButton());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(316,280);
        setFocusable(true);
        setLayout(null);
        getChoice().setLayout(null);
        getSend().setSize(90,30);
        getReceive().setSize(90,30);
        getSend().setLocation(40,10);
        getReceive().setLocation(170,10);
        getChoice().setSize(300,50);
        getChoice().setBackground(Color.BLACK);
        getChoice().setLocation(0,0);
        getSend().setText("SEND");
        getReceive().setText("RECEIVE");
        getChoice().add(getSend());
        getChoice().add(getReceive());
        add(getChoice());
        add(getSendPanel());
        setVisible(true);
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public void setChoice(JPanel choice) {
        this.choice = choice;
    }


    public void setReceive(JButton receive) {
        this.receive = receive;
    }

    public void setSend(JButton send) {
        this.send = send;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public JButton getReceive() {
        return receive;
    }

    public JButton getSend() {
        return send;
    }



    public JPanel getChoice() {
        return choice;
    }
}
