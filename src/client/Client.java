package client;

import transfer.Transfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    Socket server;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Transfer transfer;
    public Client(String address, int port) throws Exception{
        setServer(new Socket(address, port));
        setDataInputStream(new DataInputStream(getServer().getInputStream()));
        setDataOutputStream(new DataOutputStream(getServer().getOutputStream()));
        setTransfer(new Transfer());
    }
    public void close() throws Exception{
        getDataInputStream().close();
        getDataOutputStream().close();
        getServer().close();
    }

    public void setServer(Socket server) {
        this.server = server;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
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

    public Transfer getTransfer() {
        return transfer;
    }

    public Socket getServer() {
        return server;
    }
}
