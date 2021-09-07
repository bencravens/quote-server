// Based on the tutorial at 
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 
// and https://www.geeksforgeeks.org/multithreaded-servers-in-java/#:~:text=Multithreaded%20Server%3A%20A%20server%20having,clients%20at%20the%20same%20time.
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
                Socket client = serversock.accept();
                System.out.println("New client connected");
                //add new thread with connected client
                ClientHandler clientsock = new ClientHandler(client);
                new Thread(clientsock).start();
            }
        
        } catch (Exception e) {
            System.out.println("Error connecting to client");
            System.out.println(e);
        }
    }

    //Read a quote randomly from the quote file 
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

