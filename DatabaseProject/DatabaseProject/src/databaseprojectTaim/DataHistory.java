package databaseprojectTaim;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class DataHistory extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataHistory frame = new DataHistory();
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
	public DataHistory() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 557, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 43, 496, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Employee History");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Connection connection= ConnectionManager.getConnection();
				Statement statement;
				ResultSet resultSet;
				try {
					String sql = "select name,hire_date, dept_name from dept1,empt where empt.dept_no = dept1.dept_no";
					statement=connection.prepareStatement(sql);
					resultSet = statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(25, 11, 146, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Vehicle History");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbURL = "jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa";
				String user = "mo1911057";
				String pass = "mo1911057";
				Statement statement;
				ResultSet resultSet;
				try {

					Connection connection= ConnectionManager.getConnection();
					String sql = "select name, model, next_main_date from empt, vehicle where empt.emp_no = vehicle.emp_no";
					statement=connection.prepareStatement(sql);
					resultSet = statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
				
		});
		btnNewButton_1.setBounds(181, 11, 155, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Project History");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbURL = "jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa";
				String user = "mo1911057";
				String pass = "mo1911057";
		
				Statement statement;
				ResultSet resultSet;
				try {
					Connection connection= ConnectionManager.getConnection();
					String sql = "select project_name,description,dept_name from project,dept_project,dept1 where dept_project.proj_id = project.project_id and dept1.dept_no = dept_project.dept_id";
					statement=connection.prepareStatement(sql);
					resultSet = statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(346, 10, 175, 23);
		contentPane.add(btnNewButton_2);
		

		JButton btnNewButton_4 = new JButton("Return");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage2 frame = new MainPage2();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(35, 239, 85, 21);
		contentPane.add(btnNewButton_4);
	}

}
