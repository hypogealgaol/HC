// Code for popping up a window that displays a custom component
// in this case we are displaying a Binary Search tree  
// reference problem 4.38 of Weiss to compute tree node x,y positions

// input is a text file name that will form the Binary Search Tree

//     java DisplaySimpleTree textfile


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.*; 

public class DisplaySimpleTree extends JFrame {
  JScrollPane scrollpane;
  DisplayPanel panel;
  String userInput; 
  
  public DisplaySimpleTree(MyHuffmanTree t) {
	  
    panel = new DisplayPanel(t);
    panel.setPreferredSize(new Dimension(800, 800));
    //panel.add(button1); 
    scrollpane = new JScrollPane(panel);
    getContentPane().add(scrollpane, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();  // cleans up the window panel
    
  }

  public static void main(String[] args) {

    //MyHuffmanTree t = new MyHuffmanTree(); // t is Binary tree we are displayings
    InitFrame it = new InitFrame(); 
    it.setVisible(true); 
  }
}

  