package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.Material;
import model.Project;
import model.User;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

/**
*@Author Casey Hogan
*Allows you to add and edit data for a given project.
*/
public class dataEntry extends JFrame {
        
	private JFrame dataEntryFrame;
	private JLabel lblProjectTitle;
	private JLabel lblMaterialName;
	private JLabel lblCost;
	private JButton btnAdd;
	private JButton materialRemoveButton;
	private JTextField projectNameField;
	private JTextField materialNameField;
	private JTextField costField;
	private JTextArea taskDescriptionTextArea;
	private JTextField taskNameField;
	private JLabel lblTaskName;
	private JLabel lblTask;
	private JButton addTaskBtn;
	private JButton removeTaskBtn;
	private JLabel difficultyLbl;
	private JTextField currentBillField;
	private JTextField projectedNewBillField;
	private JSlider difficultySlider;
	private User user;
	private Project project;
	private GUI_START mainGUI;

	public dataEntry(GUI_START theGUI) {		
		this(theGUI, new Project());
	}

	public dataEntry(GUI_START theGUI, Project theProject) {		
		mainGUI = theGUI;
		project = theProject;
		user = theGUI.getUser();
		initialize();
	}

	/**
	* @Author Casey Hogan
	* Initializes the JFrame with all
	*/
	public void initialize() {
		
		
		dataEntryFrame = new JFrame();
		
		
		dataEntryFrame.setTitle("Please enter project data:");
		dataEntryFrame.setBounds(100, 100, 432, 282);
		dataEntryFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dataEntryFrame.getContentPane().setLayout(null);
		
	
		lblTask = new JLabel("Tasks:");
		lblTask.setForeground(Color.BLACK);
		lblTask.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblTask.setBounds(47, 95, 130, 20);
		dataEntryFrame.getContentPane().add(lblTask);
		
		difficultyLbl = new JLabel("Project Difficulty:");
		difficultyLbl.setForeground(Color.BLACK);
		difficultyLbl.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		difficultyLbl.setBounds(47, 183, 100, 20);
		dataEntryFrame.getContentPane().add(difficultyLbl);
		
		difficultySlider = new JSlider(0, 10);
		difficultySlider.setMajorTickSpacing(1);
		difficultySlider.setSnapToTicks(true);
		difficultySlider.setForeground(Color.GREEN);
		difficultySlider.setBackground(Color.DARK_GRAY);
		difficultySlider.setBounds(47, 206, 146, 18);
		dataEntryFrame.getContentPane().add(difficultySlider);
		
		
		JLabel lblCurrentBill = new JLabel("Current Bill:");
		lblCurrentBill.setForeground(Color.BLACK);
		lblCurrentBill.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblCurrentBill.setBounds(47, 145, 130, 20);
		dataEntryFrame.getContentPane().add(lblCurrentBill);
		
		currentBillField = new JTextField("$");
		currentBillField.setForeground(Color.WHITE);
		currentBillField.setColumns(10);
		currentBillField.setBackground(Color.DARK_GRAY);
		currentBillField.setBounds(47, 163, 150, 20);
		dataEntryFrame.getContentPane().add(currentBillField);
		
		JLabel projectedNewBillLabel = new JLabel("Projected New Bill:");
		projectedNewBillLabel.setForeground(Color.BLACK);
		projectedNewBillLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		projectedNewBillLabel.setBounds(200, 145, 130, 20);
		dataEntryFrame.getContentPane().add(projectedNewBillLabel);
		
		projectedNewBillField = new JTextField("$");
		projectedNewBillField.setForeground(Color.WHITE);
		projectedNewBillField.setColumns(10);
		projectedNewBillField.setBackground(Color.DARK_GRAY);
		projectedNewBillField.setBounds(200, 163, 200, 20);
		dataEntryFrame.getContentPane().add(projectedNewBillField);
		
		
		taskDescriptionTextArea = new JTextArea();
		taskDescriptionTextArea.setForeground(Color.WHITE);
		taskDescriptionTextArea.setBackground(Color.DARK_GRAY);
		taskDescriptionTextArea.setBounds(47, 115, 352, 33);
		dataEntryFrame.getContentPane().add(taskDescriptionTextArea);
		
		costField = new JTextField("$");
		costField.setForeground(Color.WHITE);
		costField.setColumns(10);
		costField.setBackground(Color.DARK_GRAY);
		costField.setBounds(187, 70, 71, 20);
		dataEntryFrame.getContentPane().add(costField);
		
		materialNameField = new JTextField();
		materialNameField.setForeground(Color.WHITE);
		materialNameField.setColumns(10);
		materialNameField.setBackground(Color.DARK_GRAY);
		materialNameField.setBounds(47, 70, 136, 20);
		dataEntryFrame.getContentPane().add(materialNameField);
		
		projectNameField = new JTextField();
		projectNameField.setForeground(Color.WHITE);
		projectNameField.setBackground(Color.DARK_GRAY);
		projectNameField.setBounds(47, 29, 352, 20);
		dataEntryFrame.getContentPane().add(projectNameField);
		projectNameField.setColumns(10);
		
		materialRemoveButton = new JButton("Remove");
		materialRemoveButton.setForeground(Color.WHITE);
		materialRemoveButton.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));
		materialRemoveButton.setBackground(Color.DARK_GRAY);
		materialRemoveButton.setBounds(319, 70, 80, 20);
		dataEntryFrame.getContentPane().add(materialRemoveButton);
		
		
		materialRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				project.removeMaterial(materialNameField.getText());
			}			
		});
		//Casey
		btnAdd = new JButton("Add\r\n");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				project.addMaterial(new Material(materialNameField.getText(), 
						Double.parseDouble(costField.getText().replace("$", ""))));	
				materialNameField.setText("");
				costField.setText("$");
			}
		});
		//Casey
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setBounds(262, 70, 60, 20);
		dataEntryFrame.getContentPane().add(btnAdd);
		
		lblCost = new JLabel("Cost:");
		lblCost.setForeground(Color.BLACK);
		lblCost.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblCost.setBounds(187, 51, 130, 20);
		dataEntryFrame.getContentPane().add(lblCost);
		
		lblMaterialName = new JLabel("Material Name:");
		lblMaterialName.setForeground(Color.BLACK);
		lblMaterialName.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblMaterialName.setBounds(47, 51, 130, 20);
		dataEntryFrame.getContentPane().add(lblMaterialName);
		
		lblProjectTitle = new JLabel("Project Title:");
		lblProjectTitle.setForeground(Color.BLACK);
		lblProjectTitle.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 14));
		lblProjectTitle.setBounds(47, 11, 130, 20);
		dataEntryFrame.getContentPane().add(lblProjectTitle);
		
		
		
		
		if(!project.getTitle().equals("")) {
			doPreset();
		}
		
		//Casey
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				var _tasks = Arrays.asList(taskDescriptionTextArea.getText().split("\n"));
				var tasks = new ArrayList<String>();
				for(var task : _tasks){
					tasks.add(task);
				}
				project.setProcedure(tasks);
				project.setCurrentBill(Double.parseDouble(currentBillField.getText().replace("$", "")));
				project.setProjectedBill(Double.parseDouble(projectedNewBillField.getText().replace("$", "")));
				project.setTitle(projectNameField.getText());
				project.setDiff(difficultySlider.getValue());
				try {
					user.addProject(project);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				mainGUI.setProject(project);
				mainGUI.aProjectHasBeenLoaded();
				dataEntryFrame.setVisible(false);
			}
		});
		//Casey
		saveButton.setBounds(225, 195, 140, 35);
		saveButton.setBackground(Color.DARK_GRAY);
		saveButton.setForeground(Color.WHITE);
		
		
		dataEntryFrame.getContentPane().add(saveButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(dataEntry.class.getResource("/resources/background.png")));
		lblNewLabel.setBounds(0, -1, 459, 284);
		dataEntryFrame.getContentPane().add(lblNewLabel);
		dataEntryFrame.setVisible(true);
		
		
	}
	/**
	 * @author Gavin Montes
	 * If we are editing a project, set preset the values in the fields to the project's values.
	 */
	private void doPreset() {
		projectNameField.setText(project.getTitle());
		String tasks = "";
		for(String s : project.getProcedure()) {
			tasks = tasks + s + "\n";
		}
		taskDescriptionTextArea.setText(tasks);
		currentBillField.setText(Double.toString(project.getBill().getCurrentBill()));
		projectedNewBillField.setText(Double.toString(project.getBill().getProjectedBill()));
		difficultySlider.setValue(project.getDiff());
	}
}
