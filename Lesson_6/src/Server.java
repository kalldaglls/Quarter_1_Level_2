import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

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

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Random random = new Random();
            ServerIntroductions[] values = ServerIntroductions.values();
            ServerIntroductions value = ServerIntroductions.HI;
            ServerIntroductions randomIntroduction = values[random.nextInt(values.length)];

            value.compareTo(values[0]);

            out.writeUTF(String.valueOf(randomIntroduction));

//            System.out.println(socket.getKeepAlive());
//            System.out.println(socket.isClosed());
//            System.out.println(socket.isConnected());

            while (true) {
                String message = in.readUTF();//читаем UTF потому что знаем, что нам на вход придет строка!
                out.writeUTF("Echo:" + message);//Работает как sout?
                //System.out.println(socket.isConnected());
            }

        } catch (SocketException e) {
            System.err.println("Клиент неожиданно отключился: " + e.getMessage());
            // Здесь можно добавить логику для обработки отключения клиента
            // Например, удалить информацию о клиенте из списка подключенных клиентов
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        finally {
            try {
                assert in != null;
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}