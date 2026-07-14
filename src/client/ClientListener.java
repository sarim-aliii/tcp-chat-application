package client;

import common.ChatMessage;
import ui.ChatFrame;
import javax.swing.SwingUtilities;

import ui.UIConstants;
import utils.DateTimeUtils;

public class ClientListener implements Runnable {
    private final ChatClient client;
    private final ChatFrame chatFrame;

    public ClientListener(ChatClient client, ChatFrame chatFrame) {
        this.client = client;
        this.chatFrame = chatFrame;
    }

    @Override
    public void run() {
        try{
            while(true){
                ChatMessage message = client.receive();

                SwingUtilities.invokeLater(() -> {
                    switch(message.getType()){
                        case MESSAGE -> showMessage(message);
                        case PRIVATE -> showPrivate(message);
                        case SYSTEM -> showSystem(message);
                        case USER_LIST -> showUserList(message);

                        default -> { }
                    }
                });
            }
        }
        catch(Exception e){
            System.out.println("Connection closed.");
            SwingUtilities.invokeLater(() -> {
                chatFrame.getChatPanel().appendMessage("[SYSTEM] Disconnected from server.");
                // chatFrame.getInputPanel().disableInput();
            });
        }
    }

    private void showMessage(ChatMessage message){
        String formatted =
                "[" +
                        DateTimeUtils.format(message.getTimestamp())
                + "] "
                + message.getSender()
                + ": "
                + message.getMessage();

        chatFrame.getChatPanel().appendMessage(formatted);
    }

    private void showPrivate(ChatMessage message){
        String formatted = "[PRIVATE] " + message.getSender() + ": " + message.getMessage();

        chatFrame
                .getChatPanel()
                .appendMessage(formatted, UIConstants.PRIVATE_COLOR);
    }

    private void showSystem(ChatMessage message){
        String formatted = "[SERVER] " + message.getMessage();

        chatFrame
                .getChatPanel()
                .appendMessage(formatted, UIConstants.SYSTEM_COLOR);
    }

    private void showUserList(ChatMessage message){
        chatFrame
                .getUserPanel().
                setUsers(message.getUsers());
    }
}