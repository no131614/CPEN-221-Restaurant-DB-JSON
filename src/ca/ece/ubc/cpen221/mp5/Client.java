package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.*;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null

    /**
     * Make a FibonacciClient and connect it to a server running on hostname at
     * the specified port.
     * 
     * @throws IOException
     *             if can't connect
     */
    public Client(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Send a request to the server. Requires this is \"open\".
     * 
     * @param x
     *            to find Fibonacci(x)
     * @throws IOException
     *             if network or server failure
     */
    public void sendRequest(String x) throws IOException {
        out.print(x + "\n");
        out.flush(); // important! make sure x actually gets sent
    }

    /**
     * Get a reply from the next request that was submitted. Requires this is
     * "open".
     * 
     * @return the requested Fibonacci number
     * @throws IOException
     *             if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }

        try {

            return reply;
        } catch (Exception e) {
            throw new IOException("misformatted reply: " + reply);
        }
    }

    /**
     * Closes the client's connection to the server. This client is now
     * "closed". Requires this is "open".
     * 
     * @throws IOException
     *             if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    private static final int N = 100;

    /**
     * Use a FibonacciServer to find the first N Fibonacci numbers.
     */
    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 1234);// FibonacciServer.FIBONACCI_PORT);

            String str =" ";
            // send the requests
           //  str = "(category(\"Chinese\")&&price(1..3))||(name(\"Sun Hong Kong Restaurant\")&&in(\"Telegraph Ave\"))";

            //client.sendRequest(str);

            // collect the replies
            String y = " ";

           // y = client.getReply();

            //System.out.println(y);

            // send the requests
            //str = "randomReview(\"Mandarin House\")";
            //client.sendRequest(str);

            // collect the replies
            //y = client.getReply();
            //System.out.println(y);
            
         // send the requests
            //str = "getRestaurant(\"5fneYCWLhgBZQUcNPOch-w\")";
            //client.sendRequest(str);

            // collect the replies
            //y = client.getReply();
            //System.out.println(y);
            
         // send the requests
            str = "addRestaurant(\"{\"open\": true, \"url\": \"http://www.yelp.com/biz/mandarin-house-berkeley\", \"business_id\": \"5fneYCWLhgBZQUcNPOch-q\", \"price\": 2}\")";
            client.sendRequest(str);

            // collect the replies
            y = client.getReply();
            System.out.println(y);

         // send the requests
            str = "getRestaurant(\"5fneYCWLhgBZQUcNPOch-q\")";
            client.sendRequest(str);

            // collect the replies
            y = client.getReply();
            System.out.println(y);
            
            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
