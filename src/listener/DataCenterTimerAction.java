package listener;

import datacenter.DataCenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataCenterTimerAction implements ActionListener {
    DataCenter dataCenter;
    public DataCenterTimerAction(DataCenter dataCenter){
        setDataCenter(dataCenter);
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getDataCenter().repaint();
        getDataCenter().getMainPanel().repaint();
    }
}
