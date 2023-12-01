package rocks.zipcode;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WebClient2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String navi;

        do {
            System.out.print("curl ");
            navi = scan.nextLine();
            navi = !navi.isEmpty() ? navi : "/";

            //if statement if line 31 URL is removed
            // if navi.isEmpty() Sout "Could not resolve host: curl"
            // 6 - cannot resolve host
            // 7 - cannot connect
            // 20 - http cannot be retrieved
            // ^^^^^^ ERROR MESSAGES from CURL

            //Curl is how web servers communicate
            // ALL CURLS IS MAKING CALLS TO END POINT: POST, GET, PUT, DELETE

            // POST - sending data to the back-end
            // PUT - updating data || most of the time you use POST and GET 99% of the time
            // DELETE - delete data
            // GET - retrieve data

            // Understand front end and back end
            // Understand databases and APIs

            // API and EndPoints (foo, bar, test)
            // Hitting those end points and getting the responses
            //Front end makes API calls to the back end -> receives something back

            //Like user name and password -> BACK END stores it

            HttpClient client = HttpClient.newBuilder() // New client for HTTP
                    .version(HttpClient.Version.HTTP_2)  //Version HTTP
                    .followRedirects(HttpClient.Redirect.NORMAL)  // not be redirected from any other webpage
                    // NORMAL means only GET and HEAD request (not sure the exact meanings)
                    .proxy(ProxySelector.of(new InetSocketAddress("localhost", 8000))) //define host name with specified host
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8000/" + navi)).build(); // using navi to use foo, bar, test
            //  ^^^^ Can remove URL to imitate CURL command
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();


        } while (!navi.equals("exit"));
        scan.close();
    }
}
