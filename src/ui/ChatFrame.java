package ui;

import client.ChatClient;
import javax.swing.*;

public class ChatFrame extends JFrame{
    private final ChatClient client;

    public ChatFrame(ChatClient client) {
        this.client = client;

        setTitle(UIConstants.APP_TITLE);
        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel(
                "Connected as: " + client.getUsername(),
                SwingConstants.CENTER
        );
        add(label);

        setVisible(true);
    }
}
