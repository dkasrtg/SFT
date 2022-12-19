package datacenter;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    DataCenter dataCenter;
    public MainPanel(DataCenter dataCenter){
        setDataCenter(dataCenter);
        setSize(400,400);
        setLocation(0,0);
        setBackground(Color.BLACK);
        setLayout(null);
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(Font.DIALOG,Font.ITALIC,15));
        int y = 15;
        for (int i=0;i<getDataCenter().getBack().getText().size();i++){
            graphics2D.drawString(getDataCenter().getBack().getText().get(i),10,y);
            y += 20;
        }
    }
}
