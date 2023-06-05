package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitializeOrManage extends JPanel implements ActionListener {
    static JFrame frame;
    private static JLabel label1;
    private String labelStr="Welcome, what would you Like to do today?";
    private String btnStr1="Initialize/manage a to-do list";
    private String btnStr2="Manage To-Do Lists";
    private String btnStr3="Exit";
    private JTextField field;
    private static JButton btn1;
    private static JButton btn2;
    private static JButton btn3;
    public static String userInput;
    public static boolean buttonPressed;

    public InitializeOrManage() {
        buttonPressed=false;
        JPanel buttonPanel = new JPanel();
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.PAGE_END);
        label1 = new JLabel(labelStr);
        field = new JTextField(5);
        btn1 = new JButton(btnStr1);
        btn2 = new JButton(btnStr2);
        btn3 = new JButton(btnStr3);
        btn1.setActionCommand("myButton1");
        btn2.setActionCommand("myButton2");
        btn3.setActionCommand("myButton3");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);//sets "this" class as an action listener for btn.
            //that means that when the btn is clicked,
            //this.actionPerformed(ActionEvent e) will be called.
            //You could also set a different class, if you wanted
            //to capture the response behaviour elsewhere
        add(label1, BorderLayout.PAGE_START);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
    }

    private static void createAndShowIoM() {
        InitializeOrManage mainPanel = new InitializeOrManage();
        frame = new JFrame("To-Do Lists: Initialize or Manage");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            createAndShowIoM();
        });
    }



    //this is the method that runs when Swing registers an action on an element
        //for which this class is an ActionListener

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("myButton1")){
            userInput= "a";
        } else if(e.getActionCommand().equals("myButton2")){
            userInput= "b";
        } else if(e.getActionCommand().equals("myButton3")){
            userInput= "c";
        }
        buttonPressed=true;
    }
    public static String getUserInput(){
        return userInput;
    }
    public static void disableIoMGUI(){
       frame.dispose();
    }
    public static void closing(String closingMessage){
        frame.remove(btn1);
        frame.remove(btn2);
        frame.remove(btn3);
        label1.setText(closingMessage);

    }

}

