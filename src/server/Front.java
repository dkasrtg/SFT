package server;

import listener.ServerTimerAction;

import javax.swing.*;

public class Front extends JFrame {
    MainPanel mainPanel;
    Back back;
    ServerTimerAction serverTimerAction;
    public Front() throws Exception {
        setBack(new Back(this));
        setMainPanel(new MainPanel(this));
        setSize(415,435);
        setLocation(50,50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        add(getMainPanel());
        setVisible(true);
        getBack().getServer().execute();
        setServerTimerAction(new ServerTimerAction(this));
        Timer timer = new Timer(10,getServerTimerAction());
        timer.start();
    }

    public void setServerTimerAction(ServerTimerAction serverTimerAction) {
        this.serverTimerAction = serverTimerAction;
    }

    public ServerTimerAction getServerTimerAction() {
        return serverTimerAction;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public Back getBack() {
        return back;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}