import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Graph implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size = 0;
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	public Graph() {
		
	}
	
	public void addNode(Node n) {
		nodes.add(n);
	}
	
	public void displayGraph() {
		for (Node n: nodes) {
			System.out.println(n.output());
		}
	}
	
	public void renderGraph(Graphics g) {
		g.setColor(Color.black);
		for (Node n: nodes) {
			n.render(g);
		}
		g.setColor(Color.white);
	}
	
	private String reverseString(String s) {
		String r = "";
		char ch;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			r = ch+r;
		}
		return r;
	}
	
	public void addRandomNode() {
		Random r = new Random();
		int counter = Application.getInstance().NodeID;
		String nodeName = "";
		//X should be the number of digits
		for(int x = (counter / 26); x >= 0; x--) {
			int divisor = counter % 26;
			nodeName += Application.alphabet[divisor];
			counter -= divisor;
			if(divisor == 0) {
				counter -= 26;
			}
		}
		//
		//for(int i = 0; i < digits; i++) {
		//	nodeName += Application.alphabet[(counter % Application.alphabet.length) - 1];
		//}
		nodeName = reverseString(nodeName);
		Application.getInstance().graph.addNode(new Node(nodeName));
	}
}
