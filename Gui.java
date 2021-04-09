/**
 * @Author Duffy Anderson, Lucius Holzinger, Myles Marr
 * @Date 04/09/2021
 * @Purpose: to Create series of JFrames, along with a way to check for similar hashtags,
             and ways to uplaod and view photos
 * @Update: 
 * @Bugs: -
 * 	    	-
 * 	    	-
 * */
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import Net.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FileDialog;

public class Gui extends Main implements ActionListener {
  //postFrame items
  String textAreaData;
  JTextArea caption;
  JFrame postFrame;
  
  //Welcome Frame Items
  JFrame welcomePage;
  JTextArea nameBox;
  JTextArea nameQuestion;
  String name = "default";
 

  //scrollPostsFrame items
  JFrame scrollPostsFrame;
  JPanel scrollPanel;
  JScrollPane scrollPane;

  //image Items
  protected Image image;
  protected JLabel label1;
  protected JButton button1;
  protected FileLoader canvas3;
  FileDialog fd = new FileDialog(postFrame, "Open", FileDialog.LOAD);

  Graphics g; 
  //imageHash Objects
  // ImageHash myHashPic; 
  // myHashPic = new ImageHash(); 
  List<ImageHash> hashPicList; 

  public Gui(){
    postFrame  = new JFrame("Posting");
    caption = new JTextArea(400, 200);
    label1 = new JLabel("Choose image");
    button1 = new JButton("select");
    postFrame.setSize(400, 800);
    postFrame.setLayout(new FlowLayout());
    postFrame.add()
    postFrame.add(button1);
    postFrame.add(BorderLayout.SOUTH, caption);
    postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    button1.addActionListener(this);
    canvas3 = new FileLoader(null);
    canvas3.setSize(700, 700);
    postFrame.add(BorderLayout.NORTH, canvas3);
    //postFrame.getContentPane...

    welcomePage = new JFrame("Welcome");
    nameBox = new JTextArea(); 
    nameQuestion = new JTextArea();
    
    //changes visibilty to allow different pages without action listener.
    caption.setVisible(false);
    postFrame.setVisible(false);
    welcomePage.setVisible(true);
    nameBox.setVisible(true);
    nameQuestion.setVisible(true);

    //scrollPane
    scrollPostsFrame = new JFrame();
    scrollPane = new JScrollPane();
    scrollPanel = new JPanel();

    //ImageHash Objects
    hashPicList = new ArrayList<>();
  }
  
  /**
  * Should check for people interacting with the project
  * might use a button but I would prefer something 
  * similar to hyper links
  */
  public void actionPerformed(ActionEvent e){
    //possible delete
    JButton b = (JButton) e.getSource();
    if((JButton)b == button1){
      fd.setVisible(true);
      imageLoad();
    }
  }

  public void imageLoad(){
    String f = (fd.getDirectory() + fd.getFile());
    Toolkit tk = Toolkit.getDefaultToolkit();
    image = tk.getImage(f);
    canvas3.setImage(image);
    canvas3.paint(g);
  }

  public void algorithm(){
    List<String> myAlgList = new ArrayList<>();
    textAreaData = caption.getText();
    String tempString = textAreaData;

    while(tempString.indexOf('#') >= 0){
      String parsedText;
      tempString.substring(tempString.indexOf('#'));
      parsedText =  tempString.substring(0, tempString.indexOf('#'));
      myAlgList.add(parsedText);
    }//end of while loop to seperate hashtags 


    /**
     *This is where we will put the cross refrencing of the hashtags.
     */
  }//end of algorithm function
  
  public void start(){
    welcomePage.setSize(400, 800);
    welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    welcomePage.add(BorderLayout.CENTER, nameQuestion);
    welcomePage.add(BorderLayout.SOUTH, nameBox);
    nameQuestion.setEditable(false);
    nameQuestion.append("Please enter a UserName.");
    name = nameBox.getText();
    nameQuestion.setText("Thank You.");  
    if(name != "default"){
      caption.setVisible(true);
      postFrame.setVisible(true);
     welcomePage.setVisible(false);
      nameBox.setVisible(false);
      nameQuestion.setVisible(false);
    }  
   
    
  }//end of start function
}//end of Gui class