import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {
    private  DataInputStream inStream;
    private  DataOutputStream out;
    private JTextArea chatArea;
    private Socket socket;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {}

    public JTextArea getChatArea() {
        return chatArea;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public DataInputStream getInStream() {
        return inStream;
    }

    public Client (JTextArea chatArea) {
        this.chatArea = chatArea;
        try {
            socket = new Socket("Localhost", 18443);

            inStream = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
//            while(true) {
//                out.writeUTF("Hi, Servak!");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                //inStream.close();
//                //out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        finally {
            try {
                //assert socket != null;
//                System.out.println("Закрылся ли сокет? " + socket.isClosed());
//                out.writeUTF("Gdfs");
//                if (inStream != null) {
//                    inStream.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}