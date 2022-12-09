package client;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SendPanel extends JPanel {
    Front front;
    Vector<String> text;
    JButton filechoose;
    JButton validate;
    JTextField file_path;
    public SendPanel(Front front){
        setFront(front);
        setSize(400,350);
        setBackground(Color.black);
        setLayout(null);
        setLocation(0,50);
        setText(new Vector<>());
        setFilechoose(new JButton("Choose file"));
        getFilechoose().setSize(100,40);
        getFilechoose().setLocation(10,200);
        add(getFilechoose());
        setFile_path(new JTextField());
        getFile_path().setSize(260,40);
        getFile_path().setLocation(130,200);
        add(getFile_path());
        setValidate(new JButton("Validate"));
        getValidate().setSize(100,40);
        getValidate().setLocation(150,260);
        add(getValidate());
    }

    public void setFilechoose(JButton filechoose) {
        this.filechoose = filechoose;
    }

    public void setValidate(JButton validate) {
        this.validate = validate;
    }

    public void setFile_path(JTextField file_path) {
        this.file_path = file_path;
    }

    public JButton getFilechoose() {
        return filechoose;
    }

    public JButton getValidate() {
        return validate;
    }

    public JTextField getFile_path() {
        return file_path;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    public void setText(Vector<String> text) {
        this.text = text;
    }

    public Vector<String> getText() {
        return text;
    }
    public void add_text(String text){
        if (getText().size()==10){
            getText().removeElementAt(0);
        }
        getText().add(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        graphics2D.drawString("SEND",300,15);
        int y = 15;
        for (int i=0;i<getText().size();i++){
            graphics2D.drawString(getText().get(i),10,y);
            y += 20;
        }
    }

}
