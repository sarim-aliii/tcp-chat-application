package ui;

import client.ChatClient;
import client.ClientListener;
import client.CommandParser;
import common.ChatMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatFrame extends JFrame{
    private final ChatClient client;

    private final ChatPanel chatPanel;
    private final UserPanel userPanel;
    private final InputPanel inputPanel;

    public ChatFrame(ChatClient client) {
        this.client = client;

        chatPanel = new ChatPanel();
        userPanel = new UserPanel();
        inputPanel = new InputPanel();

        inputPanel.getSendButton().addActionListener(e -> sendMessage());
        inputPanel.getMessageField().addActionListener(e -> sendMessage());

        initializeFrame();
        layoutComponents();

        ClientListener listener = new ClientListener(client, this);
        new Thread(listener, "ClientListener").start();

        setVisible(true);
    }

    private void layoutComponents() {
        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(chatPanel, BorderLayout.CENTER);
        centerPanel.add(userPanel, BorderLayout.EAST);

        setLayout(new BorderLayout());

        add(centerPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void initializeFrame() {
        setTitle(UIConstants.APP_TITLE + "-" + client.getUsername());

        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.disconnect();
                dispose();
                System.exit(0);
            }
        });
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public UserPanel getUserPanel() {
        return userPanel;
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }

    private void sendMessage(){
        String text = inputPanel.getMessage().trim();

        if (text.isEmpty()) return;

        ChatMessage message = CommandParser.parse(text, client.getUsername());

        try{
            client.send(message);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to send message."
            );
        }

        inputPanel.clear();
    }
}
