package project;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class InputGroup extends JPanel {
	private JRadioButton round = new JRadioButton("Round Trip");
	private JRadioButton oneWay = new JRadioButton("One way");
	private JTextField departure = new JTextField();
	private JTextField destination = new JTextField();
	private JTextField numPassengers = new JTextField();
	private ImageIcon arrow = new ImageIcon("src/project/resources/arrow.png");
	private JButton search = new JButton("Search flights", arrow);
	private ButtonGroup radioGroup = new ButtonGroup();
	
	Properties p = new Properties();
	
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//	Properties p2 = new Properties();
//	p.put("text.today", "Today");
//	p.put("text.month", "Month");
//	p.put("text.year", "Year");
	UtilDateModel model2 = new UtilDateModel();
	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
	JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
//	private JDatePickerImpl datePicker;
//	private JDatePickerImpl datePicker2;
	
	
	public InputGroup() {
		setLayout(new GridLayout(2,1));
		setPreferredSize(new Dimension(800,200));
		JPanel inputsPanel = new JPanel();
		JPanel radiosPanel = new JPanel();
		JPanel textPanel = new JPanel(); 
		JPanel panel1 = new JPanel(new BorderLayout()); 
		JPanel panel2 = new JPanel(new BorderLayout()); 
		JPanel panel3 = new JPanel(new BorderLayout()); 
		JLabel radiosText = new JLabel("                           Award ticket - Buy a ticket with Miles");
		departure.setPreferredSize(new Dimension(120, 50));
		destination.setPreferredSize(new Dimension(120, 50));
		numPassengers.setPreferredSize(new Dimension(120, 50));
		search.setPreferredSize(new Dimension(130, 70));
		search.setOpaque(true);
		search.setBorderPainted(false);
		search.setBackground(new Color(215,53, 59));
		search.setForeground(Color.WHITE);
		search.setVerticalTextPosition(AbstractButton.CENTER);
	    search.setHorizontalTextPosition(AbstractButton.LEADING); 
	    search.setFocusPainted(false);
	    search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    
	    
	    
	    p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		UtilDateModel model = new UtilDateModel();
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
////		Properties p2 = new Properties();
////		p.put("text.today", "Today");
////		p.put("text.month", "Month");
////		p.put("text.year", "Year");
//		UtilDateModel model2 = new UtilDateModel();
//		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
//		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());

//		datePicker.setPreferredSize(new Dimension(130, 70));
	
		oneWay.setSelected(true);
//		radiosPanel.setLayout(new BorderLayout());
//		panel1.setBackground(new Color(244, 246, 248));
//		panel2.setBackground(new Color(244, 246, 248));
//		panel3.setBackground(new Color(244, 246, 248));
//		departure.setBackground(new Color(244, 246, 248));
		panel1.add(new JLabel(" From"), BorderLayout.NORTH);
		panel1.add(departure, BorderLayout.SOUTH);
		panel2.add(new JLabel(" To"), BorderLayout.NORTH);
		panel2.add(destination, BorderLayout.SOUTH);
		panel3.add(new JLabel(" Passengers"), BorderLayout.NORTH);
		panel3.add(numPassengers, BorderLayout.SOUTH);
		departure.setHorizontalAlignment(JTextField.CENTER);
		destination.setHorizontalAlignment(JTextField.CENTER);
		numPassengers.setHorizontalAlignment(JTextField.CENTER);
		radioGroup.add(round);
		radioGroup.add(oneWay);
		radiosPanel.add(round);
		radiosPanel.add(oneWay);
		radiosPanel.add(radiosText);
		inputsPanel.add(radiosPanel);
		textPanel.add(panel1);
		textPanel.add(panel2);
		textPanel.add(datePicker);
		textPanel.add(panel3);
		textPanel.add(search);
		inputsPanel.setBackground(Color.WHITE);
		radiosPanel.setBackground(Color.WHITE);
		textPanel.setBackground(Color.WHITE);

		add(inputsPanel);
		add(textPanel);
		
//		System.out.println("Selected Radio Button: " + radioGroup.getSelection().getActionCommand());\
		
//		round.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("round selected");
//				round.setSelected(true);
//				textPanel.remove(panel3);
//				textPanel.remove(search);
//				textPanel.add(datePicker2);
//				textPanel.add(panel3);
//				textPanel.add(search);
//				textPanel.revalidate();
//				textPanel.repaint();
//			}
//		});
//		oneWay.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				textPanel.remove(panel3);
//				textPanel.remove(search);
//				textPanel.remove(datePicker2);
//				textPanel.add(panel3);
//				textPanel.add(search);
//				textPanel.revalidate();
//				textPanel.repaint();
//
//				System.out.println("one way selected");
//			}
//		});
		
		
		round.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("round selected");
		        round.setSelected(true);
		        textPanel.remove(panel3);
		        textPanel.remove(search);
		        textPanel.add(datePicker2);
		        textPanel.add(panel3);
		        textPanel.add(search);
		        textPanel.revalidate();
		        textPanel.repaint();
		        updateSearchListener(); // Update the SearchListener instance
		    }
		});

		oneWay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        textPanel.remove(panel3);
		        textPanel.remove(search);
		        textPanel.remove(datePicker2);
		        textPanel.add(panel3);
		        textPanel.add(search);
		        textPanel.revalidate();
		        textPanel.repaint();
		        updateSearchListener(); // Update the SearchListener instance
		        System.out.println("one way selected");
		    }
		});
		
		SearchListener listener = null;
        if (oneWay.isSelected()) {
            listener = new SearchListener(radioGroup, departure, destination, numPassengers, datePicker);
        } else {
            listener = new SearchListener(radioGroup, departure, destination, numPassengers, datePicker, datePicker2);
        }

        search.addActionListener(listener);
		
		
	}
	
	private void updateSearchListener() {
	    SearchListener listener;
	    if (oneWay.isSelected()) {
	        listener = new SearchListener(radioGroup, departure, destination, numPassengers, datePicker);
	    } else {
	        listener = new SearchListener(radioGroup, departure, destination, numPassengers, datePicker, datePicker2);
	    }
	    search.removeActionListener(search.getActionListeners()[0]); 
	    search.addActionListener(listener); 
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		InputGroup frame = new InputGroup();
//		frame.setTitle("Home Page");
//		frame.setSize(800,200);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//	}
	
	class SearchListener implements ActionListener{
		private String type;
		private ButtonGroup radioGroup;
		private JTextField departure;
		private JTextField destination;
		private JTextField numPassengers;
		private JDatePickerImpl datePicker;
		private JDatePickerImpl datePicker2;
		
		public SearchListener(ButtonGroup radioGroup, JTextField departure, JTextField destination, JTextField numPassengers, JDatePickerImpl datePicker) {
			this.type = round.isSelected() ? "round trip" : "one way";
			this.radioGroup = radioGroup;
			this.departure = departure;
			this.destination = destination;
			this.numPassengers = numPassengers;
			this.datePicker = datePicker;
			this.datePicker2 = null; 
		}
		public SearchListener(ButtonGroup radioGroup, JTextField departure, JTextField destination, JTextField numPassengers, JDatePickerImpl datePicker, JDatePickerImpl datePicker2) {
			this.type = round.isSelected() ? "round trip" : "one way";
			this.radioGroup = radioGroup;
			this.departure = departure;
			this.destination = destination;
			this.numPassengers = numPassengers;
			this.datePicker = datePicker;
			this.datePicker2 = datePicker2;
			
		}
		public void actionPerformed(ActionEvent e) {
//			Date selectedDate = (Date) datePicker.getModel().getValue();
//			System.out.println(selectedDate);
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	        String date = dateFormat.format(selectedDate);
//	        System.out.println(date);
			String from = departure.getText();
			String to = destination.getText();
			System.out.println(from);
			System.out.println(to);			
			if(from.equals("") || to.equals("")) {
				JOptionPane.showMessageDialog(null,"Please fill all the inputs", "Alert", JOptionPane.WARNING_MESSAGE);
				return;
			}
			int numPeople = 0;
	        try {
	            numPeople = Integer.parseInt(numPassengers.getText());
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null, "Please enter a valid number of passengers", "Alert", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
			from = from.substring(0, 1).toUpperCase() + from.substring(1);
			to = to.substring(0, 1).toUpperCase() + to.substring(1);
			numPeople = Integer.parseInt(numPassengers.getText());
//		    if(selectedDate == null) {
//		        JOptionPane.showMessageDialog(null, "Please choose a date", "Alert", JOptionPane.WARNING_MESSAGE);
//		        return;
//		    }
//
//		    if(datePicker2 != null && type.equals("round trip")) {
//		        Date selectedDate2 = (Date) datePicker2.getModel().getValue();
//		        System.out.println(type);
//		        System.out.println(selectedDate2);
//		        if(selectedDate2 == null) {
//		            JOptionPane.showMessageDialog(null, "Please choose a return date", "Alert", JOptionPane.WARNING_MESSAGE);
//		            return;
//		        }
//		    }
			System.out.println(type);
			String date2 = "";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (type.equals("one way")) {
	            if (datePicker.getModel().getValue() == null) {
	                JOptionPane.showMessageDialog(null, "Please choose a departure date", "Alert", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	        } 
			System.out.println(type);
			if (type.equals("round trip")) {
	            if (datePicker.getModel().getValue() == null || datePicker2.getModel().getValue() == null) {
	                JOptionPane.showMessageDialog(null, "Please choose both departure and return dates", "Alert", JOptionPane.WARNING_MESSAGE);
	                return;
	            }else {
	            	Date selectedDate2 = (Date) datePicker2.getModel().getValue();
	            	date2 = dateFormat.format(selectedDate2);
	            }
	        }
			System.out.println(date2);
			Date selectedDate = (Date) datePicker.getModel().getValue();
//			Date selectedDate2 = (Date) datePicker2.getModel().getValue();
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String date = dateFormat.format(selectedDate);
	       
//			String date2 = dateFormat.format(selectedDate2);
	        
	        
			System.out.println("you're going from "+ from + " to " + to + " on date " + date + " with # " + numPeople + " people");
//			if (!date2.isEmpty()) {
//			    // Construct CardLayoutTest with four parameters if date2 is not empty
//			    CardLayoutTest frame = new CardLayoutTest(from, to, date, date2);
//			    frame.setVisible(true);
//			    frame.setTitle("Flights from " + from + " to " + to);
//			    frame.setSize(1200, 800);
//			    frame.setLocationRelativeTo(null);
//			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			    frame.setVisible(true);
//			} else {
//			    // Construct CardLayoutTest with three parameters if date2 is empty
//			    CardLayoutTest frame = new CardLayoutTest(from, to, date);
//			    frame.setVisible(true);
//			    frame.setTitle("Flights from " + from + " to " + to);
//			    frame.setSize(1200, 800);
//			    frame.setLocationRelativeTo(null);
//			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			    frame.setVisible(true);
//			}
			CardLayoutTest frame = new CardLayoutTest(from, to, date, date2, numPeople);
		    frame.setVisible(true);
		    frame.setTitle("Flights from " + from + " to " + to);
		    frame.setSize(1200, 800);
		    frame.setLocationRelativeTo(null);
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    frame.setVisible(true);
//			JOptionPane.showMessageDialog(null,"you're going from "+ from + " to " + to + " on date " + date + " with # " + numPeople + " people", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	

}





class DateLabelFormatter extends AbstractFormatter {
	
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}
	
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		
		return "";
	}
	
}