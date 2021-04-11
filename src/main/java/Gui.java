
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import net.ImageHash;
import net.Network;

public class Gui extends Main implements ActionListener {
  // postFrame items
  String textAreaData;
  JTextArea caption;
  JFrame postFrame;
  JLabel captionHead;
  JPanel postPanel;

  // Welcome Frame Items
  JFrame welcomePage;
  JPanel welcomePanel;
  JTextArea nameBox;
  JLabel nameQuestion;
  String name = null;
  JButton submitNameBtn;

  // scrollPostsFrame items
  JFrame myMainPanel;
  JLabel hashPanel = new JLabel();
  JLabel scrollPic = new JLabel();
  // JFrame scrollPostsFrame;

  // image Items
  ImageIcon myImage;
  JLabel label1 = new JLabel("Choose image");
  JLabel picLabel = new JLabel();
  JButton postBtn = new JButton("select");
  JButton leavePostingBtn = new JButton("Post");
  FileDialog fd = new FileDialog(postFrame, "Open", FileDialog.LOAD);

  // fit check
  JFrame fitFrame = new JFrame();
  JButton exitFitBtn = new JButton();
  JLabel agreeLabel = new JLabel();
  JLabel emailLabel = new JLabel();
  JTextArea emailTxt = new JTextArea();
  JButton agreeBtn = new JButton();
  String email = null;

  Image toPost;
  String[] hashes;
  int postIndex = 0;
  ArrayList<ImageHash> posts = new ArrayList<ImageHash>();

  // refreshing
  JButton nextPostBtn;
  JButton prevPostBtn;

  // enter hashtag frame
  JFrame hashtagFrame;
  JTextArea enterTagsTxt;
  JButton submitHashtagsBtn;
  JLabel enterTagsQ;

  // profile page things
  JFrame profileFrame;
  JLabel profilePic;
  JLabel userNameLabel;
  JButton postAgainButton;
  public Gui() {

    // post page
    postFrame = new JFrame("Posting");
    postFrame.setSize(375, 525);
    postFrame.setResizable(false);
    postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // postFrame.add(label1);
    // postFrame.add(postBtn);
    // postFrame.add(picLabel, BorderLayout.SOUTH);

    // captions
    caption = new JTextArea();
    // Color welcomeColor = new Color(180, 180, 180);
    // caption.setBackground(welcomeColor);
    // caption.setPreferredSize(new Dimension(200, 30));
    // caption.setSize(100, 100);
    captionHead = new JLabel();
    postPanel = new JPanel();
    postFrame.add(postPanel);
    postPanel.setLayout(null);

    label1.setBounds(135, 0, 100, 25);
    postPanel.add(label1);

    picLabel.setBounds(25, 25, 300, 300);
    postPanel.add(picLabel);

    postBtn.setBounds(145, 35, 75, 25);
    postPanel.add(postBtn);

    captionHead.setBounds(25, 375, 175, 25);
    postPanel.add(captionHead);

    caption.setLocation(200, 380);
    postPanel.add(caption);

    leavePostingBtn.setBounds(125, 450, 125, 25);
    postPanel.add(leavePostingBtn);
    leavePostingBtn.setVisible(false);

    // postFrame.add(BorderLayout.SOUTH, caption);
    // postFrame.add(BorderLayout.CENTER, captionHead);
    // postFrame.add(BorderLayout.NORTH, leavePostingBtn);

    postBtn.addActionListener(this);
    leavePostingBtn.addActionListener(this);
    // postFrame.getContentPane...

    // welcome stuff
    welcomePage = new JFrame("Welcome");
    nameBox = new JTextArea();
    nameQuestion = new JLabel();
    welcomePanel = new JPanel();

    // submit button
    submitNameBtn = new JButton("Submit Name");
    submitNameBtn.addActionListener(this);

    // scrollPane
    // scrollPostsFrame = new JFrame();
    // scrollPostsFrame.setSize(275, 400);

    // enter Hashtags frames
    hashtagFrame = new JFrame();
    enterTagsTxt = new JTextArea();
    submitHashtagsBtn = new JButton("Enter");
    enterTagsQ = new JLabel();
    enterTagsQ.setVisible(false);
    hashtagFrame.setVisible(false);
    enterTagsTxt.setVisible(false);
    submitHashtagsBtn.setVisible(false);
    submitHashtagsBtn.addActionListener(this);

    // fit check
    // fit check
    // create objects
    fitFrame = new JFrame("privacy verification");
    exitFitBtn = new JButton("Exit");
    agreeLabel = new JLabel();
    emailLabel = new JLabel();
    emailTxt = new JTextArea();
    agreeBtn = new JButton("Agree to share info");
    // set sizes
    fitFrame.setSize(400, 300);
    exitFitBtn.setSize(100, 100);
    agreeLabel.setSize(500, 100);
    emailLabel.setSize(500, 100);
    emailTxt.setSize(100, 100);
    agreeBtn.setSize(100, 100);
    fitFrame.setLayout(new FlowLayout());
    // add action listeners
    agreeBtn.addActionListener(this);
    exitFitBtn.addActionListener(this);
    // set up fit check
    fitFrame.add(BorderLayout.CENTER, emailLabel);
    fitFrame.add(BorderLayout.CENTER, agreeLabel);
    fitFrame.add(BorderLayout.CENTER, emailTxt);
    fitFrame.add(BorderLayout.CENTER, agreeBtn);
    fitFrame.add(BorderLayout.CENTER, exitFitBtn);
    // set labels
    emailLabel.setText("Enter your email and ");
    agreeLabel.setText("Click to agree to share your info");

    // refreshing
    nextPostBtn = new JButton("Next");
    prevPostBtn = new JButton("Previous");

    // profile pages
    profilePic = new JLabel();
    userNameLabel = new JLabel();
    postAgainButton = new JButton();

    // changes visibility to allow different pages without action listener.
    welcomePage.setVisible(true);
    nameBox.setVisible(true);
    nameQuestion.setVisible(true);
    // set Visible false
    caption.setVisible(false);
    postFrame.setVisible(false);
    captionHead.setVisible(false);
    // scrollPostsFrame.setVisible(false);
    fitFrame.setVisible(false);
    exitFitBtn.setVisible(false);
    agreeLabel.setVisible(false);
    emailLabel.setVisible(false);
    emailTxt.setVisible(false);
    agreeBtn.setVisible(false);

    profilePic.setVisible(false);
//	profileFrame.setVisible(false);
	userNameLabel.setVisible(false);

  }// end of constructor

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
      hashtags();
    } else if (((JButton) b == agreeBtn)) {
      agreeBtn.setVisible(false);
    } else if(((JButton)b) == nextPostBtn){
      if (posts.size() - 1 > postIndex) {
        postIndex++;
        refreshPost();
      } else {
        profilePage();
      }
    } else if (((JButton) b) == prevPostBtn) {
      if (postIndex > 0) {
        postIndex--;
        refreshPost();
      }
    } else if ((JButton) b == submitHashtagsBtn) {
      getImages();
      scrollingMethod();
    }
  }// end of actionPerformed

  public void hashtags() {
    hashtagFrame.setVisible(true);
    enterTagsTxt.setVisible(true);
    submitHashtagsBtn.setVisible(true);
    enterTagsQ.setVisible(true);
    hashtagFrame.setSize(275, 400);
  
    Color txtBoxColor = new Color(170, 120, 190);
    enterTagsTxt.setBackground(txtBoxColor);
    enterTagsTxt.setPreferredSize(new Dimension(200, 100));
    enterTagsQ.setText("Please enter the HashTags you are looking for.");
    hashtagFrame.setLayout(new FlowLayout());
    hashtagFrame.add(enterTagsTxt);
    hashtagFrame.add(enterTagsQ);
    Color submitTagsColor = new Color(152, 20, 120);
    submitHashtagsBtn.setBackground(submitTagsColor);
    submitHashtagsBtn.setPreferredSize(new Dimension(80, 30));
    hashtagFrame.add(submitHashtagsBtn);
  }

  public void profilePage() {
    System.out.println("inside profile function");
    profileFrame = new JFrame(name);
    profilePic.setVisible(true);
    profileFrame.setVisible(true);
    userNameLabel.setVisible(true);
    postAgainButton.setVisible(true);
    myMainPanel.setVisible(false);
    hashPanel.setVisible(false);
    scrollPic.setVisible(false);
    profileFrame.setSize(275, 400);

    Font welcomeFont = new Font("Georgia", Font.BOLD, 25);
    userNameLabel.setFont(welcomeFont);
    userNameLabel.setText("Contact: " + email);
    ImageIcon myImage = new ImageIcon(
        "C:/Users/duffy/Downloads/Computer%20projects%20senior%20year/Hack36/ironman.png");
    Image image = myImage.getImage(); // transform it
    Image newimg = image.getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    myImage = new ImageIcon(newimg);
    profilePic.setIcon(myImage);
  }

  public void posting() {
    caption.setVisible(true);
    postFrame.setVisible(true);
    captionHead.setVisible(true);
    welcomePage.setVisible(false);
    nameBox.setVisible(false);
    nameQuestion.setVisible(false);
    caption.setEditable(true);
    caption.setSize(150, 20);
    captionHead.setText("Enter your hashtags here");
  }// end of posting method

  public void scrollingMethod() {

    fitFrame.setVisible(false);
    exitFitBtn.setVisible(false);
    agreeLabel.setVisible(false);
    emailLabel.setVisible(false);
    emailTxt.setVisible(false);
    agreeBtn.setVisible(false);
    caption.setVisible(false);
    postFrame.setVisible(false);
    captionHead.setVisible(false);
    leavePostingBtn.setVisible(false);
    // scrollPostsFrame.setVisible(true);
  }

  public void post() throws IOException, InterruptedException {
    System.out.println("POST BEFORE NETWORK");
    hashes = algorithm(caption);
    String base64 = Network.imgToBase64String(toPost, "png");
    Network.post(base64, hashes, email, name);
  }

  public void fitCheck() {
    fitFrame.setVisible(true);
    exitFitBtn.setVisible(true);
    agreeLabel.setVisible(true);
    emailLabel.setVisible(true);
    emailTxt.setVisible(true);
    emailTxt.setPreferredSize(new Dimension(200,30));
    agreeBtn.setVisible(true);
    // scrollPostsFrame.setVisible(false);

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
    postBtn.setPreferredSize(new Dimension(200, 100));
    leavePostingBtn.setVisible(true);
    return image;
  }

  public String[] algorithm(JTextArea value) {
    List<String> myAlgList = new ArrayList<>();
    textAreaData = value.getText();
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

    }
    String[] arr = new String[myAlgList.size()];
    return myAlgList.toArray(arr);
  }

  public void getImages() {
    String[] myHashList = algorithm(enterTagsTxt);
    for (String a : myHashList) {
      List<ImageHash> tempList = Network.get(a);
      for (ImageHash imghsh : tempList) {
        System.out.println("adding: " + imghsh.hashes[0]);
        posts.add(imghsh);
      }
    }
    myMainPanel = new JFrame("Recent Posts");
    refreshPost();
    myMainPanel.setSize(300, 400);
    hashPanel.setSize(250, 100);
    myMainPanel.add(BorderLayout.SOUTH, hashPanel);
    hashPanel.setVisible(true);
    myMainPanel.setVisible(true);
    scrollPic.setVisible(true);
    hashPanel.setVisible(true);
    myMainPanel.setVisible(true);
    myMainPanel.add(BorderLayout.EAST, nextPostBtn);
    myMainPanel.add(BorderLayout.WEST, prevPostBtn);
    nextPostBtn.addActionListener(this);
    prevPostBtn.addActionListener(this);
    /**
     * This is where we will put the cross referencing of the hashtags.
     */
  }// end of algorithm function

  public void refreshPost() {
    if (posts.size() >= postIndex && postIndex >= 0) {
      ImageHash c = posts.get(postIndex);
      Image myI = c.img;
      Image newimg = myI.getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
      myImage = new ImageIcon(newimg);
      scrollPic.setIcon(myImage);
      myMainPanel.add(BorderLayout.CENTER, scrollPic);
      String hashTemp = "";
      for (String ht : c.hashes) {
        hashTemp += "#" + ht + " ";
      }
      System.out.println(hashTemp);
      hashPanel.setText(hashTemp);
    }
  }

  public void start() {
    welcomePage.setSize(475, 325);
    welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    welcomePage.setTitle("Welcome!");
    welcomePage.add(welcomePanel);
    welcomePanel.setLayout(null);

    nameQuestion.setText("Please enter a UserName.");
    nameQuestion.setBounds(140, 30, 200, 50);
    welcomePanel.add(nameQuestion);

    nameBox.setBounds(150, 85, 165, 25);
    welcomePanel.add(nameBox);

    submitNameBtn.setBounds(150, 250, 155, 35);
    welcomePanel.add(submitNameBtn);

    welcomePage.setVisible(true);
    welcomePage.setResizable(false);

    Font welcomeFont = new Font("Abril Fatface", Font.PLAIN, 16);
    nameQuestion.setFont(welcomeFont);
    nameBox.setFont(welcomeFont);
  }// end of start function
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

