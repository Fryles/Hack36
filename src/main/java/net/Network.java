package net;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

public class Network {
    static String baseUri = "http://192.168.1.242/";

    private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public static ArrayList<ImageHash> get(String hashtag) {
        ArrayList<ImageHash> imgs = new ArrayList<ImageHash>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUri + "?hash=" + hashtag)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray json = new JSONArray(response.body());
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject currentJSON = json.getJSONObject(i);
                    String img64 = currentJSON.getString("img");
                    final ObjectMapper objectMapper = new ObjectMapper();
                    String mappableJSON = currentJSON.getJSONArray("hashes").toString();
                    String[] hashes = objectMapper.readValue(mappableJSON, String[].class);
                    String user = currentJSON.get("user").toString();
                    String email = currentJSON.get("email").toString();
                    ImageHash img = new ImageHash(base64StringToImg(img64), hashes, user, email);
                    imgs.add(img);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(imgs.size());
        return imgs;
    }

    public static void post(final String base64String, final String[] hashes,String email, String user) throws IOException, InterruptedException {

        HashMap<Object, Object> data = new HashMap<>();
        data.put("img", base64String);
        JSONArray hashArr = new JSONArray();
        for (int i = 0; i < hashes.length; i++) {
            hashArr.put(hashes[i]);
        }
        data.put("hashes", hashArr.toString());
        data.put("email", email);
        data.put("user", user);
        HttpRequest request = HttpRequest.newBuilder().POST(buildFormDataFromMap(data))
                .uri(URI.create(baseUri + "post")).setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/x-www-form-urlencoded").build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        // print response body
        //System.out.println(response.body());

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
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static BufferedImage toBufferedImage(java.awt.Image img) {

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static String imgToBase64String(java.awt.Image img, String formatName) throws IOException {
        BufferedImage bImage = toBufferedImage(img);
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", s);
        byte[] res = s.toByteArray();
        s.close();
        return Base64.getEncoder().encodeToString(res);
    }
}