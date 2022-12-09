package server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
import java.util.Stack;

public class ListPanel extends JPanel {
    MainPanel mainPanel;
    JTable list;
    public ListPanel(MainPanel mainPanel){
        setMainPanel(mainPanel);
        setSize(350,230);
        setBackground(Color.darkGray);
        setLayout(new BorderLayout());
        setList();
        JScrollPane scrollPane = new JScrollPane(getList());
        add(scrollPane,BorderLayout.CENTER);
        setLocation(25,150);
    }

    public void setList() {
        String[][] data = new String[getMainPanel().getFront().getBack().getFiles().size()][1];
        for (int i=0;i<data.length;i++){
            data[i][0] = getMainPanel().getFront().getBack().getFiles().get(i);
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,new String[]{"List"});
        this.list = new JTable(defaultTableModel);
    }

    public JTable getList() {
        return list;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
