package server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;

public class Back {
    Front front;
    Vector<String> files;
    String path;
    int port;
    Server server;
    Vector<String>  text;
    public Back(Front front){
        String rs = JOptionPane.showInputDialog(null,"Port","Port",JOptionPane.INFORMATION_MESSAGE);
        while (!analyze(rs)){
            rs = JOptionPane.showInputDialog(null,"Port","Port",JOptionPane.INFORMATION_MESSAGE);
        }
        setPort(Integer.parseInt(rs));
        setText(new Vector<>());
        setFront(front);
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Path for stocking files");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(getFront());
        setPath(jFileChooser.getSelectedFile().getAbsolutePath());
        setFiles(new Vector<>());
        setServer(new Server(this));
    }

    public void setText(Vector<String> text) {
        this.text = text;
    }

    public Vector<String> getText() {
        return text;
    }
    public void add_text(String text){
        if (getText().size()==7){
            getText().removeElementAt(0);
        }
        getText().add(text);
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setFiles(Vector<String> files) {
        this.files = files;
        File file = new File(getPath());
        File[] files1 = file.listFiles();
        for (File file1 : files1){
            getFiles().add(file1.getName());
        }
    }

    public Vector<String> getFiles() {
        return files;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }
    public void receive(String file_name,DataInputStream dataInputStream) throws Exception{
        int bytes = 0;
        File file = new File(getPath()+"\\"+file_name);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[4*1024];
        while (size>0 && (bytes=dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size)))!=-1){
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;
        }
        fileOutputStream.close();
    }
    public void refresh(){
        setFiles(new Vector<>());
        for (int i = 0;i<getFiles().size()-1;i++){
            getFront().getMainPanel().getListPanel().getList().setValueAt(getFiles().get(i),i,0);
        }
        System.out.println("aa");
        DefaultTableModel defaultTableModel = (DefaultTableModel) getFront().getMainPanel().getListPanel().getList().getModel();
        System.out.println("bb");
        defaultTableModel.addRow(new String[]{getFiles().get(getFiles().size()-1)});
        System.out.println("cc");
        defaultTableModel.fireTableDataChanged();
        System.out.println("dd");
        getFront().getMainPanel().getListPanel().getList().repaint();
        System.out.println("ok");
    }
    public void send(String file_name, DataOutputStream dataOutputStream) throws Exception {
        int bytes = 0;
        File file = new File(getPath()+"\\"+file_name);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
    public boolean analyze(String rs){
        try {
            int p = Integer.parseInt(rs);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
