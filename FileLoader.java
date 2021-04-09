import java.awt.*;
import java.awt.FileDialog;
import java.awt.Graphics;

public class FileLoader extends Canvas {
  Image img;
  

  public FileLoader(Image img){
    this.img = img;
  }

  public void setImage(Image img){
    this.img = img;
  }

  public void paint(Graphics g){
    if(img != null){
      g.drawImage(img, 50, 200, Color.lightGray, this);
    }
  }
}