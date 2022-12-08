package front;

//https://github.com/dkasrtg/SFT.git

import server.Server;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.Inet4Address;

public class Receive extends JPanel  implements Runnable{
    JLabel address;
    JLabel info;
    Frame frame;
    Server server;
    boolean turn;
    String default_path;
    public Receive(Frame frame) throws Exception {
        setSelf();
        setFrame(frame);
        setTurn(false);
        setDefault_path();
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setSelf() throws Exception{
        setAddress(new JLabel());
        setInfo(new JLabel());
        setSize(300,230);
        setLocation(0,50);
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        getAddress().setText(Inet4Address.getLocalHost().getHostAddress());
        getAddress().setSize(100,40);
        getAddress().setLocation(100,20);
        getAddress().setForeground(Color.white);
        getAddress().setFont(new Font(Font.DIALOG,Font.BOLD, 20));
        getAddress().setBorder(BorderFactory.createLineBorder(Color.white,5));
        add(getAddress());
        getInfo().setSize(300,80);
        getInfo().setLocation(0,80);
        getInfo().setText("Nothing");
        getInfo().setForeground(Color.white);
        getInfo().setFont(new Font(Font.DIALOG,Font.BOLD, 15));
        getInfo().setBorder(BorderFactory.createLineBorder(Color.white,5));
        add(getInfo());
    }

    public void setAddress(JLabel address) {
        this.address = address;
    }

    public void setInfo(JLabel info) {
        this.info = info;
    }

    public JLabel getAddress() {
        return address;
    }

    public JLabel getInfo() {
        return info;
    }

    public void run() {
        try {
            setServer(new Server(getFrame().getPort()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (isTurn()){
            try {
                String g = getServer().getDataInputStream().readUTF();
                g = g.substring(g.lastIndexOf("\\")+1);
                getFrame().getReceivePanel().getInfo().setText(g + " from "+getServer().getClient().getRemoteSocketAddress().toString());
                getFrame().getTransfer().receive(getDefault_path()+g, getServer().getDataInputStream());

            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
    public void setDefault_path(){
        File file = new File("C:\\received");
        if (!file.exists()){
            file.mkdir();
        }
        this.default_path  = "C:\\received\\";
    }
    public String getDefault_path() {
        return default_path;
    }
}
