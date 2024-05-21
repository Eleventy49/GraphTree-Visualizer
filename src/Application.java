import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Application extends Canvas implements Runnable {
	private static final long serialVersionUID = 1;
	public static int WIDTH = 1760;
	public static int HEIGHT = WIDTH / 16 * 9;
	public final String TITLE = "GraphTree Visualizer";
	private boolean running = false;
	private Thread thread;
	static JFrame frame;
	static Dimension windowSize;
	static Application app;
	private GraphicsEnvironment graphicsenvironment;
	public static Font bitoperatorfont36;
	
	private static String nodeText = "";
	
	public static Node draggedNode = null;
	
	int NodeID = 0;
	
	AddNodeButton addNode;
	AddEdgeButton addEdge;
	SaveButton save;
	LoadButton load;
	
	static Graphics g;
	
	Graph graph;
	
	boolean connectingEdges = false;
	Node edgeA;
	
	
	//Used to generate placeholder names for nodes
	static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initializeProgram();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks; // Some number that is basically nanoseconds between frames/ticks.
		double delta = 0; // Difference between system nanotime and when we should process frames and
		
		int updates = 0; // Keep track of ticks since last console display message.
		int frames = 0; // Keep track of frames rendered since last console display message
		long notTimer = System.currentTimeMillis();
		long timer = System.currentTimeMillis(); // Millisecond timer for console output
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				updates++;
				tick();
				render();
				delta--;
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
			}
		}
		stop();
		
	}
	
	public void initializeProgram() {
		addKeyListener(new KeyInput(this));
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}		
		
		requestFocus();
		graphicsenvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try { // Get the font from the file and create one at 36 point and one at 13 point
			InputStream myStream = new BufferedInputStream (new FileInputStream("res/8bitOperatorPlusSC-Regular.ttf"));
			bitoperatorfont36 = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(36f);
			graphicsenvironment.registerFont(bitoperatorfont36);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		edgeA = null;
		
		graph = new Graph();
	
		addNode = new AddNodeButton();
		addEdge = new AddEdgeButton();
		save = new SaveButton();
		load = new LoadButton();

		
		g = bs.getDrawGraphics();
		
	}
	
	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop () {
		frame.dispose();
		if(!running) {
			return;
		}
		running = false;
	
		try {
			thread.join();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	
	}
	
	public void tick() {
		//System.out.println(connectingEdges + "\t" + edgeA);
	}
	
	public void render() {
		g = getBufferStrategy().getDrawGraphics();
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle flag = new Rectangle (0,0,WIDTH,HEIGHT);

		g2.setColor(Color.white);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		g2.draw(flag);
		
		
		graph.renderGraph(g);
		addNode.draw();
		addEdge.draw();
		save.draw();
		load.draw();
		
		g.setColor(Color.black);
		if(connectingEdges) {
			Graphical.drawCenteredText(g, "Connecting Nodes", Application.WIDTH/2, 50, Application.WIDTH/2, 50);
			if(!Objects.equals(edgeA, null)) {
				Graphical.drawCenteredText(g, edgeA.getData(), Application.WIDTH/2, 100, Application.WIDTH/2, 100);
			}
		}
		
		g.setColor(Color.black);
		Graphical.drawCenteredText(g, nodeText, Application.WIDTH/2, Application.HEIGHT - 100, Application.WIDTH/2, Application.HEIGHT - 100);
		System.out.println(Application.WIDTH + "\t" + Application.HEIGHT);
		System.out.println(Application.WIDTH/2 + "\t" + (Application.HEIGHT - 100));
		
		
		g.dispose();
		getBufferStrategy().show();
		
		WIDTH = frame.getSize().width;
		HEIGHT = frame.getSize().height;
		
		
	}
	
	public Graphics getGraphics() {
		return g;
	}
	
	public static Application getInstance() {
		return app;
	}
	
	public static void main(String args[]) {
		app = new Application();
		app.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		app.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		app.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame = new JFrame(app.TITLE);
		frame.add(app);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		app.start();
	}
	
	public void keyPressed(KeyEvent e) {							//Declaring another method that will be run every time a key is pressed
		int key = e.getKeyCode();										//Translate from whatever "KeyEvent" is to something humans can understand.
		
		if((key != KeyEvent.VK_ENTER && key != KeyEvent.VK_BACK_SPACE) && nodeText.length() < 35)
		{
			nodeText += e.getKeyChar();										//Add whatever letter you just typed into the collection of letters on line 7
		}	
		if(key == KeyEvent.VK_BACK_SPACE)								//This is saying "If the key is backspace, then do the things in the curly brackets.
		{
			if(nodeText.length() != 0)										//This is saying "If there is SOMETHING in the collection of letters, do the things in curly brackets.
			{
				nodeText = nodeText.substring(0, nodeText.length() -1 );				//"Take one character out of the collection". And no, there really isn't an "easier" way to do this.
			}
		}
		if(key == KeyEvent.VK_SHIFT)									//"If the key is Shift"
		{																//"Take one character out of the collection". I have to do this because of how I wrote the code between lines 18 and 21.
			nodeText = nodeText.substring(0, nodeText.length() -1 );				//Basically, since shift isn't enter or backspace, it adds SHIFT (The machine codey version at least) to the collection
		}																//All this is doing is making sure that I dont have a name like "[SHIFT_KEY]Dylan", but instead what you would expect.
		if(key == KeyEvent.VK_ENTER)									//"If the key is enter"
		{
			
			graph.addNode(new Node(nodeText));								//Create the new player, and give him the same name as our collection of letters.
			nodeText = "";
		}
	}
}
