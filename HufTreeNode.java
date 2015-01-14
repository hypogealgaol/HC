
public class HufTreeNode implements Comparable {

	private int frequency;
	private int character; 
	HufTreeNode left, right; 
	int xpos;
	int ypos; 
	private String huffcode; 
	
	public HufTreeNode(int f, int c, HufTreeNode l, HufTreeNode r) {
		frequency = f;
		character = c; 
		left = l;
		right = r;
		huffcode = ""; 
	}
	
	public int getChar() {
		return character;
	}
	public int getFreq() {
		return frequency; 
	}
	
	public void addHuffcode(String code) {
		huffcode+=code.toString(); 
	}
	
	public String getHuffcode() {
		return huffcode;
	}
	
	//gives the string representation of the frequency for non leaf nodes
	public String getStringRep() {
		Integer freq = frequency; 
		return freq.toString(); 
	}
	
	public int compareTo(Object x) {
		HufTreeNode test = (HufTreeNode)x;
		if(frequency>test.frequency) return 1;
		if(frequency == test.frequency) return 0;
		return -1;
	}
	
	public boolean isLeaf() {
		assert (left ==null && right == null) || (left !=null && right!=null);
		return (left ==null && right == null); 
	}
	
	public HufTreeNode getLeft() {
		return left;
		
	}
	public HufTreeNode getRight() {
		return right; 
	}
}
