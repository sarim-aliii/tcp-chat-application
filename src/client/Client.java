package client;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER = "localhost";
    private static final int PORT = 2020;

    public static void main(String[] args) {
        System.out.println("Connecting...");

        try{
            Socket socket = new Socket(SERVER, PORT);

            System.out.println("Connected to Server!");

            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}