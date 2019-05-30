package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadEntry window = new loadEntry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public loadEntry() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 282, 260);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String[] projects = new String[100];
		for(int i = 0; i < 100; i++) {
			projects[i] = "Project " + i;
		}
		
		 comboBox = new JComboBox(projects);
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(42, 29, 199, 20);
		addScroller(comboBox);
		frame.getContentPane().add(comboBox);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a project:");
		lblPleaseSelectA.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 14));
		lblPleaseSelectA.setBounds(43, 0, 223, 38);
		frame.getContentPane().add(lblPleaseSelectA);

		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 434, 261);
		lblNewLabel.setIcon(new ImageIcon(loadEntry.class.getResource("/resources/background.png")));
		frame.getContentPane().add(lblNewLabel);
		
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
}
