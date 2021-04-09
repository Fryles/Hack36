
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.image.BufferedImage;

public class Network {

    public static void main(String[] args) {

        get("trees");

    }

    public static void get(String hashtag) throws IOException, InterruptedException{
        try {
            System.out.println("running");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://192.168.1.242/"+hashtag))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            }catch(Exception e)
    {
        System.out.println(e);
    }
}