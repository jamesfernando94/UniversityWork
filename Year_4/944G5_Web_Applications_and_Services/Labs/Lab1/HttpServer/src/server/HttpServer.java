package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable {

    private static final int DEFAULTPORT = 8010;
    private ServerSocket socket;

    public HttpServer(int port) throws IOException {
        socket = new ServerSocket(port); // create a new server socket that will listen for new connection on port 8010 or whatever the user supplied
    }

    public HttpServer() throws IOException {
        this(DEFAULTPORT);
    }

    public static void main(String args[]) throws IOException {
        int port = DEFAULTPORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // create a new thread and pass a new HttpServer object which is runnable. Calling start will call the run method below.
        // check the javadoc for the Runnable interface if you cannot remember how it works.
        new Thread(new HttpServer(port)).start();
    }

    @Override
    public void run() {
        // this will run forever - can be only interrupted
        while (true) {
            Socket connected;
            try {
                // the following is a blocking call! accept will block until a new TCP connection (from a browser in this case) is opened.
                connected = socket.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
                // Parse what the client has to say to us
                try {
                    // parse is a static method - check the definition of the MyHttpRequest Class.
                    MyHttpRequest req = MyHttpRequest.parse(fromClient);
                    System.err.println(req);
                    // Return what it asks for
                    MyHttpResponder.respond(req, new DataOutputStream(connected.getOutputStream()));
                } catch (HttpParseException hpe) {
                    MyHttpResponder.reportError(hpe.getMessage(), new DataOutputStream(connected.getOutputStream()));
                }
                connected.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
