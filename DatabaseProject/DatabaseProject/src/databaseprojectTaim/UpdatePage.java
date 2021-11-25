package databaseprojectTaim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import com.toedter.calendar.JDateChooser;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.awt.event.ActionEvent;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ItemEvent;

public class UpdatePage extends JFrame{

	private JPanel contentPane;
	private JTextField empphonetxt;
	private JTextField empnametxt;
	private JTextField empsalarytxt;
	private JTextField empcomtxt;
	private JTextField currentdept;
	private JTextField currentpos;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UpdatePage frame = new UpdatePage();
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
	public UpdatePage() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 517, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(56, 84, 78, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setBounds(56, 107, 78, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(56, 130, 78, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Hire Date");
		lblNewLabel_4.setBounds(56, 153, 78, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Salary");
		lblNewLabel_5.setBounds(56, 214, 78, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Commission");
		lblNewLabel_6.setBounds(56, 237, 83, 13);
		contentPane.add(lblNewLabel_6);
		
		JRadioButton R1 = new JRadioButton("Male");
		R1.setBounds(182, 126, 77, 21);
		contentPane.add(R1);
		
		JRadioButton R2 = new JRadioButton("Female");
		R2.setBounds(257, 126, 78, 21);
		contentPane.add(R2);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(R1);
	    group.add(R2);

	    JXDatePicker hiredatepicker = new JXDatePicker();
		hiredatepicker.setBounds(182, 149, 134, 21);
		contentPane.add(hiredatepicker);
		
		JXDatePicker dateofbirthpicker = new JXDatePicker();
		dateofbirthpicker.setBounds(182, 180, 134, 21);
		contentPane.add(dateofbirthpicker);

		empphonetxt = new JTextField();
		empphonetxt.setBounds(182, 104, 134, 19);
		contentPane.add(empphonetxt);
		empphonetxt.setColumns(10);
		
		empnametxt = new JTextField();
		empnametxt.setBounds(182, 81, 134, 19);
		contentPane.add(empnametxt);
		empnametxt.setColumns(10);
		
		empsalarytxt = new JTextField();
		empsalarytxt.setBounds(182, 211, 134, 19);
		contentPane.add(empsalarytxt);
		empsalarytxt.setColumns(10);
		
		empcomtxt = new JTextField();
		empcomtxt.setBounds(182, 234, 134, 19);
		contentPane.add(empcomtxt);
		empcomtxt.setColumns(10);
		
		JLabel gradelabel = new JLabel("");
		gradelabel.setBounds(365, 211, 24, 19);
		gradelabel.setOpaque(true);
		gradelabel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(gradelabel);
		
		
		

		JComboBox comboBox = new JComboBox();
		
		try {
			Connection connection= ConnectionManager.getConnection();
			String sql="SELECT DEPT_NO FROM dept1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			comboBox.addItem("");
			while(rs.next()){
				int deptno = rs.getInt(1);
				comboBox.addItem(deptno);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		comboBox.setBounds(182, 263, 134, 21);
		contentPane.add(comboBox);
		
		
		JComboBox comboBox_1 = new JComboBox();
		try {
			Connection connection= ConnectionManager.getConnection();
			String sql="SELECT POS_ID FROM position";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			comboBox_1.addItem("");
			while(rs.next()){
				int posno = rs.getInt(1);
				comboBox_1.addItem(posno);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		comboBox_1.setBounds(182, 294, 134, 21);
		contentPane.add(comboBox_1);
		
		
		JComboBox employeecombo = new JComboBox();
		employeecombo.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				Integer temp = (Integer) employeecombo.getSelectedItem();
				String sql = "select * from EMPT where EMP_NO=?";
				
				try {
					Connection connection= ConnectionManager.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, temp);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						String add1 = rs.getString("NAME");
						empnametxt.setText(add1);
						String add2 = rs.getString("PHONE");
						empphonetxt.setText(add2);
						String add3 = rs.getString("GENDER");
						if(add3 == "M") {
							R1.setSelected(true);
						}
						if(add3 == "F") {
							
						}
						Date add4 = rs.getDate("HIRE_DATE");
						hiredatepicker.setDate(add4);
						Date add5 = rs.getDate("DOB");
						dateofbirthpicker.setDate(add5);
						String add6 = rs.getString("SALARY");
						empsalarytxt.setText(add6);
						String add7 = rs.getString("COMMISSION");
						empcomtxt.setText(add7);
						String add10 = rs.getString("GRADE");
						gradelabel.setText(add10);
						String add8 = rs.getString("DEPT_NO");
						currentdept.setText(add8);
						String add9 = rs.getString("POSITIONID");
						currentpos.setText(add9);
						
					}
					
					
					
				}catch (Exception e) {
					System.out.println(e);
				}
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		try {
			Connection connection= ConnectionManager.getConnection();
			String sql="SELECT EMP_NO FROM EMPT";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			employeecombo.addItem("");
			while(rs.next()){
				int posno = rs.getInt(1);
				employeecombo.addItem(posno);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		employeecombo.setBounds(182, 44, 134, 21);
		contentPane.add(employeecombo);
		
		JButton savebutt = new JButton("Update");
		savebutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String g = null;
				if(R1.isSelected()) {
					g = "M";
				}
				if(R2.isSelected()) {
					g = "F";
				}
				
				Connection connection= ConnectionManager.getConnection();
				try {
				

					String grade = null;
					gradelabel.setText("");
					
					String query = "update Empt set NAME=?,PHONE=?,GENDER=?,HIRE_DATE=?,DOB=?,SALARY=?,COMMISSION=?,GRADE=?,DEPT_NO=?,POSITIONID=? where EMP_NO=?";
						
					PreparedStatement ps = connection.prepareStatement(query);
					
					if(Integer.parseInt(empsalarytxt.getText()) >= 1000 && Integer.parseInt(empsalarytxt.getText()) <10000)
					{
						grade = "E";
						gradelabel.setText(grade);
					}	
					if(Integer.parseInt(empsalarytxt.getText()) >= 10000 && Integer.parseInt(empsalarytxt.getText()) <20000) {
							grade = "D";
							gradelabel.setText(grade);
					}
					if(Integer.parseInt(empsalarytxt.getText()) >= 20000 && Integer.parseInt(empsalarytxt.getText()) <50000) {
								grade = "C";
								gradelabel.setText(grade);
					}
					if(Integer.parseInt(empsalarytxt.getText()) >= 30000 && Integer.parseInt(empsalarytxt.getText()) <40000) {
									grade = "B";
									gradelabel.setText(grade);
					}
					if(Integer.parseInt(empsalarytxt.getText()) >= 40000 && Integer.parseInt(empsalarytxt.getText()) <=50000 ) {
										grade = "A";
										gradelabel.setText(grade);
					}					
									
					

					ps.setString(1, empnametxt.getText());
					ps.setInt(2, Integer.parseInt(empphonetxt.getText()));
					ps.setString(3, g);
					ps.setDate(4,new java.sql.Date(hiredatepicker.getDate().getTime()));
					ps.setDate(5,new java.sql.Date(dateofbirthpicker.getDate().getTime()));
					ps.setInt(6, Integer.parseInt(empsalarytxt.getText()));
					ps.setInt(7, Integer.parseInt(empcomtxt.getText()));
					ps.setString(8, grade);
					
					
					String value1 = comboBox.getSelectedItem().toString();
					
					String value2 = comboBox_1.getSelectedItem().toString();
					
					String value3 = employeecombo.getSelectedItem().toString();
					
					System.out.println(value3);
					
					ps.setInt(9,Integer.parseInt(value1));
					ps.setInt(10,Integer.parseInt(value2));
					ps.setInt(11,Integer.parseInt(value3));
					
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Transaction successfull");
					UpdatePage frame = new UpdatePage();
					frame.setVisible(true);
					dispose();
				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Transaction failed");
					e.printStackTrace();
				}
				
				
			}
		});
		savebutt.setBounds(56, 343, 78, 21);
		contentPane.add(savebutt);
		
		
		JButton mainpagebutt = new JButton("Return");
		mainpagebutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainPage2 frame = new MainPage2();
				frame.setVisible(true);
				dispose();
			}
		});
		mainpagebutt.setBounds(144, 343, 78, 21);
		contentPane.add(mainpagebutt);
	
		
		JLabel lblNewLabel_7 = new JLabel("Date of Birth");
		lblNewLabel_7.setBounds(56, 184, 95, 13);
		contentPane.add(lblNewLabel_7);
		
		
		JLabel lblNewLabel_8 = new JLabel("Department No");
		lblNewLabel_8.setBounds(56, 267, 116, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Position ID");
		lblNewLabel_9.setBounds(56, 298, 116, 13);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Grade");
		lblNewLabel_10.setBounds(326, 214, 45, 13);
		contentPane.add(lblNewLabel_10);
		
		JLabel label = new JLabel("New label");
		label.setBounds(182, 263, 45, 13);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Employee No");
		lblNewLabel.setBounds(56, 48, 116, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_11 = new JLabel("Current Dept");
		lblNewLabel_11.setBounds(326, 267, 83, 13);
		contentPane.add(lblNewLabel_11);
		
		currentdept = new JTextField();
		currentdept.setBounds(417, 264, 32, 19);
		contentPane.add(currentdept);
		currentdept.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Current Pos");
		lblNewLabel_12.setBounds(326, 298, 83, 13);
		contentPane.add(lblNewLabel_12);
		
		currentpos = new JTextField();
		currentpos.setColumns(10);
		currentpos.setBounds(417, 295, 32, 19);
		contentPane.add(currentpos);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdatePage frame = new UpdatePage();
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnRefresh.setBounds(232, 343, 84, 21);
		contentPane.add(btnRefresh);
		
		
		
		
		
		
		
			}

	
}
