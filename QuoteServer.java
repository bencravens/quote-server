// Based on the tutorial at 
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.nio.file.*;
import java.io.File.*;
import java.util.Random;

class QuoteServer {
    public static void main(String[] args) {

        System.out.println("Initializing server");
        int port = 5000;

        try {
            ServerSocket serversock = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket sock = serversock.accept();
                System.out.println("New client connected");
                OutputStream output = sock.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                String quote = readQuote();
                writer.println(quote);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String readQuote() {
        Random random = new Random();
        long num_lines = 0;
        Path path = Paths.get("quotes.txt");
        try {
            num_lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // pick a random quote
        int randomInteger = random.nextInt((int)num_lines);
        try {
            String quote = Files.readAllLines(path).get(randomInteger);
            return quote;
        } catch (Exception e) {
            System.out.println(e);
            return "Failed to grab quote.";
        }
    }
}
