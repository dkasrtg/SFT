package server;

import datacenter.Back;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Server extends SwingWorker {
    Back back;
    Set<String> clientNames;
    Set<ClientThread> clientThreads;
    int port;
    String path;
    public Server(Back back,int port){
        setBack(back);
        setClientNames(new HashSet<>());
        setClientThreads(new HashSet<>());
        setPort(port);
        setPath(getBack().getPath()+"\\"+getPort());
    }

    public void setPath(String path) {
        this.path = path;
        File file = new File(getPath());
        if (!file.exists()){
            file.mkdir();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public void setClientNames(Set<String> clientNames) {
        this.clientNames = clientNames;
    }

    public void setClientThreads(Set<ClientThread> clientThreads) {
        this.clientThreads = clientThreads;
    }

    public Set<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public Set<String> getClientNames() {
        return clientNames;
    }

    public Back getBack() {
        return back;
    }

    @Override
    protected Object doInBackground() throws Exception {
        ServerSocket serverSocket = new ServerSocket(getPort());
        getBack().text_output("Server started successfully at port "+getPort());
        while (true){
            Socket socket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(socket,this);
            getClientThreads().add(clientThread);
            clientThread.execute();
        }
    }
    public void removeClient(String name,ClientThread clientThread){
        if (getClientNames().remove(name)){
            getClientThreads().remove(clientThread);
        }
    }
    public void tell_all_clients_to_refresh(ClientThread excluded) throws Exception {
        for (ClientThread clientThread : getClientThreads()){
            if (clientThread!=excluded){
                clientThread.getDataOutputStream().writeUTF("REFRESH_AVAILABLE;"+getBack().get_available_files(getPath()));
            }
        }
    }
}
