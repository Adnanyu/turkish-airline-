package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingInPage extends JFrame {
	
	private JTextField email = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton submit = new JButton("Sign in");
	
	public SingInPage() {
		JPanel panel = new JPanel();
		JPanel emailPanel = new JPanel(new BorderLayout());
		JPanel passPanel = new JPanel(new BorderLayout());
		JLabel textLabel = new JLabel("Aren’t you a member of Miles&Smiles?");
//		JPanel inputsPanel = new JPanel(new BorderLayout());
		emailPanel.add(new JLabel(" Email"), BorderLayout.NORTH);
		emailPanel.add(email, BorderLayout.SOUTH);
		passPanel.add(new JLabel(" Passwords"), BorderLayout.NORTH);
		passPanel.add(password, BorderLayout.SOUTH);
		submit.setPreferredSize(new Dimension(380, 50));
		email.setPreferredSize(new Dimension(380, 60));
		password.setPreferredSize(new Dimension(380, 60));
		textLabel.setFont(new Font("SansSerif", Font.ITALIC, 18));;
		textLabel.setAlignmentY(0.5f);
		submit.setOpaque(true);
		submit.setBorderPainted(false);
		submit.setBackground(new Color(215,53, 59));
		submit.setForeground(Color.WHITE);
		submit.setFocusPainted(false);
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submit.setFont(new Font("SansSerif", Font.BOLD, 18));
//		inputsPanel.add(emailPanel,BorderLayout.NORTH);
//		inputsPanel.add(PassPanel, BorderLayout.SOUTH);
		panel.add(emailPanel);
		panel.add(passPanel);
		panel.add(submit);
		panel.add(textLabel);
		add(panel);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email1 = email.getText();
				String pass = new String(password.getPassword());
				
				if(email1.isEmpty() || pass.isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {

					String query = "SELECT * FROM users where email = ? AND password = ? ";
					String url = "jdbc:sqlite:src/project/airline.db";
//					Class.forName("org.sqlite.JDBC");
					Connection conn = DriverManager.getConnection(url);
					PreparedStatement preparedStatement = conn.prepareStatement(query);
				    preparedStatement.setString(1, email1);
				    preparedStatement.setString(2, pass);
//					Statement stat = conn.createStatement();
					ResultSet rs = preparedStatement.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "The password or email are incorrect.", "Alert", JOptionPane.WARNING_MESSAGE);
						return;
					}else {
						System.out.println(rs.getString("firstName"));
						JOptionPane.showMessageDialog(null, "Welcome back "+ rs.getString("firstName"), "Alert", JOptionPane.WARNING_MESSAGE);
					}
					
				}catch (SQLException e1) {
				    e1.printStackTrace();
				}
			}
		});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingInPage frame = new SingInPage();
		frame.setTitle("Sign in");
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
