import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;


public class InitFrame extends JFrame{
	
	JScrollPane scrollpane;
	Panel2 panel2; 
	JTextField textField;
	JButton button1; 
	String userInput;  
	MyHuffmanTree t; 
	int[] frequencies;  
	Heap heap; 
	
	public InitFrame() {
		
		panel2 = new Panel2(); 
		panel2.setPreferredSize(new Dimension(300, 100));
		scrollpane = new JScrollPane(panel2);
	    getContentPane().add(scrollpane, BorderLayout.CENTER);
	    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textField = new JTextField(20);
		panel2.add(textField); 
			
		button1 = new JButton("Create Huffman Tree"); 
		
		button1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				userInput = textField.getText();
				
				//Create new data structures 
				frequencies = new int[27];  //need one for the space char
				heap = new Heap(); 
				
				//Character frequency storage
				userInput = userInput.toLowerCase();
				char a = 'a'; //used for char locations 
				for(int i = 0; i<userInput.length(); i++) {
					
					if(userInput.charAt(i) == ' ') {
						frequencies[26]++; 
					}
					else {
						int charRead = (int)userInput.charAt(i) - (int)a;
						if(charRead < 26 && charRead >=0) {
							frequencies[charRead]++; 
						}
					}
				}
				
				//HEAP INSERTION
				System.out.println("CHAR | FREQ"); 
				for(int i = 0; i<frequencies.length; i++) {
					
					
					if(frequencies[i]!=0) {
						
						char val;
						if(i == 26) 
							val = '_'; //hard coding for sanity
						else
							val = (char)(i+(int)a);
						System.out.println(" " + val +  "   |  " + frequencies[i]);
						if(i == 26) {
							HufTreeNode n = new HufTreeNode(frequencies[i], (char)32, null, null); 
							heap.insert(n);
						}
						else {
							HufTreeNode n = new HufTreeNode(frequencies[i], (char)(i+(int)a), null, null);
							heap.insert(n);
						}
					}
				}
				
				//Make Huf Tree
				//System.out.println("Checking heap size: " + heap.getSize());
				
				//Heap Algorithm for creating the HuffmanTree
				while(heap.getSize() > 1 ) {
					
					HufTreeNode min1 = (HufTreeNode)heap.deleteMin();
					//System.out.println("Deleted (" + (char)min1.getChar() + ") from the heap"); 
					HufTreeNode min2 = (HufTreeNode)heap.deleteMin();  //freq, char, left right
					//System.out.println("Deleted (" + (char)min2.getChar() + ") from the heap"); 
					HufTreeNode comb; 
					Integer combinedFreq = min1.getFreq() + min2.getFreq();
				
					if(min2.getChar() > min1.getChar()) {
						//System.out.println("Min 2 > min 1"); 
						comb = new HufTreeNode(min1.getFreq()+min2.getFreq(), 
								'0', min2, min1); 
						heap.insert(comb); 
						//System.out.println("Added (" + (char)comb.getChar() + ") to the heap, has freq of " + comb.getFreq()); 
					}
					else if (min1.getChar() > min2.getChar()) {
						//System.out.println("Min 1 > Min 2"); 
						comb = new HufTreeNode(combinedFreq, 
								'0', min2, min1); 
						heap.insert(comb); 
						//System.out.println("Added (" + (char)comb.getChar() + ") to the heap, has freq of " + comb.getFreq()); 
					}
					else {
						//System.out.println("Min 1 == Min 2"); 
						comb = new HufTreeNode(combinedFreq, 
								'0', min2, min1); 
						heap.insert(comb); 
						//System.out.println("Added (" + (char)comb.getChar() + ") to the heap, has freq of " + comb.getFreq());
					}
				} //end while 
				
				System.out.println(); 
				//System.out.println("Checking heap size after tree creation: " + heap.getSize());
				//System.out.println((char)((HufTreeNode)heap.deleteMin()).getLeft().getChar()); 
			
				//Now we need to do tree algorithms 
				t = new MyHuffmanTree((HufTreeNode)heap.deleteMin()); //create a new tree with the last element in the heap
				t.computeNodePositions();
				if(t.treeHeight(t.root) == 0) {
					System.out.println("tree height is 0 "); 
					t.computeHuffmanCode(t.root, "0");
				}
				else {
					t.computeHuffmanCode(t.root, "");
				}
				
				t.processBitRepresentation(userInput); 
				t.maxheight = t.treeHeight(t.root); 
				DisplaySimpleTree dt = new DisplaySimpleTree(t); 
				dt.setVisible(true);
			} 
		}); 
		panel2.add(button1); 
		pack(); 
	}
}

class Panel2 extends JPanel {
	
	public Panel2() {
		setBackground(Color.white);
	    setForeground(Color.black);
	}
	protected void paintComponent(Graphics g) {
		
		
	    g.setColor(getBackground()); //colors the window
	      g.fillRect(0, 0, getWidth(), getHeight());
	      g.setColor(getForeground()); //set color and fonts
	      Font MyFont = new Font("SansSerif",Font.PLAIN,10);
	      g.setFont(MyFont);

	}
}
