package databaseprojectTaim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class EmployeeAllowance extends JFrame {

	private JPanel contentPane;
	private JTextField employeeNumber;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAllowance frame = new EmployeeAllowance();
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
	public EmployeeAllowance() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Employee Number: ");
		lblNewLabel.setBounds(28, 40, 119, 25);
		contentPane.add(lblNewLabel);

		employeeNumber = new JTextField();
		employeeNumber.setBounds(157, 41, 133, 23);
		contentPane.add(employeeNumber);
		employeeNumber.setColumns(10);

		JButton employeeNumberSearch = new JButton("Search");
		employeeNumberSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				Statement statement;
				ResultSet resultSet;
				try {
					Connection connection= ConnectionManager.getConnection();
					String sql = "select allow_name from allowance, position_allowance where position_allowance.allowance_id =  allowance.a_id and position_allowance.position_id=(select pos_id from position where pos_id = (select POSITIONID from empt where emp_no ="+Integer.parseInt(employeeNumber.getText())+"))";
					statement=connection.prepareStatement(sql);
					resultSet = statement.executeQuery(sql);
	
					if(resultSet.isBeforeFirst()) {
						table.setModel(DbUtils.resultSetToTableModel(resultSet));
					}
					else {
						JOptionPane.showMessageDialog(null, "The employee number is either not found or There are no allowances for this employee");
						
					}
						
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		employeeNumberSearch.setBounds(300, 41, 89, 23);
		contentPane.add(employeeNumberSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 414, 146);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage2 frame = new MainPage2();
				frame.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(28, 227, 89, 23);
		contentPane.add(btnReturn);
	}
}
