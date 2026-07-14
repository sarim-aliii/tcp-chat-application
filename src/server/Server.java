package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static final int PORT = 2020;
    private static final List<ClientHandler> clients =
            Collections.synchronizedList(new ArrayList<>());  // Without synchronization: ConcurrentModificationException

    public static void main(String[] args) {
        System.out.println("Starting server...");

        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started.");

            while(true){
                System.out.println("Waiting for client...");
                Socket socket = serverSocket.accept(); // accept() : program pauses until a client connects
                System.out.println("New Client Connected!");

                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);

                Thread thread = new Thread(handler);
                thread.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void broadcast(String message, ClientHandler sender){
        synchronized (clients){
            for(ClientHandler client : clients){
                if(client != sender) client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler client){
        clients.remove(client);
    }
}