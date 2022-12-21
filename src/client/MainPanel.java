package client;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    Front front;
    SendPanel sendPanel;
    ReceivePanel receivePanel;
    JButton button;
    public MainPanel(Front front){
        setFront(front);
        setLayout(null);
        setBackground(Color.BLACK);
        setSize(400,400);
        setSendPanel(new SendPanel(this));
        setReceivePanel(new ReceivePanel(this));
        setButton(new JButton("RECEIVE"));
        getButton().setSize(100,40);
        getButton().setLocation(250,10);
        getButton().setFocusPainted(false);
        getButton().setBackground(new Color(59,89,192));
        getButton().setFont(new Font("Tahoma",Font.BOLD,12));
        getButton().setForeground(Color.WHITE);
        add(getButton());
        add(getSendPanel());
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JButton getButton() {
        return button;
    }

    public void setReceivePanel(ReceivePanel receivePanel) {
        this.receivePanel = receivePanel;
    }

    public ReceivePanel getReceivePanel() {
        return receivePanel;
    }

    public void setSendPanel(SendPanel sendPanel) {
        this.sendPanel = sendPanel;
    }

    public SendPanel getSendPanel() {
        return sendPanel;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(Font.SERIF,Font.ITALIC,15));
        int y = 15;
        for (int i=0;i<getFront().getBack().getText().size();i++){
            graphics2D.drawString(getFront().getBack().getText().get(i),10,y);
            y += 20;
        }
    }
}
