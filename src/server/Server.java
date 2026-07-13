package server;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 2020;

    public static void main(String[] args) {
        System.out.println("Starting server...");

        try{
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("Server started.");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept(); // accept() : program pauses until a client connects

            System.out.println("Client connectd!");

            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}