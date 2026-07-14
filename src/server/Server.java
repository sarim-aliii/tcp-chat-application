package server;

public class Server {
    private static final int PORT = 2020;

    public static void main(String[] args) {
        ChatServer server = new ChatServer(PORT);
        server.start();
    }
}