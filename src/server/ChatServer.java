package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private final int port;
    private final ClientManager clientManager;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    private volatile boolean running = true;

    public ChatServer(int port){
        this.port = port;
        this.clientManager = new ClientManager();
    }

    public void start(){
        System.out.println("Starting server...");

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started on port " + port);

            while(running){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected : " + socket.getInetAddress());

                ClientHandler handler = new ClientHandler(socket, clientManager);
                clientManager.addClient(handler);
                threadPool.execute(handler);
            }
        }
        catch (IOException e){
            if(running) e.printStackTrace();
            else System.out.println("Server shutting down gracefully.");
        }
    }

    public void stop(){
        this.running = false;
    }
}
