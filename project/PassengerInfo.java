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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PassengerInfo extends JPanel {
	private ImageIcon userIcon = new ImageIcon("src/project/resources/user-grey.png");
	private ImageIcon phoneIcon = new ImageIcon("src/project/resources/phone-grey.png");
	private ImageIcon cardIcon = new ImageIcon("src/project/resources/credit-card-black.png");
	private String[] inputNames = {"First Name", "Last Name","Date of birth", "Title"};
	private String[] inputNames2 = {"Email adress", "Area code","Phone number", "Address"};
	private String[] inputNames3 = {"Card holder name", "Card number","Expiry date(month/year)", "CVC"};
	JTextField firstName = new JTextField();
	JTextField lastName = new JTextField();
	JTextField email = new JTextField();
	JTextField areaCode = new JTextField();
	JTextField phone = new JTextField();
	JTextField address = new JTextField();
	JTextField[] all_inputs = {firstName, lastName, email, areaCode, phone, address};
	
	private JButton submit = new JButton("Complete booking");
	private JRadioButton mr = new JRadioButton("Mr");
	private JRadioButton ms = new JRadioButton("Ms");
	Properties p = new Properties();
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	
	public PassengerInfo() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		// personal info inputs and panels
		JPanel titlePanel = new JPanel();
		JPanel passengerInfoMainPanel = new JPanel(new BorderLayout(10,10));
		JPanel passengerInfoPanel = new JPanel(new GridLayout(2,2,5,5));
		JLabel passengerLabel = new JLabel(userIcon, JLabel.LEFT);
		passengerLabel.setText("<html><div> <span style='font-weight: bold'; > Passenger information</span> <span style='color: rgb(103,114, 127);'> | Adult </span> </div></html>");
		passengerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		passengerInfoPanel.setBackground(Color.WHITE);
		passengerLabel.setBackground(Color.WHITE);
		passengerInfoMainPanel.setBackground(Color.WHITE);
		
//		search.setVerticalTextPosition(AbstractButton.CENTER);
//	    search.setHorizontalTextPosition(AbstractButton.LEADING); 
		ButtonGroup radioGroup = new ButtonGroup();
//		passengerInfoPanel.setPreferredSize(new Dimension(500, 200));
		radioGroup.add(mr);
		radioGroup.add(ms);
		titlePanel.add(mr);
		titlePanel.add(ms);
		mr.setSelected(true);
		titlePanel.setBackground(Color.WHITE);
		
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePicker.setBackground(Color.WHITE);
		
		JComponent[] inputs = {firstName, lastName, datePicker, titlePanel};
		
		for(int i = 0; i < inputs.length; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			inputs[i].setPreferredSize(new Dimension(350, 50));
			panel.add(new JLabel(inputNames[i]), BorderLayout.NORTH);
			panel.add(inputs[i], BorderLayout.CENTER);
			panel.setBackground(Color.WHITE);
			passengerInfoPanel.add(panel);
		}
		passengerInfoMainPanel.add(passengerLabel, BorderLayout.NORTH);
		passengerInfoMainPanel.add(passengerInfoPanel, BorderLayout.SOUTH);
		
		add(passengerInfoMainPanel);
		
		
		// contact info inputs and panels
		JPanel contactInfoMainPanel = new JPanel(new BorderLayout(10,10));
		JPanel contactInfoPanel = new JPanel(new GridLayout(2,2,5,5));
		JLabel ContactLabel = new JLabel(phoneIcon, JLabel.LEFT);
		contactInfoPanel.setBackground(Color.WHITE);
		ContactLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		ContactLabel.setText("Contact details");
		ContactLabel.setBackground(Color.WHITE);
		contactInfoMainPanel.setBackground(Color.WHITE);
	
		Component[] inputs2 = {email, areaCode, phone, address};
		
		for(int i = 0; i < inputs2.length; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			inputs2[i].setPreferredSize(new Dimension(350, 50));
			panel.add(new JLabel(inputNames2[i]), BorderLayout.NORTH);
			panel.add(inputs2[i], BorderLayout.CENTER);
			panel.setBackground(Color.WHITE);
			contactInfoPanel.add(panel);
		}
		contactInfoMainPanel.add(ContactLabel, BorderLayout.NORTH);
		contactInfoMainPanel.add(contactInfoPanel, BorderLayout.SOUTH);
		add(contactInfoMainPanel);
		
		
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
			if(all_inputs[i].getText().equals("") || datePicker.getModel().getValue() == null) {
				all_inputs[i].setBorder(new LineBorder(Color.RED, 1));

				allValid = false;
			}else {
				all_inputs[i].setBorder(border);
				datePicker.setBorder(border);
			}
		}
		if(!allValid) {
//			JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
//		JOptionPane.showMessageDialog(null, "you are all set.", "Alert", JOptionPane.WARNING_MESSAGE);
		return true;
	}
	public String getInformation() {
		return "Name: " + getTitle() + " "+ getFirstName() + " " + getLastName() + "\n" +
	"Birth date: " + getBirthDate() + "\n" +
	"Phone number: " + getAreaCode() + " " + getPhoneNumber() + "\n" + "Email: "  + getEmail() + "\n" + "Address: " + getAddress() + "\n";
	}
	
	public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getBirthDate() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date selectedDate = (Date) datePicker.getModel().getValue();
        String date = dateFormat.format(selectedDate);
        return date;
    }

    public String getTitle() {
        return (mr.isSelected() ? "Mr." : "Ms.");
    }

    public String getEmail() {
        return email.getText();
    }


    public String getAddress() {
        return address.getText();
    }

    public String getAreaCode() {
        return areaCode.getText();
    }

    public String getPhoneNumber() {
        return phone.getText();
    }



}
