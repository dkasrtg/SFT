package server;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    Front front;
    ListPanel listPanel;
    public MainPanel(Front front){
        setFront(front);
        setSize(400,400);
        setBackground(Color.black);
        setLayout(null);
        setLocation(0,0);
        setListPanel(new ListPanel(this));
        add(getListPanel());
    }

    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
    }

    public ListPanel getListPanel() {
        return listPanel;
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
        graphics2D.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        int y = 15;
        for (int i=0;i<getFront().getBack().getText().size();i++){
            graphics2D.drawString(getFront().getBack().getText().get(i),10,y);
            y += 20;
        }
    }
}
