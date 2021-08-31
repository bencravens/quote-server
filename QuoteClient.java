// Based on the tutorial at 
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 

import java.net.*;
import java.io.*;
import java.util.*;

public class QuoteClient {

    public static void main(String[] args) {
    
        String hostname = "localhost";
        int port = 5000;

        //connect to the server
        try (Socket sock = new Socket(hostname, port)) {
            System.out.println("Connected to " + hostname + " on port " + port);
            //writing to server
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            //reading from server
            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            // scan for user input to send to server
            Scanner sc = new Scanner(System.in);
            String line = null;
            //if the user types "exit", quit.
            while (!"exit".equalsIgnoreCase(line)) {
                //read from user
                line = sc.nextLine();

                //send user input to server
                out.println(line);
                out.flush();

                //display server reply
                String quote = reader.readLine();
                System.out.println(quote);
            }
            //close scanner
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
}
