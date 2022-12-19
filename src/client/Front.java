package client;

import listener.ClientAction;
import listener.ClientTimerAction;
import listener.ClientWindow;

import javax.swing.*;

public class Front extends JFrame {
    Back back;
    Client client;
    MainPanel mainPanel;
    ClientAction clientAction;
    ClientWindow clientWindow;
    ClientTimerAction clientTimerAction;
    Timer timer;
    public Front() throws Exception{
        setLocation(500,10);
        setLayout(null);
        setSize(400,400);
        setBack(new Back(this));
        setClient(new Client(this));
        setClientTimerAction(new ClientTimerAction(this));
        setClientWindow(new ClientWindow(this));
        setMainPanel(new MainPanel(this));
        add(getMainPanel());
        setClientAction(new ClientAction(this));
        setTitle("Client "+getClient().getName());
        setVisible(true);
        getClient().execute();
        setTimer(new Timer(20,getClientTimerAction()));
        getTimer().start();
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClientAction(ClientAction clientAction) {
        this.clientAction = clientAction;
    }

    public void setClientTimerAction(ClientTimerAction clientTimerAction) {
        this.clientTimerAction = clientTimerAction;
    }

    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Back getBack() {
        return back;
    }

    public Timer getTimer() {
        return timer;
    }

    public Client getClient() {
        return client;
    }

    public ClientAction getClientAction() {
        return clientAction;
    }

    public ClientTimerAction getClientTimerAction() {
        return clientTimerAction;
    }

    public ClientWindow getClientWindow() {
        return clientWindow;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
