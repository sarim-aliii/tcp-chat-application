package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 2020;

    public static void main(String[] args) {
        System.out.println("Connecting...");
        Scanner sc = new Scanner(System.in);

        try{
            Socket socket = new Socket(SERVER, PORT);
            System.out.println("Connected to Server!");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(socket.getOutputStream(), true);

            while(true){
                System.out.println("You: ");

                String message = sc.nextLine();

                writer.println(message);

                if(message.equalsIgnoreCase("exit")) break;

                String reply = reader.readLine();
                System.out.println("Server: " + reply);
            }

            reader.close();
            writer.close();
            sc.close();
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}