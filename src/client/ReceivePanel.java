package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReceivePanel extends JPanel {
    MainPanel mainPanel ;
    JTable table;
    JButton download;
    String[][] list;
    JPanel table_container;
    public ReceivePanel(MainPanel mainPanel){
        setDownload(new JButton("DOWNLOAD"));
        setMainPanel(mainPanel);
        setSize(300,210);
        setLocation(50,150);
        setLayout(null);
        setBackground(Color.GRAY);
        getDownload().setText("DOWNLOAD");
        getDownload().setSize(120,40);
        getDownload().setLocation(90,10);
        add(getDownload());
        setTable();
        setTable_container(new JPanel());
        getTable_container().setSize(200,150);
        getTable_container().setLocation(50,50);
        getTable_container().setLayout(new BorderLayout());
        add(getTable_container());
        if (getTable()!=null){
            JScrollPane jScrollPane = new JScrollPane(getTable());
            getTable_container().add(jScrollPane,BorderLayout.CENTER);
        }
    }

    public void setTable_container(JPanel table_container) {
        this.table_container = table_container;
    }

    public JPanel getTable_container() {
        return table_container;
    }

    public void setList(String[][] list) {
        this.list = list;
    }

    public String[][] getList() {
        return list;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setTable() {
        if (getList()==null){
            setList(new String[][]{});
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(getList(),new String[]{"List"});
        this.table = new JTable(defaultTableModel);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getTable() {
        return table;
    }

    public void setDownload(JButton download) {
        this.download = download;
    }

    public JButton getDownload() {
        return download;
    }
}
