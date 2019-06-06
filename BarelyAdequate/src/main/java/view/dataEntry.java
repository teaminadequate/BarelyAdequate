package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import model.User;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

public class dataEntry extends JFrame {

	private JFrame dataEntryFrame;
	private JLabel lblProjectTitle;
	private JLabel lblMaterialName;
	private JLabel lblCost;
	private JButton btnAdd;
	private JButton btnRemove;
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
	private User user;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dataEntry window = new dataEntry(new User("nicole67","nguob@uw.edu"));
					window.dataEntryFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public dataEntry(User theUser) {
		user = theUser;
		initialize(theUser);
	}

	private void initialize(User theUser) {
		dataEntryFrame = new JFrame();
		dataEntryFrame.setTitle("Please enter project data:");
		dataEntryFrame.setBounds(100, 100, 432, 282);
		dataEntryFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dataEntryFrame.getContentPane().setLayout(null);
		
		addTaskBtn = new JButton("Add\r\n");
		addTaskBtn.setForeground(Color.WHITE);
		addTaskBtn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));
		addTaskBtn.setBackground(Color.DARK_GRAY);
		addTaskBtn.setBounds(262, 109, 60, 20);
		dataEntryFrame.getContentPane().add(addTaskBtn);
		
		lblTask = new JLabel("Task:");
		lblTask.setForeground(Color.BLACK);
		lblTask.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblTask.setBounds(47, 130, 130, 20);
		dataEntryFrame.getContentPane().add(lblTask);
		
		difficultyLbl = new JLabel("Project Difficulty:");
		difficultyLbl.setForeground(Color.BLACK);
		difficultyLbl.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		difficultyLbl.setBounds(47, 183, 130, 20);
		dataEntryFrame.getContentPane().add(difficultyLbl);
		
		JSlider difficultySlider = new JSlider(1, 3);
		difficultySlider.setForeground(Color.GREEN);
		difficultySlider.setBackground(Color.DARK_GRAY);
		difficultySlider.setBounds(47, 206, 146, 18);
		dataEntryFrame.getContentPane().add(difficultySlider);
		
		removeTaskBtn = new JButton("Remove");
		removeTaskBtn.setForeground(Color.WHITE);
		removeTaskBtn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));
		removeTaskBtn.setBackground(Color.DARK_GRAY);
		removeTaskBtn.setBounds(319, 109, 80, 20);
		dataEntryFrame.getContentPane().add(removeTaskBtn);
		
		lblTaskName = new JLabel("Task Name:");
		lblTaskName.setForeground(Color.BLACK);
		lblTaskName.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblTaskName.setBounds(47, 89, 130, 20);
		dataEntryFrame.getContentPane().add(lblTaskName);
		
		JLabel lblCurrentBill = new JLabel("Current Bill:");
		lblCurrentBill.setForeground(Color.BLACK);
		lblCurrentBill.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 12));
		lblCurrentBill.setBounds(197, 183, 130, 20);
		dataEntryFrame.getContentPane().add(lblCurrentBill);
		
		currentBillField = new JTextField("$");
		currentBillField.setForeground(Color.WHITE);
		currentBillField.setColumns(10);
		currentBillField.setBackground(Color.DARK_GRAY);
		currentBillField.setBounds(197, 205, 202, 20);
		dataEntryFrame.getContentPane().add(currentBillField);
		
		taskNameField = new JTextField();
		taskNameField.setForeground(Color.WHITE);
		taskNameField.setColumns(10);
		taskNameField.setBackground(Color.DARK_GRAY);
		taskNameField.setBounds(47, 109, 211, 20);
		dataEntryFrame.getContentPane().add(taskNameField);
		
		taskDescriptionTextArea = new JTextArea();
		taskDescriptionTextArea.setForeground(Color.WHITE);
		taskDescriptionTextArea.setBackground(Color.DARK_GRAY);
		taskDescriptionTextArea.setBounds(47, 150, 352, 33);
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
		
		btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Berlin Sans FB Demi", Font.BOLD | Font.ITALIC, 11));
		btnRemove.setBackground(Color.DARK_GRAY);
		btnRemove.setBounds(319, 70, 80, 20);
		dataEntryFrame.getContentPane().add(btnRemove);
		
		btnAdd = new JButton("Add\r\n");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(dataEntry.class.getResource("/resources/background.png")));
		lblNewLabel.setBounds(0, -1, 459, 284);
		dataEntryFrame.getContentPane().add(lblNewLabel);
		dataEntryFrame.setVisible(true);
	}
}
