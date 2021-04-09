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
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;



public class Gui implements ActionListener extends Main{
  //postFrame items
  String textAreaData;
  JTextArea caption;
  JFrame postFrame;
  
  //Welcome Frame Items
  JFrame welcomePage;
  JTextArea nameBox;
  JTextArea nameQuestion;
  JTextArea welcomeText;
  String name = "default";

  //scrollPostsFrame items
  JFrame scrollPostsFrame;
  JPanel scrollPanel;
  JScrollPane scrollPane;

  //image Items
  protected ImageIcon image;
  protected JLabel label1;
  protected JButton button1;
  protected FileLoader canvas3;

  public Gui(String name){
    postFrame  = new JFrame("Post Frame");
    caption = new JTextArea(400, 200);
    label1 = new JLabel("Choose image");
    button1 = new JButton("select");
    postFrame.setSize(400, 800);
    postFrame.setLayout(new FlowLayout());
    postFrame.add(button1);
    postFrame.add(BorderLayout.SOUTH, caption);
    postFrame.getDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    button1.addActionListener(this);
    //postFrame.getContentPane...

    welcomePage = new JFrame("Welcome Frame");
    nameBox = new JTextArea(); 
    nameQuestion = new JTextArea();
    welcomeText = new JTextArea();
    
    //unsure if this is necessary
    caption.setVisible(false);
    postFrame.setVisible(false);
    welcomePage.setVisible(true);
    nameBox.setVisible(true);
    nameQuestion.setVisible(true);
    welcomeText.setVisible(true);    
    //scrollPane
    scrollPostsFrame = new JFrame();
    scrollPane = new JPanel();
    scrollPanel = new JScrollPane();

  }
  
  /**
  * Should check for people interacting with the project
  * might use a button but I would prefer something 
  * similar to hyper links
  */
  public void ActionPreformed(ActionEvent e){
    System.out.println("Action was preformed");
  }

  public JFrame algorithm(){
    List<String> myAlgList = new ArrayList<>();
    textAreaData = caption.getText();
    String tempString = textAreaData;

    while(tempString.indexOf('#') >= 0){
      String parsedText;
      tempString.subString(tempString.indexOf('#'));
      parsedText =  tempString.subString(0, tempString.indexOf('#'));
      myAlgList.add(parsedText);
    }//end of while loop to seperate hashtags 

    /**
     *This is where we will put the cross refrencing of the hashtags.
     */
    
  }//end of algorithm function
  
  public void start(){
    welcomePage.setSize(400, 800);
    welcomePage.setDefaultCloseOperation();
    welcomePage.add(BorderLayout.North, nameQuestion);.
    welcomePage.add(BorderLayout.Center, nameBox);
    nameQuestion.setEditable(false);
    nameQuestion.append("Please enter a UserName.");

    name = nameBox.getText();
    nameQuestion.setText("Thank You.");
    
    caption.setVisible(true);
    postFrame.setVisible(true);
    welcomePage.setVisible(false);
    nameBox.setVisible(false);
    nameQuestion.setVisible(false);
    welcomeText.setVisible(false);
    
  }//end of start function
}//end of Gui class