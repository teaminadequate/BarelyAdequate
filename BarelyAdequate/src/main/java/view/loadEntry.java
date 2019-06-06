package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import model.Material;
import model.Project;
import model.User;

import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;

public class loadEntry {

	private JFrame frame;
	private JComboBox comboBox;
	private Project project;
	private User user;
	private GUI_START mainGUI;

	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					this.initialize"nguo();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	public loadEntry(GUI_START theGUI) {
		mainGUI = theGUI;
		user = mainGUI.getUser();
		initialize(user);
	}

	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 282, 260);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/** @author Nicole */
		List<Project> userProjects = user.getUserProjects();
		String[] projects = new String[userProjects.size()];
		for (int i = 0; i < projects.length; i++) {
			projects[i] = userProjects.get(i).getTitle();
		}

		comboBox = new JComboBox(projects);
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(42, 29, 199, 20);
		addScroller(comboBox);
		
		/** @author Nicole */
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String selectedProjectName = (String) comboBox.getSelectedItem();
				for (int i = 0; i < projects.length; i++) {
					Project currentProject = userProjects.get(i);
					if (currentProject.getTitle().equals(selectedProjectName)) {
						project = currentProject;
					}
					
				}

			}
		});
		
		frame.getContentPane().add(comboBox);

		JLabel lblPleaseSelectA = new JLabel("Please select a project:");
		lblPleaseSelectA.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		lblPleaseSelectA.setBounds(43, 0, 223, 38);
		frame.getContentPane().add(lblPleaseSelectA);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 434, 261);
		lblNewLabel.setIcon(new ImageIcon(loadEntry.class.getResource("/resources/background.png")));
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}

	public void addScroller(JComboBox<?> comboBox) {
		this.comboBox = comboBox;
		if (comboBox.getItemCount() == 0) {
			return;
		}
		Object value = comboBox.getUI().getAccessibleChild(comboBox, 0);
		if (!(value instanceof JPopupMenu)) {
			return;
		}
		JPopupMenu popupMenu = (JPopupMenu) value;

		JScrollPane scrollPane = (JScrollPane) popupMenu.getComponent(0);

		scrollPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	
	private void load() {
		mainGUI.setProject(project);
	}
}
