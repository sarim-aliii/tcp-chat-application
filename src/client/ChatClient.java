package client;

import common.ChatMessage;
import common.MessageType;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String username;

    public ChatClient(String host, int port, String username) throws IOException{
        this.username = username;
        socket = new Socket(host, port);

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        send(new ChatMessage(
                MessageType.LOGIN,
                username,
                null,
                username + " joined the chat."
        ));
    }

    public synchronized void send(ChatMessage message) throws IOException{
        output.writeObject(message);
        output.flush();
    }

    public ChatMessage receive() throws IOException, ClassNotFoundException{
        return (ChatMessage) input.readObject();
    }

    public String getUsername() {
        return username;
    }

    public void disconnect(){
        try{
            send(new ChatMessage(
                    MessageType.LOGOUT,
                    username,
                    null,
                    username + " left the chat."
            ));
        }
        catch (Exception ignored){}

        try {
            input.close();
        } catch (Exception ignored) {}

        try {
            output.close();
        } catch (Exception ignored) {}

        try {
            socket.close();
        } catch (Exception ignored) {}
    }
}
