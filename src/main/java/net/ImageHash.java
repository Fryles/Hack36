package net;
import java.awt.image.*;

public class ImageHash {
	public BufferedImage img;
	public String[] hashes;
	public String user;
	public String email;
	public ImageHash(BufferedImage imeg,String[] hashz, String uzer, String emayl){
		user = uzer;
		email = emayl;
		img = imeg;
		hashes = hashz;
	}
}
