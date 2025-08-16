import java.io.*;
import java.net.Socket;

public class ClientApplicationOne {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            //if (socket.isClosed()) return;
            // Поток для чтения сообщений с сервера
            new Thread(() -> {
                while (true) {
                    try {
                        String message = in.readUTF();
                        System.out.println(message);

                        if (message.contains("Incorrect credentials")) {
                            System.out.println("Please, try again");
                            out.writeUTF(consoleReader.readLine());
                            return;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

            // Поток для ввода сообщений с консоли и отправки на сервер
            new Thread(() -> {
                while (true) {
                    try {
                        out.writeUTF(consoleReader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}