package front;

import client.Client;
import transfer.Transfer;

import javax.swing.*;
import java.awt.*;

public class Send extends JPanel {
    Client client;
    JTextField address;
    JTextField filepath;
    JButton filechoose;
    JButton validate;
    Frame frame;
    public Send(Frame frame) throws Exception{
        setFrame(frame);
        setSelf();
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setSelf(){
        setValidate(new JButton());
        setAddress(new JTextField());
        setFilepath(new JTextField());
        setFilechoose(new JButton());
        setFilepath(new JTextField());
        getFilepath().setSize(180,35);
        setSize(300,230);
        setLocation(0,50);
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        getAddress().setLocation(60, 20);
        getAddress().setSize(180,35);
        add(getAddress());
        getFilechoose().setText("UPLOAD");
        getFilechoose().setSize(90,30);
        getFilechoose().setLocation(10,80);
        getFilepath().setLocation(110,78);
        add(getFilechoose());
        add(getFilepath());
        getValidate().setSize(90,30);
        getValidate().setText("VALIDATE");
        getValidate().setLocation(105,140);
        add(getValidate());
    }

    public void setFilechoose(JButton filechoose) {
        this.filechoose = filechoose;
    }

    public JButton getFilechoose() {
        return filechoose;
    }

    public void setFilepath(JTextField filepath) {
        this.filepath = filepath;
    }

    public JTextField getFilepath() {
        return filepath;
    }

    public void setValidate(JButton validate) {
        this.validate = validate;
    }

    public JButton getValidate() {
        return validate;
    }

    public void setAddress(JTextField address) {
        this.address = address;
    }

    public JTextField getAddress() {
        return address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void send() throws Exception{
        setClient(new Client(getAddress().getText(),getFrame().getPort()));
        getClient().getDataOutputStream().writeUTF(getFilepath().getText());
        getFrame().getTransfer().send(getFilepath().getText(),getClient().getDataOutputStream());
        getClient().close();
    }
}
