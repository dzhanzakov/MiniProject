package MiniProject;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide extends Thread {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1900);
            System.out.println("Server created");
            int id = 0;
            while(true){
                Socket socket = server.accept();
                id++;
                System.out.println("client " + id + " connected");

                ClientHandler clientHandler = new ClientHandler(socket, id);
                clientHandler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}