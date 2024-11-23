package project;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class PaymentDetails extends JPanel {
	private ImageIcon cardIcon = new ImageIcon("src/project/resources/credit-card-black.png");
	private String[] inputNames3 = {"Card holder name", "Card number","Expiry date(month/year)", "CVC"};

	JTextField cvc = new JTextField();
	JTextField cardNumber = new JTextField();
	JTextField cardHolderName = new JTextField();
	JTextField cardExpDate = new JTextField();
	JTextField[] all_inputs = {cvc, cardNumber, cardHolderName, cardExpDate};
	
	private JButton submit = new JButton("Complete booking");
	
	public PaymentDetails() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		
		// Payment info inputs and panels
		JPanel paymentInfoMainPanel = new JPanel(new BorderLayout(10,10));
		JPanel paymentInfoPanel = new JPanel(new GridLayout(2,2,5,5));
		JLabel paymentLabel = new JLabel(cardIcon, JLabel.LEFT);
		paymentInfoPanel.setBackground(Color.WHITE);
		paymentLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		paymentLabel.setText("Payment details");
		paymentLabel.setBackground(Color.WHITE);
		paymentInfoMainPanel.setBackground(Color.WHITE);
	
		Component[] inputs3 = {cardNumber, cardHolderName, cardExpDate, cvc};
		
		for(int i = 0; i < inputs3.length; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			inputs3[i].setPreferredSize(new Dimension(350, 50));
			panel.add(new JLabel(inputNames3[i]), BorderLayout.NORTH);
			panel.add(inputs3[i], BorderLayout.CENTER);
			panel.setBackground(Color.WHITE);
			paymentInfoPanel.add(panel);
		}
		paymentInfoMainPanel.add(paymentLabel, BorderLayout.NORTH);
		paymentInfoMainPanel.add(paymentInfoPanel, BorderLayout.SOUTH);
		add(paymentInfoMainPanel);
		
		submit.setPreferredSize(new Dimension(500, 50));
		submit.setBackground(new Color(215,53, 59));
		submit.setForeground(Color.WHITE);
		submit.setOpaque(true);
		submit.setFocusPainted(false);
		submit.setBorderPainted(false);
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkValidity() == true)
					System.out.println(getInformation());
				else	
					System.out.println("not valid");
			}
		});
//		add(submit);
		
		setBackground(Color.WHITE);
//		setSize(400,200);
		
	}
	public boolean checkValidity() {
		JTextField random = new JTextField();
    	Border border = random.getBorder();
    	boolean allValid = true;
		for(int i=0; i< all_inputs.length; i++) {
			if(all_inputs[i].getText().equals("")) {
				all_inputs[i].setBorder(new LineBorder(Color.RED, 1));
//				JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
//				return false;
				allValid = false;
			}else {
				all_inputs[i].setBorder(border);
			}
		}
		if(!allValid) {
//			JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "you are all set.", "Alert", JOptionPane.WARNING_MESSAGE);
		return true;
	}
	public String[] getInformation() {
		String[] inputs = {cardNumber.getText(), cardHolderName.getText(), cardExpDate.getText(), cvc.getText()};
		return inputs;
	}

	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		PassengerInfo frame = new PassengerInfo();
//		frame.setTitle("GUI Event Demo");
//		frame.setSize(900,700);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//	}

}
