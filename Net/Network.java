package Net;

import org.json.JSONArray;
import org.json.JSONObject;
import Net.ImageHash;
import java.io.*;
import java.net.URI;
import java.util.Base64;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Network {

    public static void main(String[] args) {

        get("trees");

    }

    public static ImageHash[] get(String hashtag) {
        ArrayList<BufferedImage> imgs;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://192.168.1.242/" + hashtag)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static BufferedImage base64StringToImg(final String base64String){
        try{
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch(Exception e){
            System.out.println(e);
            //error, so return blank img
            return new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        }
        }
}