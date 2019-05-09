package server;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class MyHttpRequest {

    private final static String GET = "GET";
    private final static String MYREQUEST = "/DATE";
    private final static String HTTPVERSION = "HTTP/1.1";

    private String method;
    private String uri;
    private String version;
    private final HashMap<String, String> headers = new HashMap<>();

    private void addHeader(String hdrName, String content) {
        headers.put(hdrName.toLowerCase(), content);
    }

    private static void parseStartLine(StreamTokenizer st, MyHttpRequest req) throws HttpParseException, IOException {

        if (st.ttype == StreamTokenizer.TT_WORD && st.sval.equalsIgnoreCase(GET)) {

            req.setMethod(st.sval);
            st.nextToken();

            if (st.ttype == StreamTokenizer.TT_WORD && st.sval.equalsIgnoreCase(MYREQUEST)) {
                req.setUri(st.sval);
                st.nextToken();

                if (st.ttype == StreamTokenizer.TT_WORD && st.sval.equalsIgnoreCase(HTTPVERSION)) {
                    req.setVersion(st.sval);
                    st.nextToken();

                    if (st.ttype == StreamTokenizer.TT_EOL) {
                        st.nextToken();
                    } else {
                        throw new HttpParseException("Expected EOL not " + st.sval);
                    }
                } else {
                    throw new HttpParseException("Unsupported Version: " + st.sval);
                }
            } else {
                throw new HttpParseException("Unsupported URI: " + st.sval);
            }
        } else {
            throw new HttpParseException("Unsupported Method: " + st.sval);
        }

    }

    private static boolean parseHeader(StreamTokenizer st, MyHttpRequest req) throws HttpParseException, IOException {
        if (st.ttype == StreamTokenizer.TT_EOL) {
            return false;
        }
        // First word is the header name
        String hdr = "";
        String content = "";
        if (st.ttype == StreamTokenizer.TT_WORD) {
            // Gobble up headers till we find an eol
            hdr = st.sval;
            st.nextToken();
            if (st.ttype == ':') {
                st.nextToken();
                // accumulate till eol
                while (st.ttype != StreamTokenizer.TT_EOL) {
                    if (st.ttype == StreamTokenizer.TT_WORD) {
                        content += st.sval;
                    } else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                        content += st.nval;
                    } else {
                        content += (char) st.ttype;
                    }
                    st.nextToken();
                }
            }
            req.addHeader(hdr, content);
        }
        st.nextToken();
        return st.ttype != StreamTokenizer.TT_EOL && st.ttype != StreamTokenizer.TT_EOF;
    }

    public static MyHttpRequest parse(Reader r) throws HttpParseException {
        try {
            MyHttpRequest req = new MyHttpRequest();
            StreamTokenizer st = new StreamTokenizer(r); // check the javadoc for the StreamTokenizer online

            st.eolIsSignificant(true);
            st.wordChars('0', '9');
            st.wordChars('.', '.');
            st.wordChars('-', '-');
            st.wordChars('\'', '\'');
            st.wordChars('"', '"');
            st.wordChars('/', '/');
            st.nextToken();

            //parse the request line
            parseStartLine(st, req);

            //parse all headers
            while (parseHeader(st, req)) {
                // Do nothing
            }
            // Ignore the rest of the message body
            return (req);
        } catch (IOException ioe) {
            throw new HttpParseException(ioe.getMessage());
        }

    }

    public String getHeader(String hdr) {
        if (headers.containsKey(hdr.toLowerCase())) {
            return headers.get(hdr.toLowerCase());
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += getMethod() + " " + getUri() + " " + getVersion() + "\n";
        for (String hdr : headers.keySet()) {
            s += hdr + ":" + headers.get(hdr) + "\n";
        }
        return s;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
