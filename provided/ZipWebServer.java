package rocks.zipcode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ZipWebServer {
    static final int port = 8000;
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(ZipWebServer.port), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/test", new TestHandler());
        server.createContext("/foo", new FooHandler());
        server.createContext("/bar", new BarHandler());
        // server.setExecutor(null); // creates a default executor
        // server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(5));
        server.start();
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if (t.getRequestMethod().equals("GET") ) {
                String response = "Zip Code Rocks!";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "No GET!";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Testing! Testing! Zip Code Rocks!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class FooHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<HTML><BODY><h1>HTML</h1><p>This is a proper paragraph of text.</p></BODY></HTML>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class BarHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = MakeBody();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static String MakeBody() {
        StringBuilder sb = new StringBuilder();
        String css = "body { font-family: Arial, sans-serif; line-height: 1.6; color: #222; " +
                "max-width: 40rem; padding: 2rem; margin: auto; background: #fafafa; }" +
                "a { color: #2ECC40; } h1, h2, strong { color: #111; }";

        sb.append("<HTML>");
        sb.append("<style>");
        sb.append(css);
        sb.append("</style>");
        sb.append("<BODY><h1>Level One Header</h1>");
        sb.append("<p>");
        for(int i = 0; i<10; i++) {
            sb.append("This is a proper paragraph of text. ");
        }
        sb.append("</p>");
        sb.append("<h2>Header Level 2</h2>");
        sb.append("<p><em>");
        for(int i = 0; i<2; i++) {
            sb.append("How often have I said to you that when you have eliminated the impossible, whatever remains, however improbable, must be the truth? We know that he did not come through the door, the window, or the chimney. We also know that he could not have been concealed in the room, as there is no concealment possible. When, then, did he come?. <br/> ");
        }
        sb.append("</em></p>");

        sb.append("<h3>Header Level 3</h3>");
        sb.append("<p>");
        for(int i = 0; i<5; i++) {
            sb.append("Experience is the teacher of all things.\n");
        }
        sb.append("</p>");

        sb.append("</BODY></HTML>");

        return sb.toString();
    }

}

