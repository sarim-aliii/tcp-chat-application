package server;

import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class Server {
    private static final int PORT = 2020;

    public static void main(String[] args) {
        System.out.println("Starting server...");

        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started.");

            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept(); // accept() : program pauses until a client connects
            System.out.println("Client Connected!");

            // socket.getInputStream() : gives raw bytes
            // InputStreamReader : convert bytes into chars
            // BufferedReader : Lets us easily read complete lines
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // The true parameter enables auto-flush, meaning messages are sent immediately.
            //Without it, messages might stay in memory until the buffer fills.
            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(), true
            );

            while(true){
                String message = reader.readLine();

                if(message == null) break;
                if(message.equalsIgnoreCase("exit")){
                    writer.println("Goodbye!!!");
                    break;
                }

                System.out.println("Client: " + message);

                writer.println("[" + LocalTime.now() + "] Received: " + message);
            }

            reader.close();
            writer.close();
            socket.close();
            serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}