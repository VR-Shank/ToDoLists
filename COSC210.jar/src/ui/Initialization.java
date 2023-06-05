package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Initialization extends JPanel implements ActionListener {
    static String initialization = "Which type of list would you like to initialize?";
    private  JLabel label1;
    private  String btnStr1="Make a Task List";
    private  String btnStr2="Make a Shopping List";
    private  String btnStr3="Choose a Saved File";
    private  String btnStr4="Weekly Challenge";
    private  String btnStr5="Exit";
    public static boolean buttonPressed=false;

    private  JTextField field;
    JButton btn1;
    JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    public String userInput;
    public Initialization() {
        JPanel buttonPanel = new JPanel();
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.PAGE_END);
        label1 = new JLabel(initialization);
        btn1 = new JButton(btnStr1);
        btn2 = new JButton(btnStr2);
        btn3 = new JButton(btnStr3);
        btn4 = new JButton(btnStr4);
        btn5 = new JButton(btnStr5);
        btn1.setActionCommand("myButton1");
        btn2.setActionCommand("myButton2");
        btn3.setActionCommand("myButton3");
        btn4.setActionCommand("myButton4");
        btn5.setActionCommand("myButton5");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);

        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere

        add(label1, BorderLayout.PAGE_START);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        buttonPanel.add(btn5);

    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener

    private static void createAndShowInitialization() {
        Initialization mainPanel = new Initialization();

        JFrame frame = new JFrame("To-Do Lists: Initialization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("myButton1")){
            userInput= "a";
        } else if(e.getActionCommand().equals("myButton2")){
            userInput= "b";
        } else if(e.getActionCommand().equals("myButton3")){
            userInput= "c";
        }else if(e.getActionCommand().equals("myButton4")){
            userInput= "d";
        } else if(e.getActionCommand().equals("myButton5")){
            userInput= "e";
        }
        buttonPressed=true;
        setVisible(false);

    }

    public void closingInitialization(String closingMessage){
        remove(btn1);
        remove(btn2);
        remove(btn3);
        remove(btn4);
        remove(btn5);
        label1.setText(closingMessage);

    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            createAndShowInitialization();
        });
    }

}

