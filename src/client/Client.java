package client;

import common.ChatMessage;
import common.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 2020;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            Socket socket = new Socket(SERVER, PORT);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            // Send LOGIN message
            ChatMessage login = new ChatMessage(
                    MessageType.LOGIN,
                    username,
                    null,
                    username + " joined the chat."
            );
            output.writeObject(login);
            output.flush();

            while(true){
                System.out.print(username + ": ");
                String text = sc.nextLine();

                if(text.equalsIgnoreCase("exit")){
                    ChatMessage logout = new ChatMessage(
                            MessageType.LOGOUT,
                            username,
                            null,
                            username + " left the chat."
                    );
                    output.writeObject(logout);
                    output.flush();

                    break;
                }

                ChatMessage message = new ChatMessage(
                        MessageType.MESSAGE,
                        username,
                        null,
                        text
                );
                output.writeObject(message);
                output.flush();

                ChatMessage reply = (ChatMessage) input.readObject();
                System.out.println(reply.getSender() + ": " + reply.getMessage());
            }

            sc.close();
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}