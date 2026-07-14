package server;

public class Server {
    private static final int DEFAULT_PORT = 2020;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;

        if(args.length > 0){
            try{
                port = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e){
                System.out.println("Invalid port provided. Using default port: " + DEFAULT_PORT);
            }
        }

        ChatServer server = new ChatServer(port);
        server.start();
    }
}