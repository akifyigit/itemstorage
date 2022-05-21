package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class itemlistpanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtsorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					itemlistpanel frame = new itemlistpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public itemlistpanel() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 20, 271, 177);
		contentPane.add(scrollPane);
		
		var db = new DBConnection();
		var rs = db.getList();
		var col = rs.getMetaData().getColumnCount(); 
		var model = new DefaultTableModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		
			for(int i=1;i<=col;i++) {
			model.addColumn(rs.getMetaData().getColumnName(i));
			}
			
		var row = new Object[col];
			while(rs.next())
			{
				for (int i = 1; i <= col; i++) 
					{
					row[i-1] = rs.getObject(i); 
					}
			model.addRow(row);
			}

		table = new JTable();
		table.setModel(model); 
		table.setRowSorter(sorter);
		scrollPane.setViewportView(table);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var item = new items();
					item.serialnumber=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					db.deleteitem(item.serialnumber);		// deleted from database			
					var rows = table.getSelectedRows();
						for(int i = 0; i < rows.length; i++) {
							model.removeRow(rows[i]-i); //deleted from table
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(36, 207, 114, 46);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			var item = new items();
			item.name=table.getValueAt(table.getSelectedRow(), 1).toString();
			item.type=table.getValueAt(table.getSelectedRow(), 2).toString();
			item.quantity=table.getValueAt(table.getSelectedRow(), 3).toString();
			var update = new updateitem();
			update.setVisible(true);
			dispose();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(171, 207, 114, 46);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			mainpanel main = new mainpanel();
			main.setVisible(true);
			dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(312, 207, 114, 46);
		contentPane.add(btnBack);
		
		
		
		txtsorter = new JTextField();
		txtsorter.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String text = txtsorter.getText();
				sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
			}
		});
		txtsorter.setBounds(307, 39, 119, 28);
		contentPane.add(txtsorter);
		txtsorter.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("          Sorter");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(307, 10, 119, 28);
		contentPane.add(lblNewLabel);
		
		
	}
}
