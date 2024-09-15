import javax.swing.*;
import java.awt.*;

public class ChatForm extends JFrame {
    public ChatForm(String title) {
        setTitle(title);
        setBounds(new Rectangle(0,0,300,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(100, 226, 47));

        setLayout(new GridLayout(2,1));

        JPanel mainScreen = new JPanel();
        JPanel bottomPanel = new JPanel();
        mainScreen.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(1,1));
        //bottomPanel.setLayout(new GridLayout(1,1));

        JTextArea chatTextArea = new JTextArea();
        //chatTextArea.setPreferredSize(new Dimension(300, 250));
        //chatTextArea.setLayout(new BorderLayout());
        chatTextArea.setEditable(false);
        mainScreen.add(chatTextArea, BorderLayout.CENTER);

        var inputTextField = new JTextField();
        bottomPanel.add(inputTextField);

        JButton sendButton = new JButton("Send");
        //sendButton.setPreferredSize(new Dimension(80, 30));
        bottomPanel.add(sendButton);

        ButtonsListener buttonsListener = new ButtonsListener(inputTextField, chatTextArea);

        sendButton.addActionListener(buttonsListener);

        add(mainScreen);
        add(bottomPanel);

        setVisible(true);
    }
}
