package simple_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

class Server {
    private ServerSocket serverSocket;
    private List<Client> clients = new LinkedList<>();

    Server() {
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println("cant create ServerSocket");
        }
    }

    void printMessage(Client sender, String message) {
        for (var client : clients) {
            if (!client.equals(sender)) {
                client.printMessage(message);
            }
        }
    }

    boolean isNameFree(String name) {
        for (var client : clients) {
            if (client.isNameValid() && client.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    void enterChat(Client client)
    {
        printMessage(client, "< \"" + client.getName() + "\" enter the chat >");
    }

    void unregister(Client client)
    {
        final var res = clients.remove(client);
        if (res){
            printMessage(client, "< \"" + client.getName() + "\" exit the chat >");
            System.out.println("server: all clients = " + clients.toString());
        }
    }

    void run() {
        if (serverSocket == null)
            return;
        /**/
        System.out.println("server: waiting for clients...");
        while (true) {
            try {
                final var socket = serverSocket.accept();
                System.out.println("Client connected!");
                Client newClient = new Client(this, socket);
                clients.add(newClient);
                new Thread(newClient).start();
            } catch (IOException e) {
                System.out.println("accept throws exception: " + e.getMessage());
            }
        }
    }
}
