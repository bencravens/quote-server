// Based on the tutorial at
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 
// and https://www.geeksforgeeks.org/multithreaded-servers-in-java/#:~:text=Multithreaded%20Server%3A%20A%20server%20having,clients%20at%20the%20same%20time.
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.nio.file.*;
import java.io.File.*;
import java.util.Random;

// Spawn a thread and send quote to client
public class ClientHandler implements Runnable {
    public final Socket clientSocket;

    //constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    //run is called when a new thread is made
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // create inputstream to get input from client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // create outputstream to send quote to client
            OutputStream output = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            // if the client sends us something, send them back a quote
            String line;
            while ((line = in.readLine()) != null) {
                System.out.printf("Client: %s\n",line);
                String quote = readQuote();
                writer.println(quote);
            }

        } catch (Exception e) {
            System.out.println("Error getting output stream");
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
