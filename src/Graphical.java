import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Graphical {
	public static void drawCenteredText(Graphics g, String txt, int x1, int y1, int x2, int y2) {
		Color color = g.getColor();
		FontMetrics fm = g.getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(txt, g);
		
		g.setFont(Application.bitoperatorfont36);

		int textHeight = y1;
		int textWidth = x2;
		int panelHeight = y2;
		int panelWidth = x1;

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2) + x2;
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + y1;

		g.drawString(txt, x, y); // Draw the string.
		g.setColor(color);
	}
	
	public static float getAngle(int X1, int Y1, int X2, int Y2) {
		float angle = (float) Math.toDegrees(Math.atan2(Y2 - Y1, X2 - X1));
		
		if (angle < 0) {
			angle += 360;
		}
		
		return -angle;
	}
	
	public static void drawLineTheta(int x, int y, int distance, double theta, Graphics g) {
		double angle = theta * Math.PI / 180; //Convert the supplied angle to radians
		double endX = x + (distance * Math.sin(angle));
		double endY = y + (distance * Math.cos(angle));
		g.drawLine(x, y, (int) endX, (int) endY);
	}
	
	public static void drawLineDegrees(int x, int y, int distance, double angle, Graphics g) {
		double endX = x + (distance * Math.sin(angle));
		double endY = y + (distance * Math.cos(angle));
		g.drawLine(x, y, (int) endX, (int) endY);
	}
	
	public static void drawButton(Button button) {
		Color color = Application.getInstance().getGraphics().getColor();
			Graphics2D g2d = (Graphics2D) Application.g;
			if (button.hovered) {
				Application.getInstance().getGraphics().setColor(button.getHoveredColor());
			} else {
				Application.getInstance().getGraphics().setColor(button.getDefaultcolor());
			}

			// Actually do the drawing to the screen

			FontMetrics fm = Application.getInstance().getGraphics().getFontMetrics(Application.bitoperatorfont36);
			Rectangle2D rect = fm.getStringBounds(button.txt, Application.g);

			int textHeight = (int) (rect.getHeight());
			int textWidth = (int) (rect.getWidth());
			int panelHeight = (int) button.bounds.getHeight();
			int panelWidth = (int) button.bounds.getWidth();

			// Center text horizontally and vertically
			int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
			int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

			Application.getInstance().getGraphics().drawString(button.txt, x, y); // Draw the string.
			g2d.draw(button.bounds);
			Application.getInstance().getGraphics().setColor(color);
		}
	
	
}
