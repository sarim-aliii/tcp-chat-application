package server;

import common.ChatMessage;

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
                try{
                    client.sendMessage(message);
                } catch(Exception ignored){}
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
}
