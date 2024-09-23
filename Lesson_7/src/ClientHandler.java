import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * Represents client session
 */
public class ClientHandler {
    private String name;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private BufferedReader consoleReader;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ClientHandler that = (ClientHandler) o;
//        return Objects.equals(name, that.name) && Objects.equals(socket, that.socket) && Objects.equals(in, that.in) && Objects.equals(out, that.out) && Objects.equals(server, that.server) && Objects.equals(consoleReader, that.consoleReader);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, socket, in, out, server, consoleReader);
//    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authenticate();
                    if (name != null) { // Проверяем, прошел ли клиент аутентификацию
                        //readMessage();
                        //showHistory();
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
        System.out.println("Client auth is ongoing...");
        sendMessage(this + ", please sign in!");
        final boolean[] isAuthenticated = {false}; // Флаг для отслеживания статуса аутентификации

        Thread secondThread = new Thread(() -> {
            try {
                Thread.sleep(50000); // Тайм-аут 50 секунд
                if (!isAuthenticated[0]) {
                    closeConnection();
                    socket.close();
                    System.out.println("Client couldn't authorize in time!");
                }
            } catch (InterruptedException | IOException e) {
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





        /*Thread firstThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread secondThread = new Thread(new Runnable() {

            @Override
            public void run() {
                checkAuth(loginInfo);
            }
        });

         */



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

    public void readMessage() throws IOException {//Читаем свои сообщения!
        while (true) {
            String message = in.readUTF();
            String formatterMessage = String.format("Message from %s: %s", name, message);
            System.out.println(formatterMessage);

            /*
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                bw.newLine();
                bw.append(formatterMessage);
            } catch (IOException e) {

                throw new RuntimeException("SWW",e);
            }
                 */
            if (message.equalsIgnoreCase("-exit")) {
                sendMessage("You've successfully logged out!");
                closeConnection();
                return;
            }

            server.broadcast(formatterMessage);
        }
    }

    public void writeToHistory() throws IOException {
        while (true) {
            sendMessage("Client, please enter the message!");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Lesson_7/Local_History.txt", true))) {
                String coolChat = in.readUTF();
                if (!coolChat.equals("-exit")) {
                    bw.newLine();
                    bw.append(coolChat);
                    //out.writeUTF(coolChat); // Отправляем сообщение на сервер
                    server.broadcast(coolChat);
                    readMessage();
                } else {
                    out.writeUTF("-exit"); // Сообщаем серверу о выходе
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
            StringBuilder stringBuilder = new StringBuilder();
            int i = 1;
            while ((str = br.readLine()) != null && i <= 5) {
                System.out.println(str);
                stringBuilder.append(str).append("\n");
                //server.broadcast(str);
                i++;
            }
            sendMessage(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {//Чтобы нам кто-то прислал сообщения!
            out.writeUTF(message);
            //System.out.println("Client, please enter the message!");
            //while (true) {
        /*
                String coolChat = consoleReader.readLine();
                out.writeUTF(coolChat);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                    bw.newLine();
                    bw.append(coolChat);
                } catch (IOException e) {

                    throw new RuntimeException("SWW",e);
                }
         */
    }

    public void checkAuth (String loginInfo) throws IOException {
        while (true) {
            if (loginInfo.startsWith("-auth")) {
                System.out.println("We are in checkAuth 1");
                // -auth l1 p1
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
                        //sendMessage("We've done it!!!");
                        //showHistory();
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
                }
            }
        }
    }
}
