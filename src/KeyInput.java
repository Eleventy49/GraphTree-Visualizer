import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	Application game;
	
	public KeyInput(Application app) {
		game = app;
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);

	}

}
