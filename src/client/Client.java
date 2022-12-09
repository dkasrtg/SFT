package client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Client extends SwingWorker {
    Back back;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    PrintWriter printWriter;
    String name;
    public Client(Back back,String name){
        setBack(back);
        setName(name);
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public Back getBack() {
        return back;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    @Override
    protected Object doInBackground() throws Exception {
        Socket socket = new Socket(getBack().getHost(),getBack().getPort());
        setDataOutputStream(new DataOutputStream(socket.getOutputStream()));
        setDataInputStream(new DataInputStream(socket.getInputStream()));
        setPrintWriter(new PrintWriter(getDataOutputStream()));
        getDataOutputStream().writeUTF(getName());
        while (true){
            String data =  getDataInputStream().readUTF();
            System.out.println(data);
            if (data.contains("LIST") && !data.contains("REFRESH")){
                getBack().setAvailable_files(new Vector<>());
                String[] all = data.split("-");
                for (int i=1;i<all.length;i++){
                    getBack().getAvailable_files().add(all[i]);
                }
                System.out.println(getBack().getAvailable_files());
                getBack().getFront().getReceivePanel().setSelf();
            }
            else if (data.contains("DOWNLOAD")){
                System.out.println("dddd1");
                getBack().receive(data.split("-")[1],getDataInputStream());
                getBack().getFront().getReceivePanel().add_text(data.split("-")[1] +" received successfully");
            }
            else if (data.contains("REFRESH")){
                System.out.println("clref");
                String p = data.split(";")[1];
                String[] all = p.split("-");
                getBack().setAvailable_files(new Vector<>());
                for (int i=1;i<all.length;i++){
                    getBack().getAvailable_files().add(all[i]);
                }
                getBack().refresh();
            }
        }
    }
}