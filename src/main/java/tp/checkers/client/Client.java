package tp.checkers.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    Client() {
        try {
            socket = new Socket("localhost", 4444);

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("CLIENT: connected");
        } catch (IOException e) {
            System.out.println("Error");
        }

        /* frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); */
    }

    private void close() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
