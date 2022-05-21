package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateitem extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txttype;
	private JTextField txtquantity;
	private JTextField txtserialnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateitem frame = new updateitem();
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
	public updateitem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 49, 122, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(10, 86, 122, 27);
		contentPane.add(lblType);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(10, 123, 122, 27);
		contentPane.add(lblQuantity);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtname.setBounds(148, 49, 131, 27);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txttype = new JTextField();
		txttype.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttype.setColumns(10);
		txttype.setBounds(148, 86, 131, 27);
		contentPane.add(txttype);
		
		txtquantity = new JTextField();
		txtquantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtquantity.setColumns(10);
		txtquantity.setBounds(148, 123, 131, 27);
		contentPane.add(txtquantity);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items item = new items();
				item.serialnumber=Integer.parseInt(txtserialnumber.getText().toString());
				item.name=txtname.getText();
				item.type=txttype.getText();
				item.quantity=txtquantity.getText();
				DBConnection db = new DBConnection();
				try {
					db.updateitem(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Item updated!");
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(148, 175, 131, 34);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemlistpanel itemlist;
				try {
					itemlist = new itemlistpanel();
					itemlist.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(148, 219, 131, 34);
		contentPane.add(btnBack);
		
		JLabel lblSerialNumber = new JLabel("Serial Number");
		lblSerialNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerialNumber.setBounds(10, 10, 122, 27);
		contentPane.add(lblSerialNumber);
		
		txtserialnumber = new JTextField();
		txtserialnumber.setColumns(10);
		txtserialnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtserialnumber.setBounds(148, 10, 131, 27);
		contentPane.add(txtserialnumber);
	}
}
