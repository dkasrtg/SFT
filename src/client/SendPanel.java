package client;

import javax.swing.*;
import java.awt.*;

public class SendPanel extends JPanel {
    MainPanel mainPanel;
    JButton choose;
    JButton send;
    JTextField path;
    public SendPanel(MainPanel mainPanel){
        setMainPanel(mainPanel);
        setLayout(null);
        setBackground(Color.GRAY);
        setSize(300,110);
        setLocation(50,250);
        setSend(new JButton("SEND"));
        setChoose(new JButton("CHOOSE"));
        setPath(new JTextField());
        getChoose().setSize(100,40);
        getChoose().setLocation(10,10);
        getPath().setSize(170,40);
        getPath().setLocation(120,10);
        getSend().setSize(100,40);
        getSend().setLocation(100,60);
        add(getChoose());
        add(getPath());
        add(getSend());
    }

    public void setPath(JTextField path) {
        this.path = path;
    }

    public void setChoose(JButton choose) {
        this.choose = choose;
    }

    public void setSend(JButton send) {
        this.send = send;
    }

    public JButton getChoose() {
        return choose;
    }

    public JButton getSend() {
        return send;
    }

    public JTextField getPath() {
        return path;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
