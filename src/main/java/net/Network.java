package net;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import net.ImageHash;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Network {
    static String baseUri = "http://192.168.1.242/";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static void main(String[] args) {

        get("trees");

    }

    public static ArrayList<ImageHash> get(String hashtag) {
        ArrayList<ImageHash> imgs = new ArrayList<ImageHash>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUri + hashtag)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONArray json = new JSONArray(response.body());

            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject currentJSON = json.getJSONObject(i);
                    String img64 = currentJSON.getString("img");
                    final ObjectMapper objectMapper = new ObjectMapper();
                    String mappableJSON = currentJSON.getJSONArray("hashes").toString();
                    String[] hashes = objectMapper.readValue(mappableJSON, String[].class);
                    ImageHash img = new ImageHash(base64StringToImg(img64), hashes);
                    imgs.add(img);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return imgs;
    }

    public static void post(final String base64String, final String[] hashes) throws IOException, InterruptedException {

        HashMap<Object, Object> data = new HashMap<>();
        data.put("img", base64String);
        data.put("hashes", hashes);

        HttpRequest request = HttpRequest.newBuilder().POST(buildFormDataFromMap(data))
                .uri(URI.create(baseUri+"post")).setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/x-www-form-urlencoded").build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());

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

    private static HttpRequest.BodyPublisher buildFormDataFromMap(HashMap<Object, Object> data) {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
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