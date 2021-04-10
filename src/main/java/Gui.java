
/**
 * @Author Duffy Anderson, Lucius Holzinger, Myles Marr
 * @Date 04/09/2021
 * @Purpose: to Create series of JFrames, along with a way to check for similar hashtags,
 *           and ways to uplaod and view photos
 * @Update: 
 * @Bugs: -
 * 	    	-
 * 	    	-
 **/
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import net.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
// import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.FileDialog;

public class Gui extends Main implements ActionListener {
  // postFrame items
  String textAreaData;
  JTextArea caption;
  JFrame postFrame;
  JLabel captionHead;

  // Welcome Frame Items
  JFrame welcomePage;
  JTextArea nameBox;
  JTextArea nameQuestion;
  String name = null;
  JButton submitNameBtn;

  // scrollPostsFrame items
  //JFrame scrollPostsFrame;

  // image Items
  ImageIcon myImage;
  JLabel label1 = new JLabel("Choose image");
  JLabel picLabel = new JLabel();
  JButton postBtn = new JButton("select");
  JButton leavePostingBtn = new JButton("Find Posts");
  FileDialog fd = new FileDialog(postFrame, "Open", FileDialog.LOAD);

  // fit check
  JFrame fitFrame = new JFrame();
  JButton exitFitBtn = new JButton();
  JLabel fivekStepsLabel = new JLabel();
  JLabel outsideLabel = new JLabel();
  JButton outsideBtn = new JButton();
  JButton fiveKBtn = new JButton();

  Image toPost;
  String[] hashes;
  double points;

  public Gui() {
    points = 0.0;

    // post page
    postFrame = new JFrame("Posting");
    postFrame.setSize(375, 525);
    postFrame.setLayout(new FlowLayout());
    postFrame.add(label1);
    postFrame.add(postBtn);
    postFrame.add(picLabel);

    // captions
    caption = new JTextArea();
    captionHead = new JLabel();
    postFrame.add(BorderLayout.CENTER, caption);
    postFrame.add(BorderLayout.CENTER, captionHead);

    postFrame.add(BorderLayout.NORTH, leavePostingBtn);
    postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    postBtn.addActionListener(this);
    leavePostingBtn.addActionListener(this);
    // postFrame.getContentPane...

    welcomePage = new JFrame("Welcome");
    nameBox = new JTextArea();
    nameQuestion = new JTextArea();

    // submit button
    submitNameBtn = new JButton("Submit Name");
    submitNameBtn.addActionListener(this);

    // scrollPane
    // scrollPostsFrame = new JFrame();
    // scrollPostsFrame.setSize(275, 400);

    // fit check
    // create objects
    fitFrame = new JFrame("Fit Check");
    exitFitBtn = new JButton("Exit");
    fivekStepsLabel = new JLabel();
    outsideLabel = new JLabel();
    outsideBtn = new JButton("been Outside");
    fiveKBtn = new JButton("walked 5k Steps");
    // set sizes
    fitFrame.setSize(400, 300);
    exitFitBtn.setSize(100, 100);
    fivekStepsLabel.setSize(500, 100);
    outsideLabel.setSize(500, 100);
    outsideBtn.setSize(100, 100);
    fiveKBtn.setSize(100, 100);
    fitFrame.setLayout(new FlowLayout());
    // add action listeners
    fiveKBtn.addActionListener(this);
    outsideBtn.addActionListener(this);
    exitFitBtn.addActionListener(this);
    // set up fit check
    fitFrame.add(BorderLayout.CENTER, outsideLabel);
    fitFrame.add(BorderLayout.CENTER, fivekStepsLabel);
    fitFrame.add(BorderLayout.CENTER, outsideBtn);
    fitFrame.add(BorderLayout.CENTER, fiveKBtn);
    fitFrame.add(BorderLayout.CENTER, exitFitBtn);
    // set labels
    outsideLabel.setText("Click if you have gone outside today.");
    fivekStepsLabel.setText("Click if you have walked fivek steps.");

    // changes visibility to allow different pages without action listener.
    welcomePage.setVisible(true);
    nameBox.setVisible(true);
    nameQuestion.setVisible(true);
    // set Visible false
    caption.setVisible(false);
    postFrame.setVisible(false);
    captionHead.setVisible(false);
    //scrollPostsFrame.setVisible(false);
    fitFrame.setVisible(false);
    exitFitBtn.setVisible(false);
    fivekStepsLabel.setVisible(false);
    outsideLabel.setVisible(false);
    outsideBtn.setVisible(false);
    fiveKBtn.setVisible(false);

  }

  /**
   * Should check for people interacting with the project might use a button but I
   * would prefer something similar to hyper links
   */
  public void actionPerformed(ActionEvent e) {
    JButton b = (JButton) e.getSource();
    if ((JButton) b == postBtn) {
      fd.setVisible(true);
      picLabel.setVisible(true);
      toPost = imageLoad();
    } else if (((JButton) b == submitNameBtn)) {
      name = nameBox.getText();
      if (name != null) {
        posting();
      } // end of if original on posting page
    } else if (((JButton) b == leavePostingBtn)) {
      System.out.println("in button 2");
      try {
        post();
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
      fitCheck();
    } else if (((JButton) b == exitFitBtn)) {
      getImages();
      scrollingMethod();
    } else if (((JButton) b == outsideBtn)) {
      outsideBtn.setVisible(false);
      points++;
    } else if (((JButton) b == fiveKBtn)) {
      points += 3;
      fiveKBtn.setVisible(false);
    } // end of if else action listener

  }// end of actionPerformed

  public void posting() {
    caption.setVisible(true);
    postFrame.setVisible(true);
    captionHead.setVisible(true);
    welcomePage.setVisible(false);
    nameBox.setVisible(false);
    nameQuestion.setVisible(false);
    caption.setEditable(true);
    caption.setSize(200, 155);
    caption.append("");
    captionHead.setText("Enter your hashtags here");
    points += 2;
  }// end of posting method

  public void scrollingMethod() {

    fitFrame.setVisible(false);
    exitFitBtn.setVisible(false);
    fivekStepsLabel.setVisible(false);
    outsideLabel.setVisible(false);
    outsideBtn.setVisible(false);
    fiveKBtn.setVisible(false);
    caption.setVisible(false);
    postFrame.setVisible(false);
    captionHead.setVisible(false);
    leavePostingBtn.setVisible(false);
    //scrollPostsFrame.setVisible(true);
  }

  public void post() throws IOException, InterruptedException {
    System.out.println("POST BEFORE NETWORK");
    hashes = algorithm();
    String base64 = Network.imgToBase64String(toPost, "png");
    Network.post(base64, hashes);
  }

  public void fitCheck() {
    fitFrame.setVisible(true);
    exitFitBtn.setVisible(true);
    fivekStepsLabel.setVisible(true);
    outsideLabel.setVisible(true);
    outsideBtn.setVisible(true);
    fiveKBtn.setVisible(true);
    //scrollPostsFrame.setVisible(false);

  }

  public Image imageLoad() {

    if (fd.getFile() == null) {
      label1.setText("You have not selected yet");
    }

    String f = (fd.getDirectory() + fd.getFile());
    myImage = new ImageIcon(f);
    Image image = myImage.getImage(); // transform it
    Image newimg = image.getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    myImage = new ImageIcon(newimg);
    picLabel.setIcon(myImage);
    postBtn.setVisible(false);
    leavePostingBtn.setVisible(true);
    return image;
  }

  public String[] algorithm() {
    List<String> myAlgList = new ArrayList<>();
    textAreaData = caption.getText();
    String tempString = textAreaData;

    while (tempString.indexOf('#') >= 0) {
      String parsedText;
      tempString = tempString.substring(tempString.indexOf('#') + 1, tempString.length());
      if (tempString.indexOf('#') >= 0) {
        parsedText = tempString.substring(0, tempString.indexOf('#'));
      } else {
        parsedText = tempString.substring(0, tempString.length());
      }
      myAlgList.add(parsedText);
      System.out.println(parsedText);
      points += 0.5;

    }
    String[] arr = new String[myAlgList.size()];
    return myAlgList.toArray(arr);
  }

  public void getImages() {
    JFrame myMainPanel;
    JLabel hashPanel;
    List<ImageHash> myPostList = new ArrayList<>();
    String[] myHashList = algorithm();
    for (String a : myHashList) {
      List<ImageHash> tempList = Network.get(a);
      for(ImageHash imghsh : tempList) {
        myPostList.add(imghsh);
      }
    }
    
    myMainPanel = new JFrame("Recent");
    for (ImageHash c : myPostList) {
      Image myI = c.img;
      Image newimg = myI.getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return myImage = new ImageIcon(newimg);
      myImage = new ImageIcon(newimg);
      scrollPic.setIcon(myImage);
      myMainPanel.add(BorderLayout.NORTH, scrollPic);
      hashPanel = new JLabel();
      String hashTemp = "";
      for (String ht : c.hashes) {
        hashTemp += "#" + ht + " ";
      }
      System.out.println(hashTemp);
      myMainPanel.setSize(275,400);
      hashPanel.setSize();
      hashPanel.setText(hashTemp);
      myMainPanel.add(BorderLayout.SOUTH, hashPanel);
      scrollPic.setVisible(true);
      hashPanel.setVisible(true);
      myMainPanel.setVisible(true);


    }

    scrollPic.setVisible(true);
    hashPanel.setVisible(true);
    myMainPanel.setVisible(true);
    scrollPaneFrame.setVisible(true);
    scrollPane.setVisible(true);

    /**
     * This is where we will put the cross referencing of the hashtags.
     */
  }// end of algorithm function

  public void start() {
    // Font ogFont = nameQuestion.getFont();
    // Color welcomeColor = new Color(80, 00, 80);
    Font welcomeFont = new Font("Abril Fatface", Font.PLAIN, 20);
    nameQuestion.setFont(welcomeFont);
    welcomePage.add(BorderLayout.SOUTH, submitNameBtn);
    welcomePage.setSize(400, 300);
    welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    welcomePage.add(BorderLayout.NORTH, nameQuestion);
    welcomePage.add(BorderLayout.CENTER, nameBox);
    nameQuestion.setEditable(false);
    nameQuestion.append("Please enter a UserName.");

  }// end of start function

  public String scoreFunc() {
    if (points >= 10) {
      return "Platinum Member";
    } else if (points >= 8) {
      return "Gold Member";
    } else if (points >= 6) {
      return "Silver Member";
    } else if (points >= 4) {
      return "Bronze Member";
    } else {
      return "Novice Member";
    }
  }
}// end of Gui class

// nameQuestion.setColor(welcomeColor);
// nameQuestion.setFont(Fonts.welcomeFont);
// notes
// imageHash Objects
// ImageHash myHashPic;
// myHashPic = new ImageHash();
// List<ImageHash> hashPicList;
// ImageHash Objects
// hashPicList = new ArrayList<>();