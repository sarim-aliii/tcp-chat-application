package client;

import common.ChatMessage;
import common.MessageType;

import java.util.Scanner;

public class ClientSender implements Runnable{
    private final ChatClient client;
    private final Scanner sc;

    public ClientSender(ChatClient client, Scanner sc) {
        this.client = client;
        this.sc = sc;
    }

    @Override
    public void run(){
        try{
            while(true){
                System.out.print(client.getUsername() + ": ");

                String text = sc.nextLine();
                if(text.equalsIgnoreCase("exit")){
                    client.disconnect();
                    break;
                }

                ChatMessage message = new ChatMessage(
                        MessageType.MESSAGE,
                        client.getUsername(),
                        null,
                        text
                );
                client.send(message);
            }
        }
        catch(Exception e){
            System.out.println("Disconnected.");
        }
    }
}
