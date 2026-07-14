package server;

import common.ChatMessage;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final ClientManager clientManager;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    private String username;

    private volatile boolean running = true;

    public ClientHandler(Socket socket, ClientManager clientManager) throws IOException {
        this.socket = socket;
        this.clientManager = clientManager;

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (running) {
                ChatMessage message =
                        (ChatMessage) input.readObject();

                switch (message.getType()) {
                    case LOGIN -> {
                        username = message.getSender();
                        System.out.println(username + " joined.");
                        clientManager.broadcast(message);
                        break;
                    }

                    case MESSAGE -> {
                        System.out.println(message);
                        clientManager.broadcast(message);
                        break;
                    }

                    case PRIVATE -> {
                        break;
                    }

                    case USERS -> {
                        break;
                    }

                    case LOGOUT ->{
                        System.out.println(username + " left.");
                        clientManager.broadcast(message);
                        return;
                    }

                    default -> {
                        System.out.println("Unknown message type");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(username + " disconnected.");
        } finally {
            clientManager.removeClient(this);
            closeResources();
        }
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(ChatMessage message){
        try{
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            System.out.println(username + " disconnected.");
        }
    }

    private void closeResources() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
