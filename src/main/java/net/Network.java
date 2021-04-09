package net;

import com.fasterxml.jackson.*;
import org.json.JSONArray;
import org.json.JSONObject;
import net.ImageHash;
import java.io.*;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public static class Network {
    static String baseUri = "http://192.168.1.242/";
    public static void main(String[] args) {

        get("trees");

    }

    public static ImageHash[] get(String hashtag) {
        ArrayList<BufferedImage> imgs;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUri + hashtag)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONArray json = new JSONArray(response.body);

             if (json != null) {
            for (int i=0;i<json.length();i++){
                listdata.add(jsonArray.get(i));  
            }   
        }
            String img64 = json.getString("img");
            String[] hashes = json.getJSONArray("hashes");
            ImageHash img = new ImageHash(img64,hashes);
            imgs.add(img);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void post(final String base64String, final String[] hashes) {
        var values = new HashMap<String, String>() {{
            put("img", base64String);
            put ("hashes", hashes);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri+"post"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        
    }

    public static BufferedImage base64StringToImg(final String base64String) {
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (Exception e) {
            System.out.println(e);
            // error, so return blank img
            return new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        }

    }

    public static String imgToBase64String(final BufferedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}