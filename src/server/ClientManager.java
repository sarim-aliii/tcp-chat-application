package server;

import common.ChatMessage;
import common.MessageType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientManager {
    private final List<ClientHandler> clients =
            Collections.synchronizedList(new ArrayList<>());


    public void addClient(ClientHandler client){
        clients.add(client);
    }

    public void removeClient(ClientHandler client){
        clients.remove(client);
    }

    public void broadcast(ChatMessage message){
        synchronized (clients){
            for(ClientHandler client : clients){
                client.sendMessage(message);
            }
        }
    }

    public List<String> getOnlineUsers() {
        List<String> users = new ArrayList<>();

        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client.getUsername() != null)  users.add(client.getUsername());
            }
        }

        return users;
    }

    public boolean sendPrivate(ChatMessage message){
        ClientHandler receiver = findClient(message.getReceiver());

        if (receiver == null) return false;
        receiver.sendMessage(message);

        return true;
    }

    public ClientHandler findClient(String username){
        for(ClientHandler client : clients){
            if (username.equals(client.getUsername()))return client;
        }
        return null;
    }

    public boolean usernameExists(String username){
        for(ClientHandler client : clients){
            if (username.equals(client.getUsername())) return true;
        }
        return false;
    }

    public void broadcastUserList(){
        List<String> users = getOnlineUsers();

        ChatMessage message = new ChatMessage(
                MessageType.USER_LIST,
                users
        );

        broadcast(message);
    }
}