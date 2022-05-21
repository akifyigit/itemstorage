package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class mainpanel extends JFrame {
	
JLabel lblHello;
public void setLabel(String label) {
	lblHello.setText("Hello "+label);
}
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpanel frame = new mainpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblHello = new JLabel();
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHello.setBounds(38, 10, 129, 47);
		contentPane.add(lblHello);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItemPanel additem = new AddItemPanel();
				additem.setVisible(true);
				dispose();
			}
		});
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddItem.setBounds(143, 82, 129, 72);
		contentPane.add(btnAddItem);
		
		JButton btnListItems = new JButton("List Items");
		btnListItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemlistpanel list;
				try {
					list = new itemlistpanel();
					list.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnListItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListItems.setBounds(143, 164, 129, 72);
		contentPane.add(btnListItems);
	}

}
