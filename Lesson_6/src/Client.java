import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  DataInputStream inStream;
    private  DataOutputStream out;
    public static void main(String[] args) {
        new Client();
    }

    public Client () {
        try {
            Socket socket = new Socket("Localhost", 18443);

            BufferedReader consoleReader =new BufferedReader(new InputStreamReader(System.in));
            inStream = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //Scanner clientScanner = new Scanner(System.in);

            System.out.println("We are in client!");
            while (true) {
                String coolChat = consoleReader.readLine();
                System.out.println("Client, please enter the message!");
                out.writeUTF(coolChat);
                String message  = inStream.readUTF();
                Thread.sleep(5000);
                System.out.println("Server: " + message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
                out.close();
            } catch (IOException  | NullPointerException e) {
                e.printStackTrace();
            }

        }
    }
}
