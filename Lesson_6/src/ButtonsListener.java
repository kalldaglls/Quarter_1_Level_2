import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsListener implements ActionListener {
   // private final JButton sendButton;
    private final JTextField inputField;
    private final JTextArea chatTextArea;
    public final StringBuilder sb = new StringBuilder();

    public ButtonsListener(JTextField inputField, JTextArea chatTextArea) {
        //this.sendButton = sendButton;
        this.inputField = inputField;
        this.chatTextArea = chatTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JButton jButton = (JButton) e.getSource();
        sb.append(inputField.getText());
        String search = sb.toString();
        chatTextArea.append(" " + search + "\n");
        //chatTextArea.setText(search + "\n");
        sb.setLength(0);
        inputField.setText("");
    }
}
