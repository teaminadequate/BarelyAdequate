package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.SystemColor;

public class GUI_START {

	/**the JFrame...*/
	private JFrame frame;
	
	/** Username enter field*/
	private JTextField nameField;
	
	/**password field*/ 
	private JTextField emailField;
	
	/**Project title field (my Projects tab*/
	private JTextField titleField;
	
	/**Total investment field (my Projects tab*/
	private JTextField InvestmentField;
	
	/** Compare payoff field 1/2/3 (Compare Projects Tab)*/
	private JTextField comparePayOffField1;
	private JTextField comparePayOffField2;
	private JTextField comparePayOffField3;

	/**
	 * Launch the app..
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_START window = new GUI_START();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize...
	 */
	public GUI_START() {
		setup();
	}

	/**
	 * setup gui all one method (because YOLO) 
	 */
	private void setup() {
		
		
		//Setup Frame
		frame = new JFrame();
		
		frame.setBounds(100, 100, 500, 401);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(0, 0, 484, 362);
		
		frame.getContentPane().add(tabbedPane);
		
		
		
		
		/*Panel for home tab and field entries.*/
		JPanel panel = new JPanel();
		
		tabbedPane.addTab("Home", null, panel, null);
		
		panel.setLayout(null);
		
		nameField = new JTextField();
		nameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		nameField.setBounds(178, 131, 119, 20);
		
		panel.add(nameField);
		
		nameField.setColumns(10);

		emailField = new JTextField();
		emailField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		emailField.setBounds(178, 175, 119, 20);
		
		panel.add(emailField);
		
		emailField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		
		lblName.setBounds(178, 117, 46, 14);
		
		panel.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		
		lblEmail.setBounds(178, 162, 81, 14);
		
		panel.add(lblEmail);
		
		JButton logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logInButton.setBounds(200, 220, 75, 20);
		
		panel.add(logInButton);
		
		//background image label icon.
		JLabel bgrdHome = new JLabel("New label");
		
		bgrdHome.setIcon(new ImageIcon("src\\resources\\background.png"));
		
		bgrdHome.setBounds(0, -12, 479, 346);
		
		panel.add(bgrdHome);
		
//-----------------------MY PROJECTS TAB----------------------------------
		
		// Panel for my project tabs
		JPanel myProjectsPanel = new JPanel();
		
		tabbedPane.addTab("My Projects", null, myProjectsPanel, null);
		
		myProjectsPanel.setLayout(null);
	
		JLabel lblDifficulty_1 = new JLabel("Difficulty:");
		
		lblDifficulty_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblDifficulty_1.setBounds(252, 102, 198, 23);
		
		myProjectsPanel.add(lblDifficulty_1);
		
		//difficulty slider
		JSlider difficultySlider = new JSlider();
		
		difficultySlider.setBackground(new Color(240, 240, 240));
		
		difficultySlider.setForeground(new Color(0, 128, 0));
		
		difficultySlider.setBounds(252, 123, 200, 20);
		
		myProjectsPanel.add(difficultySlider);
		
		
		
		
		//save project button
		JButton svButton = new JButton("Save Project");
		
		svButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}});
			
		svButton.setBackground(Color.LIGHT_GRAY);
		
		svButton.setForeground(new Color(0, 0, 0));
		
		svButton.setBounds(10, 68, 192, 33);
		
		myProjectsPanel.add(svButton);
		
		
		// Project Progress Bar
		JProgressBar progressBar = new JProgressBar();
		
		progressBar.setForeground(new Color(0, 100, 0));
		
		progressBar.setValue(20);
		
		progressBar.setBounds(24, 125, 206, 20);
		
		myProjectsPanel.add(progressBar);
	
		JLabel lblProgress = new JLabel("Progress:");
		
		lblProgress.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblProgress.setBounds(24, 102, 198, 23);
		
		myProjectsPanel.add(lblProgress);
		
		
		
		JLabel lblCurrentInvestment = new JLabel("Current Investment:");
		
		lblCurrentInvestment.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblCurrentInvestment.setBounds(212, 55, 198, 23);
		
		myProjectsPanel.add(lblCurrentInvestment);
		
		
		
		
		JLabel lblTitle = new JLabel("Title:");
		
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblTitle.setBounds(212, 0, 59, 23);
		
		myProjectsPanel.add(lblTitle);
		
		
		
		
		JLabel lblMaterial = new JLabel("Materials");
		
		lblMaterial.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblMaterial.setBounds(24, 156, 198, 23);
		
		myProjectsPanel.add(lblMaterial);
		
		
		
		// Load project button
		JButton ldButton = new JButton("Load Project..");
		
		ldButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		ldButton.setBackground(Color.LIGHT_GRAY);
		
		ldButton.setBounds(10, 18, 192, 33);
		
		myProjectsPanel.add(ldButton);
		
		
		
		
		titleField = new JTextField();
		titleField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		titleField.setBounds(212, 21, 244, 27);
		
		myProjectsPanel.add(titleField);
		
		titleField.setColumns(10);
		
		
		InvestmentField = new JTextField();
		InvestmentField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		InvestmentField.setBounds(212, 74, 244, 23);
		
		myProjectsPanel.add(InvestmentField);
		
		InvestmentField.setColumns(10);
		
		
		
		
		JTextArea materialsTextArea = new JTextArea();
		
		materialsTextArea.setBounds(24, 175, 432, 148);
		
		myProjectsPanel.add(materialsTextArea);
		
		
		// background for my Projects Tab.
		JLabel bgrdMyProjectsTab = new JLabel("New label");
		
		bgrdMyProjectsTab.setIcon(new ImageIcon("src\\resources\\background.png"));
		
		bgrdMyProjectsTab.setBounds(0, 0, 479, 334);
		
		myProjectsPanel.add(bgrdMyProjectsTab);
		
	
//-------------------Compare Projects Tab---------------------------		
		JPanel compareProjectsPanel = new JPanel();
		
		tabbedPane.addTab("Compare Projects", null, compareProjectsPanel, null);
		
		compareProjectsPanel.setLayout(null);
		
		
		// Load Project Button (left most compare project panel)
		JButton compareLoadButton1 = new JButton("Load Project..");
		compareLoadButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		compareLoadButton1.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		compareLoadButton1.setBounds(42, 23, 109, 23);
		
		compareProjectsPanel.add(compareLoadButton1);
		
		
		
		//Leftmost compare project panel 
		JPanel comparePanel1 = new JPanel();
		
		comparePanel1.setBounds(42, 43, 109, 244);
		
		compareProjectsPanel.add(comparePanel1);
		
		comparePanel1.setLayout(null);
		
		// left most progress bar
		JProgressBar progressBar_1 = new JProgressBar();
		
		progressBar_1.setBounds(10, 33, 89, 14);
		
		comparePanel1.add(progressBar_1);
		
		//left most slider
		JSlider slider_1 = new JSlider();
		
		slider_1.setBounds(10, 107, 89, 26);
		
		comparePanel1.add(slider_1);
		
		
		JLabel lblPaysForItself = new JLabel("Pays for itself in:");
		
		lblPaysForItself.setBounds(10, 197, 110, 14);
		
		comparePanel1.add(lblPaysForItself);
		
		
		comparePayOffField1 = new JTextField();
		
		comparePayOffField1.setBounds(10, 213, 86, 20);
		
		comparePanel1.add(comparePayOffField1);
		
		comparePayOffField1.setColumns(10);
		
		
		JLabel lblProgress_1 = new JLabel("Progress:");
		
		lblProgress_1.setBounds(29, 11, 91, 14);
		
		comparePanel1.add(lblProgress_1);
		
		
		JLabel label_3 = new JLabel("Difficulty:");
		
		label_3.setBounds(36, 90, 63, 14);
		
		comparePanel1.add(label_3);
		
		
		
		// background label icon
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(SystemColor.activeCaption);
		lblNewLabel_2.setBounds(0, 0, 109, 244);
		comparePanel1.add(lblNewLabel_2);
		
		
		//--------------2nd Compare Project tab (center)-------------------
		
		// center load project button
		JButton compareLoadButton2 = new JButton("Load Project...");
		
		compareLoadButton2.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		compareLoadButton2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		compareLoadButton2.setBounds(182, 23, 109, 23);
		
		compareProjectsPanel.add(compareLoadButton2);
		
		// Rightmost load project button
		JButton compareLoadButton3 = new JButton("Load Project...");
		
		compareLoadButton3.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		compareLoadButton3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		compareLoadButton3.setBounds(322, 23, 109, 23);
		
		compareProjectsPanel.add(compareLoadButton3);
		
		
		JPanel comparePanel2 = new JPanel();
		
		comparePanel2.setBounds(182, 43, 109, 244);
		
		compareProjectsPanel.add(comparePanel2);
		
		comparePanel2.setLayout(null);
		
		
		
		JProgressBar progressBar_2 = new JProgressBar();
		
		progressBar_2.setBounds(10, 33, 89, 14);
		
		comparePanel2.add(progressBar_2);
		
		
		JSlider slider_2 = new JSlider();
		
		slider_2.setBounds(10, 106, 89, 26);
		
		comparePanel2.add(slider_2);
		
		JLabel label = new JLabel("Pays for itself in:");
		
		label.setBounds(10, 197, 99, 14);
		
		comparePanel2.add(label);
		
		
		comparePayOffField2 = new JTextField();
		
		comparePayOffField2.setColumns(10);
		
		comparePayOffField2.setBounds(10, 213, 86, 20);
		
		comparePanel2.add(comparePayOffField2);
		
		
		JLabel lblProgress_2 = new JLabel("Progress:");
		
		lblProgress_2.setBounds(32, 11, 77, 14);
		
		comparePanel2.add(lblProgress_2);
		
		
		JLabel label_2 = new JLabel("Difficulty:");
		
		label_2.setBounds(32, 89, 67, 14);
		
		comparePanel2.add(label_2);
		
//--------------------------3rd compare project tab-----------------------------
		JPanel comparePanel3 = new JPanel();
		
		comparePanel3.setBounds(322, 43, 109, 244);
		
		compareProjectsPanel.add(comparePanel3);
		
		comparePanel3.setLayout(null);
		
		
		JProgressBar progressBar_3 = new JProgressBar();
		
		progressBar_3.setBounds(10, 33, 89, 14);
		
		comparePanel3.add(progressBar_3);
		
		
		
		JSlider slider_3 = new JSlider();
		
		slider_3.setBounds(10, 107, 89, 26);
		
		comparePanel3.add(slider_3);
		
		
		
		comparePayOffField3 = new JTextField();
		
		comparePayOffField3.setColumns(10);
		
		comparePayOffField3.setBounds(10, 213, 86, 20);
		
		comparePanel3.add(comparePayOffField3);
		
		
		
		JLabel label_1 = new JLabel("Pays for itself in:");
		
		label_1.setBounds(10, 197, 99, 14);
		
		comparePanel3.add(label_1);
		
		
		JLabel lblProgress_3 = new JLabel("Progress:");
		
		lblProgress_3.setBounds(28, 11, 71, 14);
		
		comparePanel3.add(lblProgress_3);
		
		
		
		JLabel lblDifficulty_3 = new JLabel("Difficulty:");
		
		lblDifficulty_3.setBounds(35, 90, 64, 14);
		
		comparePanel3.add(lblDifficulty_3);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		
		lblNewLabel_1.setIcon(new ImageIcon("src\\resources\\background.png"));
		
		lblNewLabel_1.setBounds(0, 0, 479, 334);
		
		compareProjectsPanel.add(lblNewLabel_1);
		
		
		//------------------ABOUT TAB----------------------
		// implement later
		
		JPanel panel_3 = new JPanel();
			
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		lblNewLabel.setIcon(new ImageIcon("src\\resources\\background.png"));
		
		lblNewLabel.setBounds(0, 0, 484, 362);
		
		frame.getContentPane().add(lblNewLabel);
		
	}
}
