package listener;

import front.Frame;
import front.Send;
import server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action  implements ActionListener {
    Frame frame;
    public Action(Frame frame){
        setFrame(frame);
        getFrame().getSend().addActionListener(this);
        getFrame().getReceive().addActionListener(this);
        getFrame().getSendPanel().getFilechoose().addActionListener(this);
        getFrame().getSendPanel().getValidate().addActionListener(this);
        getFrame().requestFocusInWindow();
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Frame getFrame() {
        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==getFrame().getReceive()){
            getFrame().getContentPane().remove(getFrame().getSendPanel());
            getFrame().getContentPane().invalidate();
            getFrame().add(getFrame().getReceivePanel());
            getFrame().getContentPane().revalidate();
            getFrame().repaint();
            getFrame().getReceivePanel().setTurn(true);
            new Thread(getFrame().getReceivePanel()).start();
        }
        if (e.getSource()==getFrame().getSend()){
            getFrame().getContentPane().remove(getFrame().getReceivePanel());
            getFrame().getContentPane().invalidate();
            getFrame().add(getFrame().getSendPanel());
            getFrame().getContentPane().revalidate();
            getFrame().repaint();
            getFrame().getReceivePanel().setTurn(false);
        }
        if (e.getSource()==getFrame().getSendPanel().getFilechoose()){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(getFrame());
            getFrame().getSendPanel().getFilepath().setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        if (e.getSource()==getFrame().getSendPanel().getValidate()){
            try {
                System.out.println("ddd");
                getFrame().getSendPanel().send();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
