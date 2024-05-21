import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Button implements MouseListener, MouseMotionListener {
	public Rectangle bounds; // The phyisical button
	public String txt; // What the button displays as text
	Color cDefault; // The default color when the button is NOT highlighted
	Color cHovered; // The color of the button when the color is highlighted
	public boolean hovered = false; // Whether or not this button is being hovered over by the mouse.
	static boolean isMouse = true;


	// The constructor. These are all invoked in ButtonCollection
	public Button(Rectangle b, String t, Color co, Color co2) {
		bounds = b;
		txt = t;
		cDefault = co;
		cHovered = co2;
	}

	public void draw() {
		Graphical.drawButton(this);
	}

	public void init() {

	}

	public void mouseLeftClicked(MouseEvent e) {
		System.out.println("Did it the old way");
	}

	public void mouseRightClicked(MouseEvent e) {

	}

	public void mouseMiddleClicked(MouseEvent e) {

	}

	public Color getHoveredColor() {
		return cHovered;
	}

	public Color getDefaultcolor() {
		return cDefault;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				if (bounds.contains(x, y)) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						try {
							mouseLeftClicked(e);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getButton() == MouseEvent.BUTTON2) {
						mouseRightClicked(e);
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						mouseMiddleClicked(e);
					}
				}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		hovered = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent e) {
		if(bounds.contains(e.getX(),e.getY()))
			hovered = true;
			else
			hovered = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
}
