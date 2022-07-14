package simple_chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    private final Socket socket;
    private Scanner scanner;
    private PrintStream printStream;
    private String name;
    private Server server;

    public Client(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            scanner = new Scanner(is);
            printStream = new PrintStream(os);
        } catch (IOException e) {
            System.out.println("Client catch exception");
        }
    }

    boolean valid() {
        return (scanner != null) && (printStream != null);
    }

    public boolean isNameValid() {
        return name != null;
    }

    public String getName() {
        return name;
    }

    void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("socket close failed");
        }
    }

    void printMessage(String message) {
        printStream.println(message);
    }

    @Override
    public void run() {

        if (!valid()) {
            System.out.println("failed: !valid");
            return;
        }

        String s;

        printStream.println("enter you name...");
        while (true) {
            s = scanner.nextLine();
            if (s.isEmpty()) {
                printStream.println("fail: name is empty.");
            } else if (!server.isNameFree(s)) {
                printStream.println("fail: name-already taken.");
            } else {
                break;
            }
        }

        name = s;
        server.enterChat(this);

        printStream.println("welcome \"" + name + "\"!");
        printStream.println("for exit type \"exit\"");
        try {
            while (true) {
                s = scanner.nextLine();
                if (s.equals("exit")) {
                    break;
                }
                server.printMessage(this, name + ": " + s);
            }
        } catch (Exception e) {
            /*lost connection*/
            System.out.println("\"client(" + name + "): lost connection...");
        }
        close();
        server.unregister(this);
        System.out.println("client(" + name + "): exit...");
    }

    @Override
    public String toString() {
        return "Client{\"" + name + "\"}";
    }
}
