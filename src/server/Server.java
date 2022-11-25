package server;

import transfer.Transfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    Socket client;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Transfer transfer;
    String address;
    public Server(int port) throws Exception{
        setServerSocket(new ServerSocket(port));
        setClient(getServerSocket().accept());
        setDataInputStream(new DataInputStream(getClient().getInputStream()));
        setDataOutputStream(new DataOutputStream(getClient().getOutputStream()));
        setTransfer(new Transfer());
        setAddress(Inet4Address.getLocalHost().getHostAddress());
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public Socket getClient() {
        return client;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getAddress() {
        return address;
    }

    public Transfer getTransfer() {
        return transfer;
    }
}