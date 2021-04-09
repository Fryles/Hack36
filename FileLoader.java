import java.awt.FileDialog;

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
      g.drawImage(img, 50, 200, 200, this);
    }
  }

}