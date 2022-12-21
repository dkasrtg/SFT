package server;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThread extends SwingWorker {
    Socket socket;
    Server server;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public ClientThread(Socket socket,Server server) throws Exception {
        setServer(server);
        setSocket(socket);
        setDataInputStream(new DataInputStream(getSocket().getInputStream()));
        setDataOutputStream(new DataOutputStream(getSocket().getOutputStream()));
        System.out.println(getServer().getPort());
        getServer().getBack().text_output(getServer().getPort()+" : New client "+getDataInputStream().readUTF()+" connected");
        System.out.println(getServer().getPort()+"a");
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            String message = getDataInputStream().readUTF();
            if (message.contains("REMOVE")){
                String name = message.split("-")[1];
                getServer().getBack().text_output(getServer().getPort()+" : Client "+name+" quit ");
                getServer().removeClient(name,this);
            }
            else if (message.contains("RECEIVE")){
                String fn = message.split(";")[1];
                String name = message.split(";")[2];
                getServer().getBack().receive(name,getServer().getPath(),getDataInputStream());
                getServer().getBack().text_output(getServer().getPort()+" : "+ name + " received from "+fn);
                getServer().tell_all_clients_to_refresh(this);
            }
            else if (message.contains("AVAILABLE")){
                getDataOutputStream().writeUTF("AVAILABLE;"+getServer().getBack().get_available_files(getServer().getPath()));
            }
            else if (message.contains("DOWNLOAD")){
                String name = message.split(";")[1];
                String fn = message.split(";")[2];
                getServer().getBack().send(getServer().getPath(),fn,getDataOutputStream());
                getServer().getBack().text_output(getServer().getPort()+" : "+"Client "+name+" downloaded "+fn);
            }
        }
    }
}
