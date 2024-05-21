import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Node implements MouseListener, MouseMotionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -516787078682882545L;
	private int ID;
	private String data;
	private ArrayList<Node> edges = new ArrayList<Node>();
	public Rectangle outside;
	public int coordx;
	public int coordy;
	Color c;
	
	static Color appColor;
	
	public String getData() {
		return data;
	}
	
	public Node(String d) {
		
		ID = Application.getInstance().NodeID;
		data = d;
		Random r = new Random();
		coordx = r.nextInt(Application.WIDTH);
		coordy = r.nextInt(Application.HEIGHT);
		outside = new Rectangle(coordx, coordy, 100, 100);
		Application.getInstance().addMouseListener(this);
		Application.getInstance().addMouseMotionListener(this);
		Application.getInstance().NodeID++;
		
		c = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	}
	
	public void addEdge(Node e) {
		edges.add(e);
	}
	
	public String output() {
		String edgeString = "";
		for (Node n: edges) {
			edgeString += "" + n.ID + ", ";
		}
		
		return "ID: " + ID + "\tEdges: " + edgeString;
	}
	
	public void render(Graphics g) {
		appColor = g.getColor();
		
		g.setColor(c);
		g.drawRect(coordx, coordy, 100, 100);
		
		Graphical.drawCenteredText(g, data, coordx, coordy, coordx+100, coordy+100);
		
		for(Node n: edges) {
			g.drawLine(coordx+50, coordy+50, n.coordx+50, n.coordy+50);
			Graphical.drawLineTheta(n.coordx+50, n.coordy+50, 20, Graphical.getAngle(n.coordx+50, n.coordy+50, coordx+50, coordy+50) + 45, g);
			Graphical.drawLineTheta(n.coordx+50, n.coordy+50, 20, Graphical.getAngle(n.coordx+50, n.coordy+50, coordx+50, coordy+50) + 45 - 270, g);
		}
		
		g.setColor(appColor);
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (outside.contains(e.getX(), e.getY()) && (Application.draggedNode == null || Application.draggedNode.equals(this))) {
			Application.draggedNode = this;
			coordx = e.getX() - 50;
			coordy = e.getY() - 50;
			outside = new Rectangle(coordx, coordy, 100, 100);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		if (outside.contains(e.getX(), e.getY()) && (Application.draggedNode == null || Application.draggedNode.equals(this))) {
			Application.draggedNode = this;
			coordx = e.getX() - 50;
			coordy = e.getY() - 50;
			outside = new Rectangle(coordx, coordy, 100, 100);
			
			if(Application.getInstance().connectingEdges) {
				if (Objects.equals(Application.getInstance().edgeA, null)) {
					Application.getInstance().edgeA = this;
				}
				else {
					Application.getInstance().edgeA.addEdge(this);
					Application.getInstance().connectingEdges = false;
					Application.getInstance().edgeA = null;
				}
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.draggedNode = null;
		outside = new Rectangle(coordx, coordy, 100, 100);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
		if (Application.draggedNode.equals(this))
			Application.draggedNode = null;
			outside = new Rectangle(coordx, coordy, 100, 100);
		}
		catch (NullPointerException ex) {
			
		}
		
	}
	

}
