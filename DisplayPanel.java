import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JTextField;

public class DisplayPanel extends JPanel {
     MyHuffmanTree t;
     int xs; 
     int ys; 

    public DisplayPanel(MyHuffmanTree t) {
      this.t = t; // allows dispay routines to access the tree
      setBackground(Color.white);
      setForeground(Color.black);
    }

    protected void paintComponent(Graphics g) {

      g.setColor(getBackground()); //colors the window
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(getForeground()); //set color and fonts
      Font MyFont = new Font("SansSerif",Font.PLAIN,6);
      g.setFont(MyFont);
      xs=10;   //where to start printing on the panel
      ys=20;
      //g.drawString("Binary Search tree for the input string:\n",xs,ys);
      ys=ys+10;;
      int start=0;
      //  print input string on panel, 150 chars per line
      // if string longer than 23 lines don't print
      if(t.inputString.length()<23*150){
           while((t.inputString.length()-start)>150){
              g.drawString(t.inputString.substring(start,start+150),xs,ys);        
              start+=151;
              ys+=15;
           }
           g.drawString(t.inputString.substring(start,t.inputString.length()),xs,ys);
      }
      MyFont = new Font("SansSerif",Font.BOLD,14); //bigger font for tree
      g.setFont(MyFont);
      
      //Compute huffmancodes
      
      this.drawTree(g, t.root); // draw the tree
      revalidate(); //update the component panel
      
      //Short recursive method to get the bin rep
      String binaryRep = t.getBitRep(); 
      
      
      g.drawString("Binary Representation for the input string: " + binaryRep ,xs,ys);
    }

      public void drawTree(Graphics g, HufTreeNode root) {//actually draws the tree
	      int dx, dy, dx2, dy2;
	      int SCREEN_WIDTH=800; //screen size for panel
	      int SCREEN_HEIGHT=700;
	      int XSCALE, YSCALE;  
	      String binaryRep = ""; 
	      XSCALE=SCREEN_WIDTH/t.totalnodes; //scale x by total nodes in tree
	      YSCALE=(SCREEN_HEIGHT-ys)/(t.maxheight+1); //scale y by tree height
	
	      if (root != null) { // inorder traversal to draw each node
	        drawTree(g, root.left); // do left side of inorder traversal 
	        dx = root.xpos * XSCALE; // get x,y coords., and scale them 
	        dy = root.ypos * YSCALE +ys;
	        
	        //Compute the huffman codes so we can draw them
	        
	        //put ovals.
	        
	// this draws the lines from a node to its children, if any
	        if(root.left!=null){ //draws the line to left child if it exists
	          dx2 = root.left.xpos * XSCALE; 
	          dy2 = root.left.ypos * YSCALE +ys;
	          g.drawLine(dx,dy,dx2,dy2);
	        }
	        if(root.right!=null){ //draws the line to right child if it exists
	          dx2 = root.right.xpos * XSCALE;//get right child x,y scaled position
	          dy2 = root.right.ypos * YSCALE + ys;
	          g.drawLine(dx,dy,dx2,dy2);
	        }
	        
	        //trying to make the ovals obscure the lines but doesn't work really
	        g.drawOval(dx-17, dy-22, 43, 33);
	        g.setColor(Color.white);
	        g.fillOval(dx-15,  dy-20, 40, 30);
	        String sn;
	        Character s; 
	        g.setColor(Color.black);
	        //drawing strings for the method 
	        if(!root.isLeaf()) {
	        	sn = root.getStringRep(); 
	        	g.drawString(sn.toString(), dx, dy);

	        }
	        else {
	        	//Leaves
	        	s = (char)root.getChar();
	        	g.drawString(s.toString() + ":" + root.getFreq(), dx, dy);
	        	g.drawString(root.getHuffcode(), dx, dy+20);
	        	 
	        }	//get the word at this node
	        
	        drawTree(g, root.right); //now do right side of inorder traversal 
	      }
	      //Displaying the digits 
	      //System.out.println(binaryRep + "n"); 
	 //g.drawString("Binary Representation for the input string: \n" + binaryRep,xs,ys);
    } //end drawTree
}