import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
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
                    //writeHistory();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        }).start();
    }

    public void authenticate()  {
        System.out.println("Client auth is on going...");
            Thread firstThread = new Thread(new Runnable() {

                @Override
                public synchronized void run() {
                    String loginInfo;
                    try {
                        loginInfo = in.readUTF();
                        checkAuth(loginInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread secondThread = new Thread(new Runnable() {

                @Override
                public synchronized void run() {
                    try {
                        Thread.sleep(120000);
                       // if (in.readUTF().isBlank()) {
                            closeConnection();
                            socket.close();
                        //}
                        System.out.println("Client couldn't authorize in time!");
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            firstThread.start();
            secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
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



    public void closeConnection() {
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

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            String formatterMessage = String.format("Message from %s: %s", name, message);
            System.out.println(formatterMessage);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                bw.newLine();
                bw.append(formatterMessage);
            } catch (IOException e) {

                throw new RuntimeException("SWW",e);
            }
            if (message.equalsIgnoreCase("-exit")) {
                return;
            }

            server.broadcast(formatterMessage);
        }
    }

    public void writeHistory () {
        while (true) {
            System.out.println("Client, please enter the message!");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                String coolChat = consoleReader.readLine();
                out.writeUTF(coolChat);
                bw.newLine();
                bw.append(coolChat);
                return;
            } catch (IOException e) {
                throw new RuntimeException("SWW",e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            System.out.println("Client, please enter the message!");
            while (true) {
                String coolChat = consoleReader.readLine();
                out.writeUTF(coolChat);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                    bw.newLine();
                    bw.append(coolChat);
                } catch (IOException e) {

                    throw new RuntimeException("SWW",e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAuth (String loginInfo) {
        while (true) {
            if (loginInfo.startsWith("-auth")) {
                // -auth l1 p1
                String[] splittedLoginInfo = loginInfo.split("\\s");
                AuthenticationService.Client maybeClient = server.getAuthenticationService()
                        .findByLoginAndPassword(
                                splittedLoginInfo[1],
                                splittedLoginInfo[2]
                        );
                if (maybeClient != null) {
                    if (!server.checkLogin(maybeClient.getName())) {
                        sendMessage("status: authok");
                        name = maybeClient.getName();
                        server.broadcast(String.format("%s came in", name));
                        System.out.println("Client auth completed");
                        server.subscribe(this);
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
