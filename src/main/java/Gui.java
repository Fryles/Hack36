package com.csworkspace;

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
// import net.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FileDialog;

public class GUI extends Main implements ActionListener {


    //postFrame items
    String textAreaData;
    JTextArea caption;
    JLabel captionHead;
    JFrame postFrame;

    //Welcome Frame Items
    JFrame welcomePage;
    JTextArea nameBox;
    JTextArea nameQuestion;
    String name = null;
    JButton myButtonName;

    //scrollPostsFrame items
    JFrame scrollPostsFrame;
    JPanel scrollPanel;
    JScrollPane scrollPane;

    //image Items
    ImageIcon myImage;
    JLabel label1 = new JLabel("Choose image");
    JLabel picLabel = new JLabel();
    JButton postBtn = new JButton("select");
    JButton leavePostingBtn = new JButton("Find Posts");
    FileDialog fd = new FileDialog(postFrame, "Open", FileDialog.LOAD);

    //fit check
    JFrame fitFrame;
    JButton exitBtn;
    JLabel fitHeader;
    JLabel fiveKSteps;
    JLabel checkOutside;
    JButton outsideBtn;
    JButton fiveKBtn;
    JPanel fiveKPan;
    JPanel outsidePan;

    int points;

    public GUI(){

        //fit check
        points = 0;

        //post page
        postFrame  = new JFrame("Posting");
        postFrame.setSize(375, 525);
        postFrame.setLayout(new FlowLayout());
        postFrame.add(label1);
        postFrame.add(postBtn);
        postFrame.add(BorderLayout.SOUTH, picLabel);
        postFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        postBtn.addActionListener(this);
        //postFrame.getContentPane...

        //caption
        caption = new JTextArea();
        captionHead = new JLabel();
        postFrame.add(BorderLayout.CENTER, captionHead);
        postFrame.add(BorderLayout.SOUTH, caption);
        postFrame.add(BorderLayout.NORTH, leavePostingBtn);

        welcomePage = new JFrame("Welcome");
        nameBox = new JTextArea();
        nameQuestion = new JTextArea();

        //submit button
        myButtonName = new JButton("Submit Name");
        myButtonName.addActionListener(this);

        //scrollPane
        scrollPostsFrame = new JFrame();
        scrollPostsFrame.setSize(275, 400);
        scrollPane = new JScrollPane();
        scrollPanel = new JPanel();
        leavePostingBtn.addActionListener(this);
        scrollPostsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //fit check
        //instantiate elements
        fitFrame = new JFrame("Fit Check");
        fitHeader = new JLabel();
        fiveKSteps = new JLabel();
        checkOutside = new JLabel();
        exitBtn = new JButton("Exit");
        outsideBtn = new JButton();
        fiveKBtn = new JButton();
        fiveKPan = new JPanel();
        outsidePan = new JPanel();

        //set frame size and add action listeners
        fitFrame.setSize(300, 400);
        fitHeader.setSize(100, 100);
        fiveKSteps.setSize(300, 50);
        checkOutside.setSize(200, 50);
        exitBtn.setSize(25, 25);
        outsideBtn.setSize(75, 75);
        outsideBtn.setLocation(150, 200);
        fiveKBtn.setSize(75, 75);
        fiveKBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        outsideBtn.addActionListener(this);
        fiveKPan.add(fiveKSteps);
        fiveKPan.add(fiveKBtn);
        outsidePan.add(checkOutside);
        outsidePan.add(outsideBtn);


        //add elements to frame
        fitFrame.setLayout(new BorderLayout());
        fitFrame.add(exitBtn, BorderLayout.NORTH);
        fitFrame.setLayout(new FlowLayout());
        fitFrame.add(fiveKPan);
        fitFrame.add(outsidePan);

        fitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set label text
        fitHeader.setText("Fit Check");
        fiveKSteps.setText("Click if you have walked 5k steps");
        checkOutside.setText("Click if you went outside today");

        //changes visibilty to allow different pages without action listener.
        caption.setVisible(false);
        postFrame.setVisible(false);
        welcomePage.setVisible(true);
        nameBox.setVisible(true);
        nameQuestion.setVisible(true);
        captionHead.setVisible(false);
        scrollPostsFrame.setVisible(false);
        scrollPane.setVisible(false);
        scrollPanel.setVisible(false);
        fitFrame.setVisible(false);
        fitHeader.setVisible(false);
        fiveKSteps.setVisible(false);
        checkOutside.setVisible(false);
        exitBtn.setVisible(false);
        outsideBtn.setVisible(false);
        fiveKBtn.setVisible(false);
    }

    /**
     * Should check for people interacting with the project
     * might use a button but I would prefer something
     * similar to hyper links
     */
    public void actionPerformed(ActionEvent e){

        JButton b = (JButton) e.getSource();

        if((JButton)b == postBtn){
            fd.setVisible(true);
            picLabel.setVisible(true);
            imageLoad();
        } else if(((JButton)b == myButtonName)){
            name = nameBox.getText();
            if(name != null){
                posting();
            }
        } else if (((JButton)b == leavePostingBtn)) {
            System.out.println("yolo");
            fitCheck();
        } else if (((JButton)b == exitBtn)){
            scrollingMethod();
        } else if (((JButton)b == outsideBtn)){
            points++;
            outsideBtn.setVisible(false);
        } else if (((JButton)b == fiveKBtn)){
            points += 3;
            fiveKBtn.setVisible(false);
        }//end of if else action listener
    }//end of action performed

    public void imageLoad(){
        if (fd.getFile() == null){
            label1.setText("You have not select");
        }
        String f = (fd.getDirectory() + fd.getFile());
        myImage = new ImageIcon(f);
        Image image = myImage.getImage(); // transform it
        Image newimg = image.getScaledInstance(240, 240,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        myImage = new ImageIcon(newimg);
        picLabel.setIcon(myImage);
        postBtn.setVisible(false);
        leavePostingBtn.setVisible(true);
    }

    public void posting(){
        caption.setVisible(true);
        postFrame.setVisible(true);
        captionHead.setVisible(true);
        welcomePage.setVisible(false);
        nameBox.setVisible(false);
        nameQuestion.setVisible(false);
        caption.setEditable(true);
        caption.setSize(100, 75);
        caption.append("         ");
        captionHead.setText("Enter your hashtags here");
        points += 2;
    }

    public void scrollingMethod(){
        fitFrame.setVisible(false);
        fitHeader.setVisible(false);
        fiveKSteps.setVisible(false);
        checkOutside.setVisible(false);
        exitBtn.setVisible(false);
        outsideBtn.setVisible(false);
        fiveKBtn.setVisible(false);
        scrollPostsFrame.setVisible(true);
        scrollPane.setVisible(true);
        scrollPanel.setVisible(true);
        algorithm();
    }

    public void fitCheck(){
        caption.setVisible(false);
        postFrame.setVisible(false);
        captionHead.setVisible(false);
        leavePostingBtn.setVisible(false);
        fitFrame.setVisible(true);
        fitHeader.setVisible(true);
        fiveKSteps.setVisible(true);
        checkOutside.setVisible(true);
        exitBtn.setVisible(true);
        outsideBtn.setVisible(true);
        fiveKBtn.setVisible(true);
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
            points += 0.5;
        }//end of while loop to seperate hashtags

        /**
         *This is where we will put the cross refrencing of the hashtags.
         */
    }//end of algorithm function

    public void start(){
        Font ogFont = nameQuestion.getFont();
        Color welcomeColor = new Color(80,00,80);
        Font welcomeFont = new Font("Abril Fatface", Font.PLAIN, 40);
        welcomePage.add(BorderLayout.SOUTH, myButtonName);
        welcomePage.setSize(400, 300);
        welcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomePage.add(BorderLayout.NORTH, nameQuestion);
        welcomePage.add(BorderLayout.CENTER, nameBox);
        nameQuestion.setEditable(false);
        nameQuestion.append("Please enter a UserName.");
    }//end of start function
}//end of Gui classcin

//imageHash Objects
//ImageHash myHashPic;
//myHashPic = new ImageHash();
//List<ImageHash> hashPicList;
//nameQuestion.setColor(welcomeColor);
//nameQuestion.setFont(Fonts.welcomeFont);
//ImageHash Objects
//hashPicList = new ArrayList<>();