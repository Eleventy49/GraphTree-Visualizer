import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Random;

public class AddNodeButton extends Button{
	
	public AddNodeButton() {
		super(new Rectangle(100, 100, 300, 100), "Add Node", Color.black, Color.gray);
		Application.getInstance().addMouseListener(this);
		Application.getInstance().addMouseMotionListener(this);
		
	}
	
	public void mouseLeftClicked(MouseEvent e) {
		Application.getInstance().graph.addRandomNode();
	}

}
