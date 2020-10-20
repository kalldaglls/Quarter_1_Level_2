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

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authenticate();
                    showHistory();
                    writeToHistory();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void authenticate()  {
        System.out.println("Client auth is on going...");
        Thread secondThread = new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                try {
                    Thread.sleep(20000);
                    // if (in.readUTF().isBlank()) {
                    closeConnection();
                    socket.close();
                    //}
                    System.out.println("Client couldn't authorize in time!");
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(Thread.currentThread() + " " + " is dead!");
                }

            }
        });
        Thread firstThread = new Thread(new Runnable() {

                @Override
                public synchronized void run() {
                    String loginInfo;
                    try {
                        loginInfo = in.readUTF();
                        checkAuth(loginInfo);
                        secondThread.interrupt();
                    } catch (IOException e) {
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

    public void readMessage() throws IOException {
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
            if (message.equalsIgnoreCase("-exit")) {
                return;
            }

             */

            server.broadcast(formatterMessage);
        }
    }

    public void writeToHistory () {
        while (true) {
            System.out.println("Client, please enter the message!");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt", true))) {
                String coolChat = consoleReader.readLine();
                    // out.writeUTF(coolChat);
                if (!coolChat.equals("-exit")) {
                    bw.newLine();
                    bw.append(coolChat);//return;
                }
                else break;
            } catch (IOException e) {
                throw new RuntimeException("SWW",e);
            }
        }
    }

    public void showHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\JAVA\\IdeaProjects\\Quarter_1_Level_2_\\Lesson_7\\Local_History.txt"))) {
            String str;
            int i = 1;
            while ((str = br.readLine()) != null && i <= 100) {
                System.out.println(str);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
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
                        sendMessage("We've done it!!!");
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
