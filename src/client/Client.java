package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 2020;
    private static String username;

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

            System.out.print("Enter username: ");
            username = sc.nextLine();

            while(true){
                System.out.print(username + ": ");

                String message = sc.nextLine();
                if(message.equalsIgnoreCase("exit")){
                    writer.println("exit");
                    break;
                }

                writer.println(username + ": " + message);

                String reply = reader.readLine();
                System.out.println(reply);
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