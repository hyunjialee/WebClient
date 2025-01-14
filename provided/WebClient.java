//package rocks.zipcode;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;


public class WebClient {

    public static void main(String[] args) {

            HttpClient client = HttpClient.newBuilder() // New client for HTTP
                    .version(HttpClient.Version.HTTP_2)  //Version HTTP
                    .followRedirects(HttpClient.Redirect.NORMAL)  // not be redirected from any other webpage
                    // NORMAL means only GET and HEAD request (not sure the exact meanings)
                    .proxy(ProxySelector.of(new InetSocketAddress("localhost", 8000))) //define host name with specified host
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(args[0])).build(); // using navi to use foo, bar, test
                                   //  ^^^^ Can remove URL to imitate CURL command
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();

    }
}
