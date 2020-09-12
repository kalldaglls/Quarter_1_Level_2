import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        new Client();
    }

    public Client () {
        try {
            Socket socket = new Socket("Localhost", 18443);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner clientScanner = new Scanner(System.in);

            System.out.println("Client, please enter the message!");
            System.out.println("We are in client!");
            while (true) {
                out.writeUTF(clientScanner.nextLine());//Почему при написании out здесь работает, а уже при прописании в цикле ниже - нет?
                String message  = in.readUTF();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
