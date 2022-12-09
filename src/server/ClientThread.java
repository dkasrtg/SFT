package server;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientThread extends SwingWorker {
    Server server;
    Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public ClientThread(Server server,Socket socket) throws Exception {
        setServer(server);
        setSocket(socket);
        setPrintWriter(new PrintWriter(getSocket().getOutputStream(),true));
        setBufferedReader(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
        setDataInputStream(new DataInputStream(getSocket().getInputStream()));
        setDataOutputStream(new DataOutputStream(getSocket().getOutputStream()));
        String name = getDataInputStream().readUTF();
        getServer().getClients_name().add(name);
        getServer().getBack().add_text("New client "+name+" connected");
        getDataOutputStream().writeUTF(getServer().availables_files());
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            String message = getDataInputStream().readUTF();
            System.out.println(message);
            if (message.contains("RECEIVE")){
                String[] data = message.split("-");
                getServer().getBack().receive(data[2],getDataInputStream());
                getServer().getBack().add_text(data[2]+" successfully received from "+data[1]);
                getServer().getBack().refresh();
                getServer().tell_all_client_to_refresh();
                System.out.println("refreshed");
            }
            else if (message.contains("DOWNLOAD")){
                System.out.println("dddd2");
                String file_name = message.split("-")[1];
                getDataOutputStream().writeUTF("DOWNLOAD-"+file_name);
                getServer().getBack().send(file_name,getDataOutputStream());
            }
            else if (message.contains("QUIT")){
                getServer().getBack().add_text(message.split("-")[1]+" quit");
                getServer().removeUser(message.split("-")[1],this);
                getSocket().close();
                return null;
            }
        }
    }
}
