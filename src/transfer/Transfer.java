package transfer;

import java.io.*;

public class Transfer {
    public void receive(String path, DataInputStream dataInputStream) throws Exception{
        int bytes = 0;
        File file = new File(path);
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
}
