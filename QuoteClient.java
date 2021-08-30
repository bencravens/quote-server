// Based on the tutorial at 
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 

import java.net.*;
import java.io.*;

public class QuoteClient {

    public static void main(String[] args) {
    
        String hostname = "localhost";
        int port = 5000;

        try (Socket sock = new Socket(hostname, port)) {
            System.out.println("Connected to " + hostname + " on port " + port);
            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String quote = reader.readLine();

            System.out.println(quote);
        } catch (Exception e) {
            System.out.println(e);
        }   
    }   
}
