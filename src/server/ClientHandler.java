package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run(){
        try{
            while(true){
                String message = reader.readLine();

                if(message == null) break;
                if(message.equalsIgnoreCase("exit")){
                    writer.println("Goodbye!!!");
                    break;
                }

                System.out.println(message);

                Server.broadcast(message, this);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            Server.removeClient(this);

            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        writer.println(message);
    }
}
