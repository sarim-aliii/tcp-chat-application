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

                ChatMessage message = CommandParser.parse(text, client.getUsername());
                client.send(message);

                if(message.getType() == MessageType.LOGOUT){
                    client.disconnect();
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println("Disconnected.");
        }
    }
}
