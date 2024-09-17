import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  DataInputStream inStream;
    private  DataOutputStream out;
    //private final JTextField inputField;
    private JTextArea chatArea;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {}

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getInStream() {
        return inStream;
    }

    public Client (JTextArea chatArea) {
        this.chatArea = chatArea;
        try {
            //Thread.sleep(3000);
            Socket socket = new Socket("Localhost", 18443);

            //BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            inStream = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //System.out.println("We are in client!");
            //ждут от друг друга сообщения, а до этого не могут отправить свое?
//            while (true) {
//                String coolChat = inputField.getText();
//                out.writeUTF(coolChat);
//                String message  = inStream.readUTF();
//                if(message.equals("STOP")) {
//                    break;
//                }
//            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String message = inStream.readUTF();
                            chatArea.append(message + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                //inStream.close();
                //out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
