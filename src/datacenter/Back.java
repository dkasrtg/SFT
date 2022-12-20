package datacenter;

import server.Server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Back {
    DataCenter dataCenter;
    Vector<String> text;
    Vector<Server> servers;
    String path;
    public Back(DataCenter dataCenter) throws Exception{
        setDataCenter(dataCenter);
        setPath();
        setServers(new Vector<>());
    }

    public void setPath() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose directory for stocking files");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(getDataCenter());
        System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
        this.path = jFileChooser.getSelectedFile().getAbsolutePath();
//        this.path = "F:\\Test\\server";
    }

    public String getPath() {
        return path;
    }

    public void setServers(Vector<Server> servers) throws Exception {
        this.servers = servers;
        boolean test = false;
        int nb = 0;
        while (!test) {
            try {
                String opt = JOptionPane.showInputDialog(null, "Enter the number of server to launch");
                nb = Integer.parseInt(opt);
                if (nb>0) {
                    test = true;
                }
            }catch (Exception e){}
        }
        for (int i=0;i<nb;i++){
            getServers().add(new Server(this,2020+i));
            getServers().get(i).execute();
            Thread.sleep(1000);
        }
    }

    public Vector<Server> getServers() {
        return servers;
    }

    public void setText(Vector<String> text) {
        this.text = text;
    }

    public Vector<String> getText() {
        return text;
    }

    public void setDataCenter(DataCenter dataCenter) {
        this.dataCenter = dataCenter;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }
    public void text_output(String text){
        if (getText()==null){
            setText(new Vector<>());
        }
       while (getText().size()>13){
           getText().removeElementAt(0);
       }
        getText().add(text);
    }
    public void receive(String file_name,String path, DataInputStream dataInputStream) throws Exception{
        int bytes = 0;
        File file = new File(path+"\\"+file_name);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[1024];
        while (size>0 && (bytes=dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size)))!=-1){
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;
        }
        fileOutputStream.close();
    }
    public String get_available_files(String path){
        String rs = "";
        File file = new File(path);
        File[] files1 = file.listFiles();
        for (File file1 : files1){
            rs += file1.getName() + ";";
        }
        return rs;
    }
    public void send(String path,String file_name, DataOutputStream dataOutputStream) throws Exception {
        String real_name = "";
        File file = new File(path);
        File[] files1 = file.listFiles();
        for (File file1 : files1){
            String tmp = file1.getName().split("-")[1];
            if (Objects.equals(tmp, file_name)){
                real_name = file1.getName();
                break;
            }
        }
        int bytes = 0;
        file = new File(path+"\\"+real_name);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeUTF("DOWNLOAD;"+real_name);
        dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}