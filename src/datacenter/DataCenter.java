package datacenter;

import listener.DataCenterTimerAction;
import listener.DataCenterWindow;

import javax.swing.*;

public class DataCenter extends JFrame {
    Back back;
    MainPanel mainPanel;
    Timer timer;
    DataCenterTimerAction dataCenterTimerAction;
    DataCenterWindow dataCenterWindow;
    public DataCenter() throws Exception{
        setLocation(1000,10);
        setBack(new Back(this));
        setMainPanel(new MainPanel(this));
        setSize(400,400);
        setTitle("DataCenter");
        setLayout(null);
        setDataCenterTimerAction(new DataCenterTimerAction(this));
        setTimer(new Timer(20,getDataCenterTimerAction()));
        setDataCenterWindow(new DataCenterWindow(this));
        add(getMainPanel());
        setVisible(true);
        getTimer().start();
    }

    public void setDataCenterWindow(DataCenterWindow dataCenterWindow) {
        this.dataCenterWindow = dataCenterWindow;
    }

    public DataCenterWindow getDataCenterWindow() {
        return dataCenterWindow;
    }

    public void setDataCenterTimerAction(DataCenterTimerAction dataCenterTimerAction) {
        this.dataCenterTimerAction = dataCenterTimerAction;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public DataCenterTimerAction getDataCenterTimerAction() {
        return dataCenterTimerAction;
    }

    public Back getBack() {
        return back;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
