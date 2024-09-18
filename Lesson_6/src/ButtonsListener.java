import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonsListener implements ActionListener {
    private final JTextField inputField;
    private final Client client;

    public ButtonsListener(JTextField inputField, Client client) {
        this.inputField = inputField;
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                    String message = inputField.getText();
                    if (!message.trim().isBlank()) {
                        try {
                            client.getOut().writeUTF(message);
                            inputField.setText("");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = client.getInStream().readUTF();
                        System.out.println(message);
                        SwingUtilities.invokeLater(() -> client.getChatArea().append(message + "\n"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}