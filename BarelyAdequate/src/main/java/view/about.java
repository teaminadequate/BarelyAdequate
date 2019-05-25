package view;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 * About GUI with team names
 * @author Kyle's PC
 *
 */
public class about {

	private JFrame frame;

	/**App launch*/
	/*
	 * No need because calling program constructs, and constructor calls initialize()
	 * -Gavin
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					about window = new about();
					
					//window.frame.setVisible(true);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		}
	);		
}

	/**Initialize frame*/
	public about() {
		
		initialize();
		
	}

	
	
	
	/**Create Frame*/
	private void initialize() {
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 314);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		
		textArea.setFont(new Font("Rage Italic", Font.BOLD, 36));
		
		textArea.setBackground(new Color(0, 0, 51));
		
		textArea.setForeground(new Color(255, 255, 255));
		
		textArea.setBounds(0, 90, 434, 185);
		
		frame.getContentPane().add(textArea);
		
		
		JLabel background = new JLabel("");
		
		background.setIcon(new ImageIcon("src\\resources\\title.png"));
		
		background.setBounds(0, 0, 434, 89);
		
		frame.getContentPane().add(background);
		
		
	
		
		//Receives team names from text file.
		try {
			
			String text;
			
			FileReader fileR = new FileReader("src\\resources\\about.txt");
			
			BufferedReader reader = new BufferedReader(fileR);
			
			textArea.read(reader, "textArea");			
		}
		
		
		catch (IOException error) {
			
			System.err.println(error);
			
			System.exit(1);
		}
		this.frame.setVisible(true);
	}
}
