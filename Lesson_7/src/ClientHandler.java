import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents client session
 */
public class ClientHandler {
    private String name;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authenticate();
                    if (name != null) { // Проверяем, прошел ли клиент аутентификацию
                        showHistory();
                        writeToHistory();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void authenticate() throws IOException {
        System.out.printf("Client %s auth is ongoing...", this);
        sendMessage(this + ", please sign in!");
        final boolean[] isAuthenticated = {false}; // Флаг для отслеживания статуса аутентификации

        Thread secondThread = new Thread(() -> {
            try {
                for (int i = 0; i < 50 && !Thread.currentThread().isInterrupted(); i++) {
                    Thread.sleep(1000); // Спим по 1 секунде 50 раз
                }
                if (!isAuthenticated[0] && !Thread.currentThread().isInterrupted()) {
                    closeConnection();
                    System.out.println("Client couldn't authorize in time!");
                }
            } catch (InterruptedException e) {
                // Нормальное завершение при прерывании
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread firstThread = new Thread(() -> {
            try {
                String loginInfo = in.readUTF();
                checkAuth(loginInfo);
                isAuthenticated[0] = true; // Установите флаг в true, если аутентификация успешна
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join(); // Ждем завершения первого потока
            secondThread.interrupt(); // Прерываем второй поток, если он еще работает
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        server.unsubscribe(this);
        server.broadcast(String.format("%s left", name));
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public void readMessage() throws IOException {//Читаем свои сообщения!
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = in.readUTF();
                        String formatterMessage = String.format("Message from %s: %s", name, message);
                        System.out.println(formatterMessage);

                        if (message.equalsIgnoreCase("-exit")) {
                            sendMessage("You've successfully logged out!");
                            closeConnection();
                            return;
                        }
                        server.broadcast(formatterMessage);
                    } catch (IOException e) {
                       e.printStackTrace();
                    }
                }
            }
        }).start();
    }
*/
    public void writeToHistory() throws IOException {
        sendMessage(this.getName() + ", please enter the message!");
        while (true) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Lesson_7/Local_History.txt", true))) {
                String coolChat = in.readUTF();
                if (!coolChat.equals("-exit")) {
                    bw.newLine();
                    bw.append(this.name).append(":").append(coolChat);
                    String formatterMessage = String.format("Message from %s: %s", name, coolChat);
                    server.broadcast(formatterMessage);
                }
                else {
                    System.out.println("-exit"); // Сообщаем серверу о выходе
                    closeConnection();
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }

    public void showHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("Lesson_7/Local_History.txt"))) {
            String str;
            List<String> history = new ArrayList<>();

            while ((str = br.readLine()) != null) {
                history.add(str);
            }

            int size = history.size();

            List<String> lastFiveMessages =  history.subList(Math.max(0, size - 5), size);

            for (String s : lastFiveMessages) {
                sendMessage(s + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {//Чтобы нам кто-то прислал сообщения!
            out.writeUTF(message);
    }

    public void checkAuth (String loginInfo) throws IOException {
        while (true) {
            if (loginInfo.startsWith("-auth")) {
                System.out.println("We are in checkAuth 1");
                String[] splittedLoginInfo = loginInfo.split("\\s");
                AuthenticationService.Client maybeClient = server.getAuthenticationService()
                        .findByLoginAndPassword(
                                splittedLoginInfo[1],
                                splittedLoginInfo[2]
                        );
                if (maybeClient != null) {
                    if (!server.checkLogin(maybeClient.getName())) {
                        System.out.println("We are in checkAuth 2");
                        sendMessage("status: authok");
                        name = maybeClient.getName();
                        System.out.println("We are in checkAuth 3");
                        server.broadcast(String.format("%s came in", name));
                        System.out.println("Client auth completed");
                        System.out.println("We are in checkAuth 4");
                        server.subscribe(this);
                        System.out.println("We are in checkAuth 5");
                        return;
                    } else {
                        sendMessage(String.format("%s already logged in", maybeClient.getName()));
                    }
                } else {
                    sendMessage("Incorrect credentials");
                    return;
                }
            }
        }
    }
}