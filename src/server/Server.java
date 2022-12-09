package server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server extends SwingWorker {
    Back back;
    Set<String> clients_name;
    Set<ClientThread> clientThreads;
    public Server(Back back){
        setBack(back);
        setClientThreads(new HashSet<>());
        setClients_name(new HashSet<>());
    }

    @Override
    protected Object doInBackground() throws Exception {
        ServerSocket serverSocket = new ServerSocket(getBack().getPort());
        getBack().add_text("Server launched at port "+getBack().getPort());
        while (true){
            Socket socket = serverSocket.accept();
            ClientThread clientThread  = new ClientThread(this,socket);
            getClientThreads().add(clientThread);
            clientThread.execute();
        }
    }
    public void setBack(Back back) {
        this.back = back;
    }

    public Back getBack() {
        return back;
    }

    public void setClientThreads(Set<ClientThread> clientThreads) {
        this.clientThreads = clientThreads;
    }

    public void setClients_name(Set<String> clients_name) {
        this.clients_name = clients_name;
    }

    public Set<String> getClients_name() {
        return clients_name;
    }

    public Set<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public String availables_files(){
        String rs = "LIST-";
        for (int i=0;i<getBack().getFiles().size()-1;i++){
            rs += getBack().getFiles().get(i) + "-";
        }
        rs += getBack().getFiles().get(getBack().getFiles().size()-1);
        return rs;
    }
    public void removeUser(String userName, ClientThread clientThread) {
        boolean removed = getClients_name().remove(userName);
        if (removed) {
            getClientThreads().remove(clientThread);
        }
    }
    public void tell_all_client_to_refresh() throws IOException {
        for (ClientThread clientThread : getClientThreads()){
            System.out.println(availables_files());
            clientThread.getDataOutputStream().writeUTF("REFRESH;"+availables_files());
            System.out.println("avac");
        }
    }
}