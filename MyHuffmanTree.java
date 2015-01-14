import java.util.*; 
public class MyHuffmanTree { //remember to change this
    String inputString= new String();
    HufTreeNode root;
    int totalnodes = 0; //keeps track of the inorder number for horiz. scaling 
    int maxheight=0;//keeps track of the depth of the tree for vert. scaling
    String bitRep; 
    
    ArrayList<HufTreeNode> leafArray; 

    MyHuffmanTree() {
      root = null;
    }
    
    MyHuffmanTree(HufTreeNode t) {
    	root = t; 
    	leafArray = new ArrayList<HufTreeNode>(); //this will give the total # of chars
    }

    public int treeHeight(HufTreeNode t){
	if(t==null) return -1;
          else return 1 + max(treeHeight(t.left),treeHeight(t.right));
    }
    public int max(int a, int b){
	  if(a>b) return a; else return b;
    }

    public void computeNodePositions() {
      int depth = 1;
      inorder_traversal(root, depth);
    }

//traverses tree and computes x,y position of each node, stores it in the node, also 
    //computes huff codes

    public void inorder_traversal(HufTreeNode t, int depth) { 
      if (t != null) {
        inorder_traversal(t.left, depth + 1); //add 1 to depth (y coordinate) 
        t.xpos = totalnodes++; //x coord is node number in inorder traversal
        t.ypos = depth; // mark y coord as depth
        inorder_traversal(t.right, depth + 1);
      }
    }
    
    public void computeHuffmanCode(HufTreeNode t, String code) {
    	if(t!=null) {
    		leafArray.add(t); 
    		t.addHuffcode(code);
    		computeHuffmanCode(t.left, code+"1");
    		computeHuffmanCode(t.right, code+"0"); 
    	}
	  
    }
    
    public void processBitRepresentation(String userInput) {
    	
    	bitRep = ""; 
    	
    	for(int i=0; i<userInput.length(); i++) {
    		char c = userInput.charAt(i); 
    		//now search
    		for(int j = 0; j<leafArray.size(); j++) {
    			//System.out.println("Character codes: " + (char)leafArray.get(j).getChar()); 
    			if((char)leafArray.get(j).getChar() == c) { 
    				//System.out.println("Character codes: " + leafArray.get(i).getChar());  
    				bitRep+=leafArray.get(j).getHuffcode(); 
    			}
    		}
    	}
    	
    	
    }
    
    public String getBitRep() {
    	return bitRep; 
    }



/* below is standard Binary Search tree insert code, creates the tree */

    public Node insert(Node root, String s) { // Binary Search tree insert
      if (root == null) {
        root = new Node(s, null, null);
        return root;
      }
      else {
        if (s.compareTo((String)(root.data)) == 0) {
           return root;  /* duplicate word  found - do nothing */
        } else   if (s.compareTo((String)(root.data)) < 0)
                     root.left = insert(root.left, s);
                 else
                     root.right = insert(root.right, s);
        return root;
      }
    }
  }

class Node {  //standard Binary Tree node
    Object data;
    Node left;
    Node right;
    int xpos;  //stores x and y position of the node in the tree
    int ypos;  

    Node(String x, Node l, Node r) {
      left = l;
      right = r;
      data = (Object) x;
    }
  }

