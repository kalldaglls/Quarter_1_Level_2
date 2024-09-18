import javax.swing.*;
import java.awt.*;

public class ChatForm extends JFrame {
    private final JTextField inputTextField;
    private final JTextArea chatTextArea;
    private Client client;

    public static void main(String[] args) {
        new ChatForm("YOTA");
    }

    public ChatForm(String title) {
        chatTextArea = new JTextArea();
        client = new Client(chatTextArea);

        setTitle(title);
        setBounds(new Rectangle(0,0,300,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(100, 226, 47));

        setLayout(new GridLayout(2,1));

        JPanel mainScreen = new JPanel();
        JPanel bottomPanel = new JPanel();
        mainScreen.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(1,1));

        chatTextArea.setEditable(false);
        mainScreen.add(chatTextArea, BorderLayout.CENTER);

        inputTextField = new JTextField();
        bottomPanel.add(inputTextField);

        JButton sendButton = new JButton("Send");

        bottomPanel.add(sendButton);

        ButtonsListener buttonsListener = new ButtonsListener(inputTextField, client);

        sendButton.addActionListener(buttonsListener);

        add(mainScreen);
        add(bottomPanel);

        setVisible(true);
    }
}