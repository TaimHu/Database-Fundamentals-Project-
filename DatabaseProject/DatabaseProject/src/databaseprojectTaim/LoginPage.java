package databaseprojectTaim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	Connection connection= null;
	Statement stm = null;
	ResultSet set = null;

	private JPanel contentPane;
	private JTextField usernametxt;
	private JTextField passwordtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 383, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(22, 52, 66, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(22, 85, 71, 13);
		contentPane.add(lblNewLabel_1);

		usernametxt = new JTextField();
		usernametxt.setBounds(98, 49, 96, 19);
		contentPane.add(usernametxt);
		usernametxt.setColumns(10);

		passwordtxt = new JTextField();
		passwordtxt.setBounds(98, 82, 96, 19);
		contentPane.add(passwordtxt);
		passwordtxt.setColumns(10);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					connection = ConnectionManager.getConnection();
					String sql="select * from Login where USERNAME = '" + usernametxt.getText() +"' and PASSWORD = '"+ passwordtxt.getText()+"'";
					stm = connection.createStatement();
					set = stm.executeQuery(sql);
					if(set.next()) {
						JOptionPane.showMessageDialog(null, "Login Successfull");
						MainPage2 frame = new MainPage2();
						frame.setVisible(true);
						
						String store ="insert into logged values( '" + usernametxt.getText() +"', "
								+ "(select emp_no from login where username = '" + usernametxt.getText() + "'))";
						stm = connection.createStatement();
						set = stm.executeQuery(store);
						
						dispose();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Login Failed");
					}

				}catch (Exception e){
					System.out.println(e);

				}

			}
		});
		btnNewButton.setBounds(20, 121, 71, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(117, 121, 77, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel padlockimg = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/padlock.png"));
		padlockimg.setIcon(img);
		padlockimg.setBounds(230, 44, 116, 101);
		contentPane.add(padlockimg);
	}
}
