import java.io.*;
import java.net.Socket;

public class ClientApplicationOne {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            //out.writeUTF("-auth l1 p1 u1");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            //out.writeUTF("-auth l1 p1 u1");
                            //if(socket.isClosed()) return;
//                            while (in.readUTF().isBlank()) {
//                                return;
////                                message = in.readUTF();
////                                System.out.println(message);
////                                if (message.contains("Incorrect credentials")) {
////                                    out.writeUTF("-auth l1 p1");
////                                }
//                            }
                            //if(in.readUTF().isEmpty()) {
                            String message = in.readUTF();
                            System.out.println(message);
                            out.writeUTF(consoleReader.readLine());
                            // }
                            //String message = in.readUTF();
                            //System.out.println(message);
                         if (message.contains("Incorrect credentials")) {
                               out.writeUTF("-auth l1 p1");
                           }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }

                    }
                }
            }).start();

   //         out.writeUTF("-exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }