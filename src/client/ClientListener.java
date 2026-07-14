package client;

import common.ChatMessage;
import ui.ChatFrame;
import javax.swing.SwingUtilities;
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
        chatFrame
                .getChatPanel()
                .appendMessage("[PRIVATE] " + message.getSender() + ": " + message.getMessage());
    }

    private void showSystem(ChatMessage message){
        chatFrame
                .getChatPanel()
                .appendMessage("[SERVER] " + message.getMessage());
    }

    private void showUserList(ChatMessage message){
        chatFrame.getUserPanel()
                .setUsers(message.getUsers());
    }
}