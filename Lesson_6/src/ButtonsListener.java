import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonsListener implements ActionListener {
    private final JTextField inputField;
    private final JTextArea chatTextArea;
    public final StringBuilder sb = new StringBuilder();
    private final Client client;

    public ButtonsListener(JTextField inputField, JTextArea chatTextArea, Client client) {
        this.inputField = inputField;
        this.chatTextArea = chatTextArea;
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
    }
}
