package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Waiting...");

        final var socket = serverSocket.accept();
        System.out.println("Client connected!");

        var is = socket.getInputStream();
        var os = socket.getOutputStream();

        final Scanner scanner = new Scanner(is);
        final PrintStream printStream = new PrintStream(os);

        printStream.println("welcome");
        printStream.println("enter bye to exit");
        String s;
        while(!(s = scanner.nextLine()).equals("bye")) {
            printStream.println("echo: " + s);
        }
        socket.close();
        System.out.println("exit...");
    }
}
