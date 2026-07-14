package client;

import common.ChatMessage;

public class ClientListener implements Runnable {
    private final ChatClient client;

    public ClientListener(ChatClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ChatMessage message = client.receive();

                System.out.println();
                System.out.println(message.getSender() + ": " + message.getMessage());
                System.out.print(client.getUsername() + ": ");
            }

        }
        catch (Exception e) {
            System.out.println("Connection closed.");
        }
    }
}