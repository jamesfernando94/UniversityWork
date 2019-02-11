package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Base64;


public class MyHttpResponder {

    private static final String STARTLINE = "HTTP/1.1 200 OK\n";
    private static final String TYPEHEADER = "content-type: text/plain; charset=us-ascii\n";
    private static final String SERVERHEADER = "Server: JamesPC/Windows Custom Java Http Server\n";
    private static final String BADREQUEST = "HTTP/1.1 400 Bad Request: ";
    private static final String UNAUTHORISEDREQUEST = "HTTP/1.1 401 Unaithorized Request: ";
    private static final String AUTHORISEDKEY = "James:pass";

    public static void respond(MyHttpRequest req, DataOutputStream dos) throws IOException {
        String auth = req.getHeader("Authorization");
        if (auth == null || !auth.equals(Base64.getEncoder().encodeToString(AUTHORISEDKEY.getBytes()))) {
            reportUnauthorised(dos);
        } else {
            dos.writeBytes(STARTLINE);
            dos.writeBytes(TYPEHEADER);
            dos.writeBytes(SERVERHEADER);
            dos.writeBytes("\n");
            dos.writeBytes("Hello " + req.getHeader("User-Agent") + " Machine" + "\n");
            dos.close();
        }
    }

    public static void reportError(String message, DataOutputStream dos) throws IOException {
        dos.writeBytes(BADREQUEST + message);
        dos.writeBytes("\n\n");
        dos.close();

    }

    public static void reportUnauthorised(DataOutputStream dos) throws IOException {
        dos.writeBytes(UNAUTHORISEDREQUEST);
        dos.writeBytes("\n\n");
        dos.close();
    }
}
