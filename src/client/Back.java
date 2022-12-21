package client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Vector;

public class Back {
    Front front;
    Vector<Socket> sockets;
    Vector<DataInputStream> dataInputStreams;
    Vector<DataOutputStream> dataOutputStreams;
    Vector<String> text;
    String path;
    public Back(Front front) throws Exception{
        setFront(front);
        setSockets(new Vector<>());
        setDataInputStreams(new Vector<>());
        setDataOutputStreams(new Vector<>());
    }
    public void setPath() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose directory for receiving files");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.showOpenDialog(getFront());
        System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
        this.path = jFileChooser.getSelectedFile().getAbsolutePath();
    }

    public String getPath() {
        return path;
    }

    public void setText(Vector<String> text) {
        this.text = text;
    }

    public Vector<String> getText() {
        return text;
    }
    public void text_output(String text){
        if (getText()==null){
            setText(new Vector<>());
        }
        if (getText().size()==7){
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

    public void setDataInputStreams(Vector<DataInputStream> dataInputStreams) throws Exception{
        this.dataInputStreams = dataInputStreams;
        for (int i=0;i<getSockets().size();i++){
            getDataInputStreams().add(new DataInputStream(getSockets().get(i).getInputStream()));
        }
    }

    public void setDataOutputStreams(Vector<DataOutputStream> dataOutputStreams) throws Exception{
        this.dataOutputStreams = dataOutputStreams;
        for (int i=0;i<getSockets().size();i++){
            getDataOutputStreams().add(new DataOutputStream(getSockets().get(i).getOutputStream()));
        }
    }

    public void setSockets(Vector<Socket> sockets) {
        this.sockets = sockets;
        boolean test = true;
        int i=2020;
        while (test){
            try {
                Socket socket = new Socket("localhost",i);
                socket.getInputStream();
                getSockets().add(socket);
                text_output("Connected to server "+i);
                i++;
            }
            catch (Exception e){
                test = false;
            }
        }
    }

    public Vector<DataInputStream> getDataInputStreams() {
        return dataInputStreams;
    }

    public Vector<DataOutputStream> getDataOutputStreams() {
        return dataOutputStreams;
    }

    public Vector<Socket> getSockets() {
        return sockets;
    }
    public void writeUTF(String s) throws Exception{
        for (int i=0;i<getSockets().size();i++){
            getDataOutputStreams().get(i).writeUTF(s);
            System.out.println("writing");
        }
    }
    public void receive_all(String message) throws Exception{
        File file = new File(getPath()+"\\temp");
        if (file.exists()){
            deleteDirectory(file);
        }
        file.mkdir();
        String[] sp = message.split("~");
        for (int i=0;i<getSockets().size();i++){
            receive(sp[i].split(";")[1],getDataInputStreams().get(i));
        }
        File[] files = file.listFiles();
        Arrays.sort(files);
        File file1 = new File(getPath()+"\\"+files[0].getName().split("-")[1]);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        for (int i=0;i<files.length;i++){
            Files.copy(files[i].toPath(),bufferedOutputStream);
        }
        deleteDirectory(file);
        text_output(file1.getName()+" successfully received");
    }
    public void receive(String file_name,DataInputStream dataInputStream) throws Exception {
        int bytes = 0;
        File file = new File(getPath()+"\\temp\\" + file_name);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;
        }
        fileOutputStream.close();
    }
    public void divide_and_send(String path) throws Exception{
//        Divide
        String path_new = path.substring(0,path.lastIndexOf(("\\"))+1)+"temp";
        System.out.println(path);
        String file_name = path.substring(path.lastIndexOf("\\")+1);
        System.out.println(path_new);
        System.out.println(file_name);
        File temp = new File(path_new);
        if (!temp.exists()){
            temp.mkdir();
        }
        int count = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ((bytes=fileInputStream.read(buffer))!=-1){
            count ++;
        }
        System.out.println(count);
        fileInputStream.close();
        int nb = count/getSockets().size();
        int rm = count%getSockets().size();
        System.out.println(nb+"--"+rm);
        fileInputStream = new FileInputStream(file);
        for (int i=0;i<getSockets().size();i++){
            if (i==getSockets().size()-1){
                nb = nb + rm;
            }
            int tst = 0;
            String temp_name = i + "-" + file_name;
            String temp_path = path_new + "\\" + temp_name;
            File file1 = new File(temp_path);
            file1.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            while (tst<nb){
                bytes=fileInputStream.read(buffer);
                fileOutputStream.write(buffer,0,bytes);
                fileOutputStream.flush();
                tst ++;
            }
            fileOutputStream.close();
        }
        fileInputStream.close();
//        Send
        for (int i=0;i<getSockets().size();i++){
            String file_temp_name = path_new +"\\"+ i +"-"+ file_name;
            send_one(i+"-"+file_name,file_temp_name,getDataOutputStreams().get(i));
        }
        text_output(file_name +" sent successfully");
        File file1 = new File(path_new);
        deleteDirectory(file1);
    }
    public void send_one(String file_name,String path,DataOutputStream dataOutputStream) throws Exception{
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeUTF("RECEIVE;"+getFront().getClient().getName()+";"+file_name);
        dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
    public boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
    public String readUTF() throws IOException {
        String rs = "";
        for (int i=0;i<getSockets().size();i++){
            rs += getDataInputStreams().get(i).readUTF() + "~";
        }
        return rs;
    }
    public String[][] get_file_list(String message){
        String[] sp = message.split("~");
        Vector<String> p = new Vector<>();
        for (int i=0;i<sp.length;i++){
            String[] tp = sp[i].split(";");
            for (int j=1;j<tp.length;j++){
                String real = tp[j].split("-")[1];
                if (!p.contains(real)) {
                    p.add(real);
                }
            }
        }
        String[][] rs = new String[p.size()][1];
        for (int i=0;i<rs.length;i++){
            rs[i][0] = p.get(i);
        }
        return rs;
    }
    public void refresh(){
        System.out.println("1");
        DefaultTableModel defaultTableModel = (DefaultTableModel) getFront().getMainPanel().getReceivePanel().getTable().getModel();
        System.out.println("2");
        System.out.println("listl"+getFront().getMainPanel().getReceivePanel().getList().length);
        int j=0;
        for (int i=0;i<getFront().getMainPanel().getReceivePanel().getTable().getRowCount();i++){
            getFront().getMainPanel().getReceivePanel().getTable().setValueAt(getFront().getMainPanel().getReceivePanel().getList()[i][0],i,0);
            j++;
        }
        for (int i=j;i<getFront().getMainPanel().getReceivePanel().getList().length;i++){
            defaultTableModel.addRow(new String[]{getFront().getMainPanel().getReceivePanel().getList()[i][0]});
        }
        defaultTableModel.fireTableDataChanged();
        getFront().getMainPanel().getReceivePanel().getTable().repaint();
    }
}
