package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: Implement a server that will instantiate a database, 
// process queries concurrently, etc.

public class RestaurantDBServer {

    private ServerSocket serverSocket;

    /**
     * Constructor for creating a server Restaurant DBServer that handles
     * multiple clients request for database search and editing.
     * 
     * @param port
     *            number of the server
     * @param filename
     *            for the Restaurant database (in JSON format)
     * @param filename
     *            for the User database (in JSON format)
     * @param filename
     *            for the Review database (in JSON format)
     * @throws IOException
     */
    RestaurantDB DB;
    
    public RestaurantDBServer(int port, String filename1, String filename2, String filename3) throws IOException {

        serverSocket = new ServerSocket(port);
        this.DB = new RestaurantDB(filename1, filename2, filename3);
    }

    /**
     * Run the server, listening for connections and handling them.
     * 
     * @throws IOException
     *             if the main server socket is broken
     */
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            final Socket socket = serverSocket.accept();
            // create a new thread to handle that client
            Thread handler = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            handle(socket);
                        } finally {
                            socket.close();
                        }
                    } catch (IOException ioe) {
                        // this exception wouldn't terminate serve(),
                        // since we're now on a different thread, but
                        // we still need to handle it
                        ioe.printStackTrace();
                    }
                }
            });
            // start the thread
            handler.start();
        }
    }

    /**
     * Handle one client connection. Returns when client disconnects.
     * 
     * @param socket
     *            socket where client is connected
     * @throws IOException
     *             if connection encounters an error
     */
    private synchronized void handle(Socket socket) throws IOException {
        System.err.println("client connected");

        // get the socket's input stream, and wrap converters around it
        // that convert it from a byte stream to a character stream,
        // and that buffer it so that we can read a line at a time
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // similarly, wrap character=>bytestream converter around the
        // socket output stream, and wrap a PrintWriter around that so
        // that we have more convenient ways to write Java primitive
        // types to it.
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        try {
            // each request is a single line containing a query or other request
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println("request: " + line);
                try {
                    String patternS = ".*randomReview.*";
                    Pattern pattern = Pattern.compile(patternS);
                    Matcher matcher = pattern.matcher(line);
                    String inside = RestaurantDBServer.takeInsideParen(line);

                    if (matcher.matches()) {
                        out.println(DB.randomReview(inside));
                    }

                    else {

                        patternS = ".*getRestaurant.*";
                        pattern = Pattern.compile(patternS);
                        matcher = pattern.matcher(line);
                        inside = RestaurantDBServer.takeInsideParen(line);

                        if (matcher.matches()) {
                            out.println(DB.getRestaurant(inside));
                        }

                        else {

                            patternS = ".*addRestaurant.*";
                            pattern = Pattern.compile(patternS);
                            matcher = pattern.matcher(line);

                            if (matcher.matches()) {
                                inside = RestaurantDBServer.takeInsideParen2(line);
                                System.err.println(inside);
                                out.println(DB.addRestaurant(inside));
                            }

                            else {

                                patternS = ".*addUser.*";
                                pattern = Pattern.compile(patternS);
                                matcher = pattern.matcher(line);

                                if (matcher.matches()) {
                                    inside = RestaurantDBServer.takeInsideParen2(line);
                                    System.err.println(inside);
                                    out.println(DB.addUser(inside));
                                }

                                else {

                                    patternS = ".*addReview.*";
                                    pattern = Pattern.compile(patternS);
                                    matcher = pattern.matcher(line);

                                    if (matcher.matches()) {
                                        inside = RestaurantDBServer.takeInsideParen2(line);
                                        System.err.println(inside);
                                        out.println(DB.addReview(inside));
                                    }

                                    else {

                                        try {
                                            Set<Restaurant> set = DB.query(line);
                                            String str = " ";
                                            for (Restaurant rest : set) {
                                                System.out.println("-----------");
                                                System.out.println(rest.getJSONString());
                                                str = str.concat(rest.getJSONString());

                                            }
                                            out.println(str);
                                            out.flush();
                                        } catch (Exception e) {
                                            out.println("{\"ERROR\": \"INCORRECT SYNTAX\"}");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // complain about ill-formatted request
                    System.err.println("reply: err");
                    out.print("{\"ERROR\": \"INCORRECT SYNTAX\"}");
                }

            }
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Convert a given request to a suitable string for searching Requires str
     * not null
     * 
     * @param the
     *            given request
     * @return suitable String inside the request parentheses
     */
    public static String takeInsideParen(String str) {
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str = matcher.group(1);
        }
        return str;
    }

    /**
     * Convert a given request to a suitable string for searching Requires str
     * not null (the requests are all adding instruction to database)
     * 
     * @param the
     *            given request
     * @return suitable String inside the request parentheses
     */
    public static String takeInsideParen2(String str) {

        int index1 = str.indexOf("\"");
        int index2 = str.lastIndexOf("\"");

        str = str.substring(index1 + 1, index2);

        return str;
    }

    /**
     * Start a RestaurantDBServer running
     */
    public static void main(String[] args) {
        try {
            String str = args[0];
            int port = Integer.parseInt(str);
            String file1 = args[1];
            String file2 = args[2];
            String file3 = args[3];
            RestaurantDBServer server = new RestaurantDBServer(port, file1, file2, file3);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
