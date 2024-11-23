package project;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.*;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

//public class SignUpPage extends JPanel{
public class SignUpPage extends JFrame{
//	private JTextField firstName, lastName, country, langauge = new JTextField();
	private String[] titles = {"Male", "Female"};
	private JComboBox title = new JComboBox(titles); 
	private JPasswordField password = new JPasswordField(); 
	private JPasswordField confPassword = new JPasswordField(); 
	private JButton submit = new JButton("Create your Miles& Smiles account");
	private JTextField firstName = new JTextField();
	private JTextField lastName = new JTextField();
	private JTextField nationality = new JTextField();
	private JTextField langauge = new JTextField();
	private JTextField country = new JTextField();
	private JTextField city = new JTextField();
	private JTextField zipCode = new JTextField();
	private JTextField address = new JTextField();
	private JTextField email = new JTextField();
	private JTextField code = new JTextField();
	private JTextField phoneNumber = new JTextField();
	
	public SignUpPage() {
		
		JPanel mainPanel = new JPanel();
		JTextField[] all_inpus = {firstName, lastName, nationality, langauge, country, city, zipCode, address, email, code , phoneNumber};
		JPasswordField[] passwords = {password, confPassword};

		
		//personal info panel
		setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		
		//personal info header
		JLabel header = new JLabel("Miles & Smiles Membership Enrollment Details", SwingConstants.LEFT);
		header.setForeground(new Color(103,114, 127));
		header.setFont(new Font("SansSerif", Font.BOLD, 22));
		
		// date picker part
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBackground(Color.WHITE);
		
		JComponent[] inputs = {firstName, lastName, nationality, langauge, datePicker, title};
		String[] inputNames = {"First Name", "Last Name", "nationality", "Langauge", "Date of birth", "Title"};
		JPanel PersonalInfoPanel = new JPanel(new BorderLayout(10,10));
		JPanel groupOnePanel = new JPanel(new GridLayout(3,2,5,5));
		JPanel SecurityPanel = new JPanel(new BorderLayout(5,10));
		JPanel contactPanelInner = new JPanel(new GridLayout(4,2,5,5));
		contactPanelInner.setBackground(Color.WHITE);
		groupOnePanel.setBackground(Color.WHITE);
		
		for(int i = 0; i < inputs.length; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			inputs[i].setPreferredSize(new Dimension(400, 50));
			panel.add(new JLabel(inputNames[i]), BorderLayout.NORTH);
			panel.add(inputs[i], BorderLayout.CENTER);
			panel.setBackground(Color.WHITE);
			groupOnePanel.add(panel);
		}
		
		PersonalInfoPanel.add(header, BorderLayout.NORTH);
		PersonalInfoPanel.add(groupOnePanel, BorderLayout.SOUTH);
		add(PersonalInfoPanel);
		
		
		// contact inputs and panel
		
		//contact info header
		JPanel contactPanel = new JPanel(new BorderLayout(10,10));
		JLabel header2 = new JLabel("Contact details", JLabel.LEFT);
		
		header2.setForeground(new Color(103,114, 127));
		header2.setFont(new Font("SansSerif", Font.BOLD, 22));
		
		contactPanel.add(header2, BorderLayout.NORTH);
		
		JTextField[] inputs2 = {country, city, zipCode, address, email, code, phoneNumber};
		String[] inputNames2 = {"Country", "City", "Zip/Postal Code", "Address", "Email", "Code", "Phone number"};
		for(int i = 0; i < inputs2.length; i++) {
			JPanel panel = new JPanel(new BorderLayout(10,10));
//			panel.setPreferredSize(new Dimension(800, 50));
			inputs2[i].setPreferredSize(new Dimension(400, 50));
			panel.add(new JLabel(inputNames2[i]), BorderLayout.NORTH);
			panel.add(inputs2[i], BorderLayout.CENTER);
			panel.setBackground(Color.WHITE);
			contactPanelInner.add(panel);
		}
		
		add(contactPanel);
		
		// contact inputs and panel
		JPanel sec1 = new JPanel(new BorderLayout(10,10));
		JPanel sec2 = new JPanel(new BorderLayout(10,10));

		// security info header
		JLabel header3 = new JLabel("Security", JLabel.LEFT);		
		header3.setForeground(new Color(103,114, 127));
		header3.setFont(new Font("SansSerif", Font.BOLD, 22));
		
		password.setPreferredSize(new Dimension(400, 50));
		confPassword.setPreferredSize(new Dimension(400, 50));
		
		sec1.add(new JLabel("Create password"), BorderLayout.NORTH);
		sec1.add(password, BorderLayout.SOUTH);
		sec1.setBackground(Color.WHITE);
		sec2.add(new JLabel("Confirm password"), BorderLayout.NORTH);
		sec2.add(confPassword, BorderLayout.SOUTH);
		sec2.setBackground(Color.WHITE);
		
		SecurityPanel.add(header3, BorderLayout.NORTH);
		SecurityPanel.add(sec1, BorderLayout.WEST);
		SecurityPanel.add(sec2, BorderLayout.EAST);
		contactPanel.add(contactPanelInner,  BorderLayout.SOUTH);
		
		add(SecurityPanel);
		
		submit.setPreferredSize(new Dimension(500, 50));
		submit.setBackground(new Color(215,53, 59));
		submit.setForeground(Color.WHITE);
		submit.setOpaque(true);
		submit.setFocusPainted(false);
		submit.setBorderPainted(false);
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
//		SubmitListener listener1 = new SubmitListener(all_inpus, passwords, title, datePicker);
		
		submit.addActionListener(new SubmitListener(all_inpus, passwords, title, datePicker));
		add(submit);
		setBackground(Color.WHITE);
		mainPanel.setSize(1200,800);
		setPreferredSize(new Dimension(1200,800));

	}
	public void CloseFrame(){
	    super.dispose();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SignUpPage frame = new SignUpPage();
		frame.setTitle("GUI Event Demo");
		frame.setSize(1200,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	class SubmitListener implements ActionListener {
	    private JTextField[] all_inputs;
	    private JPasswordField[] passwords;
	    private JComboBox<String> title;
	    private JDatePickerImpl datePicker;

	    public SubmitListener(JTextField[] all_inputs, JPasswordField[] passwords, JComboBox<String> title, JDatePickerImpl datePicker) {
	        this.all_inputs = all_inputs;
	        this.passwords = passwords;
	        this.title = title;
	        this.datePicker = datePicker;
	    }

	    public void actionPerformed(ActionEvent e) {
	    	JTextField random = new JTextField();
	    	Border border = random.getBorder();
	    	boolean allInputsValid = true;
	        // Retrieve values from text fields
	    	for (int i = 0; i < all_inputs.length; i++) {
	            if (all_inputs[i].getText().trim().isEmpty()) {
	                all_inputs[i].setBorder(new LineBorder(Color.RED, 1));
	                allInputsValid = false;
	            } else {
//	                all_inputs[i].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
	            	all_inputs[i].setBorder(border);
	            }
	        }
	    	
	   
	        if (passwords[0].getPassword().length == 0 || passwords[1].getPassword().length == 0) {
	            passwords[0].setBorder(new LineBorder(Color.RED, 1));
	            passwords[1].setBorder(new LineBorder(Color.RED, 1));
	            allInputsValid = false;
	        } else {
	            passwords[0].setBorder(border);
	            passwords[1].setBorder(border);
	        }

	        if (!allInputsValid) {
	            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
	            return; 
	        }
	        
	        


	        String password1 = new String(passwords[0].getPassword());
	        String password2 = new String(passwords[1].getPassword());
	        System.out.println("Password 1: " + password1);
	        System.out.println("Password 2: " + password2);

	  
	        if (!password1.equals(password2)) {
	        	 JOptionPane.showMessageDialog(null, "Passwords don't match.", "Alert", JOptionPane.WARNING_MESSAGE);
	        	 System.out.println("Passwords doesn't match");
	             return; 
	        } else {
	            System.out.println("Passwords match");
	        }
	        

	        String selectedTitle = (String) title.getSelectedItem();
	        System.out.println("Selected Title: " + selectedTitle);


	        Date selectedDate = (Date) datePicker.getModel().getValue();
	        if(selectedDate == null) {
				JOptionPane.showMessageDialog(null, "Please enter choose a date", "Alert", JOptionPane.WARNING_MESSAGE);
				return;
			}
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(selectedDate);
	        System.out.println("Date of Birth: " + formattedDate);

	        System.out.println("Submit button clicked...");
	        try {
	        	String email = all_inputs[8].getText();
				String query = "SELECT * FROM users where email = ? ";
				String url = "jdbc:src/project/airline.db";
//				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setString(1, email);
//				Statement stat = conn.createStatement();
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next()) {
//					JTextField[] all_inpus = {firstName, lastName, nationality, langauge, country, city, zipCode, address, email, code , phoneNumber};
//					System.out.println("firstName : " + all_inputs[0].getText());
//					System.out.println("lastName : " + all_inputs[1].getText());
//					System.out.println("nationality : " + all_inputs[2].getText());
//					System.out.println("langauge : " + all_inputs[3].getText());
//					System.out.println("country : " + all_inputs[4].getText());
//					System.out.println("city : " + all_inputs[5].getText());
//					System.out.println("zipCode : " + all_inputs[6].getText());
//					System.out.println("address : " + all_inputs[7].getText());
//					System.out.println("email : " + all_inputs[8].getText());
//					System.out.println("code : " + all_inputs[9].getText());
//					System.out.println("phoneNumber : " + all_inputs[10].getText());
//					System.out.println("selectedTitle : " + selectedTitle);
//					System.out.println("Date of Birth: " + formattedDate);
					JOptionPane.showMessageDialog(null, "The email address you entered is already in use.", "Alert", JOptionPane.WARNING_MESSAGE);
				}else {
					String firstName  = all_inputs[0].getText();
					String lastName  = all_inputs[1].getText();
					String nationality = all_inputs[2].getText();
					String langauge = all_inputs[3].getText();
					String country = all_inputs[4].getText();
					String city = all_inputs[5].getText();
					String zipCode = all_inputs[6].getText();
					String address = all_inputs[7].getText();
					String code = all_inputs[9].getText();
					String phoneNumber = all_inputs[10].getText();
					String Date_of_Birth = formattedDate;
//					String password = passwords[0].getPassword();
					
					String query2 = "INSERT INTO users (firstName, lastName, nationality, language, birthDate, title, email, password, country, city, zipCode, address, code, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
//					Class.forName("org.sqlite.JDBC");
					Connection conn2 = DriverManager.getConnection(url);
					PreparedStatement preparedStatement2 = conn2.prepareStatement(query2);
				    preparedStatement2.setString(1, firstName);
				    preparedStatement2.setString(2, lastName);
				    preparedStatement2.setString(3, nationality);
				    preparedStatement2.setString(4, langauge);
				    preparedStatement2.setString(5, Date_of_Birth);
				    preparedStatement2.setString(6, selectedTitle);
				    preparedStatement2.setString(7, email);
				    preparedStatement2.setString(8, password1);
				    preparedStatement2.setString(9, country);
				    preparedStatement2.setString(10, city);
				    preparedStatement2.setString(11, zipCode);
				    preparedStatement2.setString(12, address);
				    preparedStatement2.setString(13, code);
				    preparedStatement2.setString(14, phoneNumber);
//					Statement stat = conn.createStatement();
					preparedStatement2.executeUpdate();
					JOptionPane.showMessageDialog(null, "Congratss you have created a new account sucessfully.", "Alert", JOptionPane.WARNING_MESSAGE);
					CloseFrame();
					
				}
				
//				while(rs.next()) {
//					flights1.add(new Flight(rs.getInt("id"), rs.getString("departure"), rs.getString("destination"),rs.getString("departureTime"),rs.getString("arrivelTime"),rs.getString("flightDuration"), rs.getString("departureAirport"), rs.getString("destinationAirport"), rs.getInt("ecoPrice"),rs.getInt("busPrice"), rs.getString("flightDate")));
//				}
				
				
			}catch (SQLException e1) {
			    e1.printStackTrace();
			}
	    }
	}

}



	

