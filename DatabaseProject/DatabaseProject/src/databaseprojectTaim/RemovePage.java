package databaseprojectTaim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RemovePage extends JFrame {
	

	private JPanel contentPane;
	private JTextField empnotxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemovePage frame = new RemovePage();
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
	public RemovePage() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 356, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton removebutt = new JButton("Remove");
		removebutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection connection;
				PreparedStatement stmt;
				
				
				try {
					connection = ConnectionManager.getConnection();
					String sql = "delete from EMPT where EMP_NO="+empnotxt.getText();
					stmt=connection.prepareStatement(sql);
					int x = stmt.executeUpdate(sql);
					
					if(x !=0) {
					JOptionPane.showMessageDialog(null, "Employee removed.");}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Employee Number");}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Transaction failed");
					e.printStackTrace();
				}
				
			}
		});
		removebutt.setBounds(39, 84, 85, 21);
		contentPane.add(removebutt);
		
		empnotxt = new JTextField();
		empnotxt.setBounds(161, 30, 130, 19);
		contentPane.add(empnotxt);
		empnotxt.setColumns(10);
		
		JButton returnbutt = new JButton("Return");
		returnbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage2 frame = new MainPage2();
				frame.setVisible(true);
				dispose();
			}
		});
		returnbutt.setBounds(134, 84, 85, 21);
		contentPane.add(returnbutt);
	
		
		JLabel lblNewLabel = new JLabel("Employee Number");
		lblNewLabel.setBounds(39, 33, 123, 13);
		contentPane.add(lblNewLabel);
	}

}
