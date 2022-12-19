package client;

import javax.swing.*;

public class Client extends SwingWorker {
    Front front;
    String name;

    public Client(Front front) throws Exception{
        setFront(front);
        setName();
        getFront().getBack().writeUTF(getName());
    }

    public void setName() {
        this.name = JOptionPane.showInputDialog(null,"Enter your name");
    }

    public String getName() {
        return name;
    }

    public void setFront(Front front) {
        this.front = front;
    }

    public Front getFront() {
        return front;
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            String message = getFront().getBack().readUTF();
            System.out.println(message);
            if (message.contains("AVAILABLE")){
                getFront().getMainPanel().getReceivePanel().setList(getFront().getBack().get_file_list(message));
                System.out.println("a");
                getFront().getBack().refresh();
                System.out.println("b");
                System.out.println(getFront().getMainPanel().getReceivePanel().getList().length);
            }
            if (message.contains("DOWNLOAD")){
                getFront().getBack().receive_all(message);
            }
            if (message.contains("REFRESH")){
                getFront().getBack().refresh();
            }
        }
    }
}
