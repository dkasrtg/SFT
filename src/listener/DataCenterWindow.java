package listener;

import datacenter.DataCenter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DataCenterWindow implements WindowListener {
    DataCenter dataCenter;
    public DataCenterWindow(DataCenter dataCenter){
        setDataCenter(dataCenter);
        getDataCenter().addWindowListener(this);
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        getDataCenter().getTimer().stop();
        getDataCenter().dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
