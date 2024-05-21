import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;


public class SaveButton extends Button{
	
	public SaveButton() {
	super(new Rectangle(100, 500, 300, 100), "Save Graph", Color.black, Color.gray);
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
			
			if(x.contains(".txt"))
				x = x.substring(0,x.lastIndexOf(".txt"));
			else
				x += ".txt";
			try {
				//FileWriter fw = new FileWriter(x, false);
				//BufferedWriter bw = new BufferedWriter(fw);
				//PrintWriter w = new PrintWriter(bw);
				
				
				FileOutputStream fos = new FileOutputStream(x);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(Application.getInstance().graph);
				oos.close();
				
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
