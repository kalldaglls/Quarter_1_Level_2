import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private DataInputStream in;//входящий поток!
    private DataOutputStream out;//исходящий поток!

    public static void main(String[] args) {
        new Server();
    }

    public Server(){
        try {
            System.out.println("Server is starting...");
            ServerSocket serverSocket = new ServerSocket(18443);//localhost = 127.0.0.1. Создание
            System.out.println("Server is waiting for clients");

            Socket socket = serverSocket.accept();//хоть клиент на другой стороне, но мы через это должны с ним коммуницировать
            //этот тот же экземпляр(про socket), что и в клиенте, потому что мы его создаем на уровне сервера "аксептируя" приходящего клиента!
            //сверху как будто сканнер некст что-то или actionListener!
            //System.out.println(socket + " - client connected");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String message = in.readUTF();//читаем UTF потому что знаем, что нам на вход придет строка!
                out.writeUTF("Echo:" + message);//Работает как sout?
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //in.close();
                //out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
