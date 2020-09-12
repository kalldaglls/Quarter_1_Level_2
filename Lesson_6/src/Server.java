import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        new Server();
    }

    public Server(){
        try {
            ServerSocket serverSocket = new ServerSocket(18443);

            Socket socket = serverSocket.accept();
            System.out.println(socket);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner serverScanner = new Scanner(System.in);

            while (true) {
                String message = in.readUTF();
                System.out.println(message);
                System.out.println("Server, please write the message!");
                out.writeUTF(serverScanner.nextLine());
                //System.out.println("Want you write another message?");
                /*while (serverScanner.nextLine().equals("yes")) {
                    System.out.println("Enter another message!");
                    out.writeUTF(serverScanner.nextLine());
                }

                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
