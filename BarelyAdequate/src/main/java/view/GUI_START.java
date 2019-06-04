package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.databaseManager;
import model.Project;
import model.User;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.Button;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

public class GUI_START {

	/** the JFrame... */
	private JFrame frame;

	/** Username enter field */
	private JTextField nameField;

	/** password field */
	private JTextField emailField;

	/** The loaded user. */
	private User loadedUser;

	/** The loaded project. */
	private Project loadedProject;

	private databaseManager dbm;

	/** Project title field (my Projects tab */
	private JTextField titleField;

	/** Total investment field (my Projects tab */
	private JTextField InvestmentField;
	private JTextField projectedBillField;
	private JTextField CurrentBillField;

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

		dbm = new databaseManager();
		// Setup Frame
		frame = new JFrame();

		frame.setBounds(100, 100, 500, 401);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);

		tabbedPanel.setBounds(0, 0, 484, 362);

		frame.getContentPane().add(tabbedPanel);

		/* Panel for home tab and field entries. */
		JPanel panel = new JPanel();

		tabbedPanel.addTab("Home", null, panel, null);

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

		// label to show the loaded user
		JLabel loadedUserLabel = new JLabel();
		loadedUserLabel.setVisible(false);
		loadedUserLabel.setBounds(10, 0, 200, 40);
		panel.add(loadedUserLabel);

		JButton logInButton = new JButton("Log In");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadedUser = new User(nameField.getText(), emailField.getText());
				} catch (SQLException sqlE) {
					sqlE.printStackTrace();
				} catch (ClassNotFoundException cnfE) {
					cnfE.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "Welcome, " + loadedUser.getName() + " you are logged in.");
				loadedUserLabel.setText("Logged In: " + loadedUser.getName());
				loadedUserLabel.setVisible(true);
			}
		});
		logInButton.setBounds(200, 220, 75, 20);

		panel.add(logInButton);

		// background image label icon.
		JLabel bgrdHome = new JLabel("New label");

		bgrdHome.setIcon(new ImageIcon(GUI_START.class.getResource("/resources/background.png")));

		bgrdHome.setBounds(0, -12, 479, 346);

		panel.add(bgrdHome);

//-----------------------MY PROJECTS TAB----------------------------------

		// Panel for my project tabs
		JPanel myProjectsPanel = new JPanel(null);

		tabbedPanel.addTab("My Projects", null, myProjectsPanel, null);

		JLabel lblTitle = new JLabel("Title would go here");

		lblTitle.setForeground(Color.DARK_GRAY);

		lblTitle.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 15));

		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);

		lblTitle.setBounds(50, 22, 223, 36);

		JLabel lblPostBill = new JLabel("Projected Bill:");

		lblPostBill.setForeground(Color.BLACK);

		lblPostBill.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 12));

		lblPostBill.setBounds(50, 58, 130, 20);

		myProjectsPanel.add(lblTitle);

		myProjectsPanel.add(lblPostBill);

		JLabel lblCurrentBill = new JLabel("Current Bill:");

		lblCurrentBill.setForeground(Color.BLACK);

		lblCurrentBill.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 12));

		lblCurrentBill.setBackground(Color.LIGHT_GRAY);

		lblCurrentBill.setBounds(50, 96, 171, 20);

		myProjectsPanel.add(lblCurrentBill);

		JLabel lblProcedure = new JLabel("Tasks:");

		lblProcedure.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 14));

		lblProcedure.setBackground(Color.BLACK);

		lblProcedure.setBounds(47, 189, 226, 10);

		myProjectsPanel.add(lblProcedure);

		JLabel lblMaterials = new JLabel("Materials:");

		lblMaterials.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 14));

		lblMaterials.setBounds(256, 35, 223, 10);

		JButton newProjectButton = new JButton("New Project");
		newProjectButton.addActionListener(action -> {
			dataEntry entry = new dataEntry();
			dataEntry.main(new String[0]);
		});

		newProjectButton.setForeground(Color.WHITE);

		newProjectButton.setBackground(Color.DARK_GRAY);

		newProjectButton.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));

		newProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnLoadProject = new JButton("Load Project");
		btnLoadProject.addActionListener(action -> {
			loadEntry.main(new String[0]);
		});

		projectedBillField = new JTextField();
		projectedBillField.setColumns(10);
		projectedBillField.setBackground(Color.DARK_GRAY);
		projectedBillField.setBounds(50, 77, 189, 20);
		myProjectsPanel.add(projectedBillField);

		CurrentBillField = new JTextField();
		CurrentBillField.setColumns(10);
		CurrentBillField.setBackground(Color.DARK_GRAY);
		CurrentBillField.setBounds(50, 116, 189, 20);
		myProjectsPanel.add(CurrentBillField);
		btnLoadProject.setForeground(Color.WHITE);

		btnLoadProject.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));

		btnLoadProject.setBackground(Color.DARK_GRAY);

		btnLoadProject.setBounds(145, 0, 165, 23);

		myProjectsPanel.add(btnLoadProject);

		JButton btnEditProject = new JButton("Edit Project");

		btnEditProject.addActionListener(action -> {
			dataEntry.main(new String[0]);
		});

		JTextArea tasksTextArea = new JTextArea();

		tasksTextArea.setForeground(Color.WHITE);

		tasksTextArea.setBackground(Color.DARK_GRAY);

		tasksTextArea.setBounds(50, 200, 189, 123);

		myProjectsPanel.add(tasksTextArea);

		JTextArea materialsTextArea = new JTextArea();

		materialsTextArea.setForeground(Color.WHITE);

		materialsTextArea.setBackground(Color.DARK_GRAY);

		materialsTextArea.setBounds(249, 49, 220, 274);

		myProjectsPanel.add(materialsTextArea);

		btnEditProject.setForeground(new Color(255, 255, 255));

		btnEditProject.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));

		btnEditProject.setBackground(Color.DARK_GRAY);

		btnEditProject.setBounds(304, 0, 175, 23);
		myProjectsPanel.add(btnEditProject);

		newProjectButton.setBounds(0, 0, 152, 23);

		myProjectsPanel.add(newProjectButton);

		myProjectsPanel.add(lblMaterials);

		JLabel lblDiff = new JLabel("Difficulty:");

		lblDiff.setBackground(Color.LIGHT_GRAY);

		lblDiff.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 12));

		lblDiff.setForeground(Color.BLACK);

		lblDiff.setBounds(50, 137, 171, 20);

		JSlider diffSlider = new JSlider(1, 3);

		diffSlider.setForeground(Color.GREEN);

		diffSlider.setBackground(Color.DARK_GRAY);

		diffSlider.setBounds(50, 158, 189, 20);

		myProjectsPanel.add(lblDiff);

		myProjectsPanel.add(diffSlider);

		JLabel lblNewLabel_3 = new JLabel("");

		lblNewLabel_3.setIcon(new ImageIcon(GUI_START.class.getResource("/resources/background.png")));
		lblNewLabel_3.setBounds(0, 0, 490, 334);
		myProjectsPanel.add(lblNewLabel_3);

		// ------------------ABOUT TAB----------------------
		// implement later

		JPanel panel_3 = new JPanel();

		tabbedPanel.addTab("About", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Kyle Bittner");
		lblNewLabel_4.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(191, 171, 274, 33);
		panel_3.add(lblNewLabel_4);

		JLabel lblCaseyHogan = new JLabel("Casey Hogan");
		lblCaseyHogan.setForeground(new Color(0, 0, 0));
		lblCaseyHogan.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 18));
		lblCaseyHogan.setBounds(191, 125, 274, 33);
		panel_3.add(lblCaseyHogan);

		JLabel lblGavinMontes = new JLabel("Gavin Montes");
		lblGavinMontes.setForeground(new Color(0, 0, 0));
		lblGavinMontes.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 18));
		lblGavinMontes.setBounds(181, 215, 274, 33);
		panel_3.add(lblGavinMontes);

		JLabel lblNicoleGuobadia = new JLabel("Nicole Guobadia");
		lblNicoleGuobadia.setForeground(new Color(0, 0, 0));
		lblNicoleGuobadia.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 18));
		lblNicoleGuobadia.setBounds(170, 259, 274, 33);
		panel_3.add(lblNicoleGuobadia);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(GUI_START.class.getResource("/resources/title2.png")));
		lblNewLabel_1.setBounds(0, 0, 479, 90);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 0, 51));
		lblNewLabel.setBounds(0, 89, 479, 245);
		panel_3.add(lblNewLabel);

	}
}
