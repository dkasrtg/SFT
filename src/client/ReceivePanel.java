package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ReceivePanel extends JPanel {
    Front front;
    Vector<String> text;
    JTable list;
    JPanel for_table;
    JButton download;
    public ReceivePanel(Front front){
        setFront(front);
        setSize(400,350);
        setLayout(null);
        setLocation(0,50);
        setBackground(Color.black);
        setText(new Vector<>());
        setFor_table(new JPanel());
        getFor_table().setSize(250,250);
        getFor_table().setLocation(75,75);
        getFor_table().setLayout(new BorderLayout());
        add(getFor_table());
        setDownload(new JButton("Download"));
        getDownload().setSize(100,40);
        getDownload().setLocation(270,20);
        add(getDownload());
    }

    public void setDownload(JButton download) {
        this.download = download;
    }

    public JButton getDownload() {
        return download;
    }

    public void setFor_table(JPanel for_table) {
        this.for_table = for_table;
    }

    public JPanel getFor_table() {
        return for_table;
    }

    public void setSelf(){
        setList();
        JScrollPane scrollPane = new JScrollPane(getList());
        getFor_table().add(scrollPane,BorderLayout.CENTER);
    }

    public void setText(Vector<String> text) {
        this.text = text;
    }

    public Vector<String> getText() {
        return text;
    }
    public void add_text(String text){
        if (getText().size()==2){
            getText().removeElementAt(0);
        }
        getText().add(text);
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    public void setList() {
        String[][] data = new String[getFront().getBack().getAvailable_files().size()][1];
        for (int i=0;i<data.length;i++){
            data[i][0] = getFront().getBack().getAvailable_files().get(i);
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,new String[]{"List"});
        this.list = new JTable(defaultTableModel);
    }

    public JTable getList() {
        return list;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        graphics2D.drawString("RECEIVE",300,15);
        int y = 15;
        for (int i=0;i<getText().size();i++){
            graphics2D.drawString(getText().get(i),10,y);
            y += 20;
        }
    }
}
