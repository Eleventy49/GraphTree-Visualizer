import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

public class LoadButton extends Button{
	
	public LoadButton() {
	super(new Rectangle(100, 700, 300, 100), "Load Graph", Color.black, Color.gray);
	Application.getInstance().addMouseListener(this);
	Application.getInstance().addMouseMotionListener(this);
	}
	
	public void mouseLeftClicked(MouseEvent e) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
		j.setDialogTitle("Save");
		
		int r = j.showSaveDialog(null);
		if (r == JFileChooser.APPROVE_OPTION)
		{
			String x = j.getSelectedFile().getAbsolutePath();
			
			try {
				//FileWriter fw = new FileWriter(x, false);
				//BufferedWriter bw = new BufferedWriter(fw);
				//PrintWriter w = new PrintWriter(bw);
				
				
				FileInputStream fis = new FileInputStream(x);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				try {
					Application.getInstance().graph = (Graph) ois.readObject();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ois.close();
				
				for(Node n: Application.getInstance().graph.nodes) {
					Application.getInstance().addMouseListener(n);
					Application.getInstance().addMouseMotionListener(n);
					
				}
				
			}
			catch (IOException e3) {
				System.out.println("Failed to open the Players file. Could not save.");
				e3.printStackTrace();
			} catch (NullPointerException e2) {
				//WarningText.add("There is no game to save");
			}
		}
	}
	
	

}
