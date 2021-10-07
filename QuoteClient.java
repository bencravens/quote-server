// Based on the tutorial at 
// www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip 

import java.net.*;
import java.io.*;
import java.util.*;

public class QuoteClient {

    public static void main(String[] args) {
        //invoke like java QuoteClient 192.168.0.0 10 5000
        //to read 10 quotes from server ip 192.168.0.0 via port 5000
        String hostname = args[0];
        int numquotes = Integer.parseInt(args[1]);
        int port = Integer.parseInt(args[2]);

        for (int i=0; i<numquotes; i++) {        
            //connect to the server
            try (Socket sock = new Socket(hostname, port)) {
                System.out.println("Connected to " + hostname + " on port " + port);
                //reading from server
                InputStream input = sock.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                //display server reply
                String quote = reader.readLine();
                System.out.println(quote);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
