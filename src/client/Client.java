package client;

import java.util.Scanner;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 2020;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = sc.nextLine();

        try {
            ChatClient chatClient = new ChatClient(SERVER, PORT, username);

            Thread listener = new Thread(new ClientListener(chatClient));

            Thread sender = new Thread(new ClientSender(chatClient, sc));

            listener.start();
            sender.start();

            listener.join();
            sender.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}