package server;

import common.ChatMessage;
import common.MessageType;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final ClientManager clientManager;

    private final ObjectInputStream input;
    private final ObjectOutputStream output;

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
                    case LOGIN -> handleLogin(message);
                    case MESSAGE -> handleMessage(message);
                    case PRIVATE -> handlePrivate(message);
                    case USERS -> handleUsers();
                    case LOGOUT -> {
                        handleLogout(message);
                        clientManager.broadcastUserList();
                    }

                    default -> System.out.println("Unknown message type");
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            String displayInfo = (username != null) ? username : "Unknown Client";
            System.out.println(displayInfo + " disconnected.");
        }
        finally {
            clientManager.removeClient(this);
            clientManager.broadcastUserList();
            closeResources();
        }
    }

    public String getUsername() {
        return username;
    }

    public synchronized void sendMessage(ChatMessage message){
        try{
            output.writeObject(message);
            output.flush();
        }
        catch (IOException e) {
            running = false;
            clientManager.removeClient(this);
            closeResources();
        }
    }

    private void handleLogin(ChatMessage message){
        if(clientManager.usernameExists(message.getSender())) {
            sendMessage(new ChatMessage(
                    MessageType.SYSTEM,
                    "Server",
                    message.getSender(),
                    "Username already exists."
            ));

            running = false;
            return;
        }

        username = message.getSender();
        System.out.println(username + " joined.");

        clientManager.broadcast(message);
        clientManager.broadcastUserList();
    }

    private void handleMessage(ChatMessage message){
        System.out.println(message);
        clientManager.broadcast(message);
    }

    private void handlePrivate(ChatMessage message){
        if (!clientManager.sendPrivate(message)) {
            sendMessage(new ChatMessage(
                    MessageType.SYSTEM,
                    "Server",
                    username,
                    "User " + message.getReceiver() + " is not online."
            ));
        }
    }

    private void handleUsers(){
        String users = String.join("\n", clientManager.getOnlineUsers());

        sendMessage(new ChatMessage(
                MessageType.SYSTEM,
                "Server",
                username,
                """
                Online Users:
                %s
                """.formatted(users)
        ));
    }
    private void handleLogout(ChatMessage message){
        System.out.println(username + " left.");
        clientManager.broadcast(message);

        running = false;
    }

    private void closeResources() {
        try {
            input.close();
        } catch (IOException ignored) {}

        try {
            output.close();
        } catch (IOException ignored) {}

        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
