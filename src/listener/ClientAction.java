package listener;

import client.Front;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientAction implements ActionListener {
    Front front;
    public ClientAction(Front front){
        setFront(front);
        getFront().getMainPanel().getSendPanel().getSend().addActionListener(this);
        getFront().getMainPanel().getSendPanel().getChoose().addActionListener(this);
        getFront().getMainPanel().getButton().addActionListener(this);
        getFront().getMainPanel().getReceivePanel().getDownload().addActionListener(this);
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==getFront().getMainPanel().getSendPanel().getSend()){
            System.out.println("aaaa");
            try {
                System.out.println("bbbb");
                getFront().getBack().divide_and_send(getFront().getMainPanel().getSendPanel().getPath().getText());
                System.out.println("ccc");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource()==getFront().getMainPanel().getSendPanel().getChoose()){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(getFront());
            getFront().getMainPanel().getSendPanel().getPath().setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        if (e.getSource()==getFront().getMainPanel().getButton()){
            if (Objects.equals(getFront().getMainPanel().getButton().getText(), "RECEIVE")){
                if(getFront().getBack().getPath()==null){
                    getFront().getBack().setPath();
                }
                getFront().getMainPanel().getButton().setText("SEND");
                getFront().getMainPanel().remove(getFront().getMainPanel().getSendPanel());
                getFront().getMainPanel().invalidate();
                getFront().getMainPanel().add(getFront().getMainPanel().getReceivePanel());
                getFront().getMainPanel().revalidate();
                getFront().getMainPanel().repaint();
                try {
                    getFront().getBack().writeUTF("AVAILABLE");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (Objects.equals(getFront().getMainPanel().getButton().getText(), "SEND")){
                getFront().getMainPanel().getButton().setText("RECEIVE");
                getFront().getMainPanel().remove(getFront().getMainPanel().getReceivePanel());
                getFront().getMainPanel().invalidate();
                getFront().getMainPanel().add(getFront().getMainPanel().getSendPanel());
                getFront().getMainPanel().revalidate();
                getFront().getMainPanel().repaint();
            }
        }
        if (e.getSource()==getFront().getMainPanel().getReceivePanel().getDownload()){
            int selected = getFront().getMainPanel().getReceivePanel().getTable().getSelectedRow();
            try {
                getFront().getBack().writeUTF("DOWNLOAD;"+getFront().getClient().getName()+";"+getFront().getMainPanel().getReceivePanel().getList()[selected][0]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
