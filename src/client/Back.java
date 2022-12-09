package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Vector;

public class Back {
    String host;
    int port;
    Client client;
    Front front;
    Vector<String> available_files;
    String path;
    public Back(Front front){
        String rs = JOptionPane.showInputDialog(null,"Host-Port-Name","Host-Port-Name",JOptionPane.INFORMATION_MESSAGE);
        while (!analize(rs)){
            rs = JOptionPane.showInputDialog(null,"Host-Port-Name","Host-Port-Name",JOptionPane.INFORMATION_MESSAGE);
        }
        String[] p = rs.split("-");
        setHost(p[0]);
        setPort(Integer.parseInt(p[1]));
        setFront(front);
        setClient(new Client(this,p[2]));
        setAvailable_files(new Vector<>());
        setPath(null);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setAvailable_files(Vector<String> available_files) {
        this.available_files = available_files;
    }

    public Vector<String> getAvailable_files() {
        return available_files;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public Client getClient() {
        return client;
    }

    public String getHost() {
        return host;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }
    public void send(String path, DataOutputStream dataOutputStream) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
    public void receive(String file_name, DataInputStream dataInputStream) throws Exception{
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
        for (int i = 0;i<getAvailable_files().size()-1;i++){
            getFront().getReceivePanel().getList().setValueAt(getAvailable_files().get(i),i,0);
        }
        System.out.println("aa");
        DefaultTableModel defaultTableModel = (DefaultTableModel) getFront().getReceivePanel().getList().getModel();
        System.out.println("bb");
        defaultTableModel.addRow(new String[]{getAvailable_files().get(getAvailable_files().size()-1)});
        System.out.println("cc");
        defaultTableModel.fireTableDataChanged();
        System.out.println("dd");
        getFront().getReceivePanel().getList().repaint();
        System.out.println("ok");
    }
    public boolean analize(String rs){
        if (!rs.contains("-")){
            return false;
        }
        String[] p = rs.split("-");
        if (p.length!=3){
            return false;
        }
        try {
            int d = Integer.parseInt(p[1]);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}