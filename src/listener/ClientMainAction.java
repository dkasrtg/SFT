package listener;

import client.Front;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientMainAction implements ActionListener {
    Front front;
    public ClientMainAction(Front front){
        setFront(front);
        getFront().getSend().addActionListener(this);
        getFront().getReceive().addActionListener(this);
        getFront().getSendPanel().getFilechoose().addActionListener(this);
        getFront().getSendPanel().getValidate().addActionListener(this);
        getFront().getReceivePanel().getDownload().addActionListener(this);
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==getFront().getReceive()){
            if (getFront().getBack().getPath()==null) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Path for receiving files");
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.setAcceptAllFileFilterUsed(false);
                jFileChooser.showOpenDialog(getFront());
                System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
                getFront().getBack().setPath(jFileChooser.getSelectedFile().getAbsolutePath());
            }
            getFront().getContentPane().remove(getFront().getSendPanel());
            getFront().getContentPane().invalidate();
            getFront().add(getFront().getReceivePanel());
            getFront().revalidate();
            getFront().repaint();
        }
        if (e.getSource()==getFront().getSend()){
            getFront().getContentPane().remove(getFront().getReceivePanel());
            getFront().getContentPane().invalidate();
            getFront().add(getFront().getSendPanel());
            getFront().revalidate();
            getFront().repaint();
        }
        if (e.getSource()==getFront().getSendPanel().getFilechoose()){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(getFront());
            getFront().getSendPanel().getFile_path().setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        if (e.getSource()==getFront().getSendPanel().getValidate()){
            try {
                getFront().getBack().getClient().getDataOutputStream().writeUTF("RECEIVE-"+getFront().getBack().getClient().getName()+"-" + getFront().getSendPanel().getFile_path().getText().substring(getFront().getSendPanel().getFile_path().getText().lastIndexOf("\\")+1));
                System.out.println("a");
                getFront().getBack().send(getFront().getSendPanel().getFile_path().getText(),getFront().getBack().getClient().getDataOutputStream());
                System.out.println("b");
                getFront().getSendPanel().add_text(getFront().getSendPanel().getFile_path().getText().substring(getFront().getSendPanel().getFile_path().getText().lastIndexOf("\\")+1) +" successfully sent");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource()==getFront().getReceivePanel().getDownload()){
            int ind = getFront().getReceivePanel().getList().getSelectedRow();
            System.out.println(ind);
            try {
                getFront().getBack().getClient().getDataOutputStream().writeUTF("DOWNLOAD-"+getFront().getBack().getAvailable_files().get(ind));
                getFront().getReceivePanel().add_text("Downloading "+getFront().getBack().getAvailable_files().get(ind));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
