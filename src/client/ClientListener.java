package client;

import common.ChatMessage;
import ui.ChatFrame;
import javax.swing.SwingUtilities;

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
        chatFrame
                .getChatPanel()
                .appendMessage(message.getSender() + ": " + message.getMessage());
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
}