import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private  DataInputStream in;
    private  DataOutputStream out;
    public static void main(String[] args) {
        new Client();
    }

    public Client () {
        try {
            Socket socket = new Socket("Localhost", 18443);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Scanner clientScanner = new Scanner(System.in);

            System.out.println("We are in client!");
            while (true) {
                System.out.println("Client, please enter the message!");
                out.writeUTF(clientScanner.nextLine());//Почему при написании out здесь работает, а уже при прописании в цикле ниже - нет?
                String message  = in.readUTF();
                System.out.println("Server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException  | NullPointerException e) {
                e.printStackTrace();
            }

        }
    }
}
