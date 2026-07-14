package server;

import common.ChatMessage;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String username;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                ChatMessage message =
                        (ChatMessage) input.readObject();

                switch (message.getType()) {
                    case LOGIN:
                        username = message.getSender();
                        System.out.println(username + " joined.");
                        Server.broadcast(message, this);
                        break;

                    case MESSAGE:
                        System.out.println(message);
                        Server.broadcast(message, this);
                        break;

                    case PRIVATE:
                        break;

                    case USERS:
                        break;

                    case LOGOUT:
                        System.out.println(username + " left.");
                        return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(username + " disconnected.");
        } finally {
            Server.removeClient(this);

            try {
                if (input != null) input.close();
                if (output != null) output.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(ChatMessage message){
        try{
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
