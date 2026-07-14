package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private final int port;
    private final ClientManager clientManager;

    public ChatServer(int port){
        this.port = port;
        this.clientManager = new ClientManager();
    }

    public void start(){
        System.out.println("Starting server...");

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started on port " + port);

            while (true){
                Socket socket = serverSocket.accept();

                System.out.println("New client connected : " + socket.getInetAddress());

                ClientHandler handler = new ClientHandler(socket, clientManager);

                clientManager.addClient(handler);

                new Thread(handler).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
