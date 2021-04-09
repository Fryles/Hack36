/**
 * @Author Duffy Anderson, Lucius Holzinger, Myles Marr
 * @Date 04/09/2021
 * @Purpose: 
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



public class Gui implements ActionListener extends Main{
  String textAreaData;
  JTextArea commentSection;
  JFrame myFrame;

  public Gui(){
    myFrame  = new JFrame("First Frame");
    commentSection = new JTextArea();
    myFrame.setSize(300, 600);
    myFrame.getDefaultCloseOperation();
    myFrame.getContentPane().add(BorderLayout.SOUTH, button);
  }

  textAreaData = commentSection.getText();
  
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
    for(int i =0; ){
      String parsedText;
      parsedText = textAreaData.next();
    }
   
  }
}
