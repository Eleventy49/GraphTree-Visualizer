import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class AddEdgeButton extends Button{
		
		public AddEdgeButton() {
			super(new Rectangle(100, 300, 300, 100), "Add Edge", Color.black, Color.gray);
			Application.getInstance().addMouseListener(this);
			Application.getInstance().addMouseMotionListener(this);
		}
		
		public void mouseLeftClicked(MouseEvent e) {
			Application.getInstance().connectingEdges = true;
		}

}
