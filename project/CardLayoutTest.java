package project;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardLayoutTest extends JFrame {

    private static final int firstIndex = 1;
    private int currentCard = firstIndex;
    private CardLayout cardLayout;
    JButton previousButton = new JButton("Back");
    JButton nextButton = new JButton("Continue");
    JPanel cardPanel = new JPanel();
    JLabel btnText = new JLabel();
    JLabel btnPriceText = new JLabel();
    JLabel btnText2 = new JLabel();
    TestFlight flighstPanel;
    TestFlight flighstPanel2;
    PassengerInfo PassengerInfo;
    PaymentDetails paymentInfo;
    JPanel buttonPanel = new JPanel();
    private float total = 0;
    int numPeople1;
    List<JComponent> cards = new ArrayList<>();
    ArrayList<PassengerInfo> passengers = new ArrayList<PassengerInfo>();
    public CardLayoutTest(String from, String to, String date, String date2, int numPeople) {
    	cardPanel = new JPanel();
    	cardLayout = new CardLayout();
    	cardPanel.setLayout(cardLayout);
    	numPeople1 = numPeople;
    	flighstPanel = new TestFlight(from, to, date, numPeople);
        cards.add(flighstPanel);

        if (!date2.isEmpty()) {
        	flighstPanel2 = new TestFlight(to, from, date2, numPeople);
            cards.add(flighstPanel2);
        }
        PassengerInfo = new PassengerInfo();
        for (int i = 0; i < numPeople; i++) {
        	passengers.add(new PassengerInfo());
            cards.add(passengers.get(i));
        }
        paymentInfo = new PaymentDetails();
        cards.add(paymentInfo);

        for (int i = 0; i < cards.size(); i++) {
            cardPanel.add(cards.get(i), Integer.toString(i + 1));
        }
        
        
        previousButton.setPreferredSize(new Dimension(130, 70));
        previousButton.setOpaque(true);
        previousButton.setBorderPainted(false);
        previousButton.setBackground(new Color(215, 53, 59));
        previousButton.setForeground(Color.WHITE);
        previousButton.setFocusPainted(false);
        previousButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        nextButton.setPreferredSize(new Dimension(130, 70));
        nextButton.setOpaque(true);
        nextButton.setBorderPainted(false);
        nextButton.setBackground(new Color(215, 53, 59));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(previousButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnText);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnText2);   
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(btnPriceText);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(nextButton);
        buttonPanel.setBackground(new Color(37, 43, 55));
        
        nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleNextButton();
			}
		});
        previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handlePreviousButton();
			}
		});
        

        add(buttonPanel, BorderLayout.SOUTH);
        add(cardPanel, BorderLayout.CENTER);
    }

         
        


    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(130, 70));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(new Color(215, 53, 59));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void handleNextButton() {

    	if (currentCard == cards.size()) {
            nextButton.setText("Complete Booking");
            nextButton.setPreferredSize(new Dimension(160, 70));
            System.out.println("this is payment part");
            boolean paymentValid = paymentInfo.checkValidity();
            if (!paymentValid) {
                // Display a message indicating invalid payment details
                JOptionPane.showMessageDialog(null, "Please fill in all required payment details1111.", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }else {
            	for( int i = 0; i < passengers.size(); i++) {
            		try {
            			String url = "jdbc:sqlite:src/project/airline.db";
            			String query = "INSERT INTO tickets (firstName, lastName, birthDate, title, email, address, code, phoneNumber, flightId,  returnFlight, paymentCardNumber, paymentCardHolderName, totalPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            			Connection conn = DriverManager.getConnection(url);
            			PreparedStatement preparedStatement2 = conn.prepareStatement(query);
            			preparedStatement2.setString(1, passengers.get(i).getFirstName());
            			preparedStatement2.setString(2, passengers.get(i).getLastName());
            			preparedStatement2.setString(3, passengers.get(i).getBirthDate());
            			preparedStatement2.setString(4, passengers.get(i).getTitle());
            			preparedStatement2.setString(5, passengers.get(i).getEmail());
            			preparedStatement2.setString(6, passengers.get(i).getAddress());
            			preparedStatement2.setString(7, passengers.get(i).getAreaCode());
            			preparedStatement2.setString(8, passengers.get(i).getPhoneNumber());
            			preparedStatement2.setInt(9, flighstPanel.getSelectedId());
            			if(flighstPanel2 != null ) {
            				preparedStatement2.setInt(10, flighstPanel2.getSelectedId());            				
            			}else {
            				preparedStatement2.setNull(10, Types.NULL);
            			}
            			preparedStatement2.setString(11, paymentInfo.getInformation()[1]);
            			preparedStatement2.setString(12, paymentInfo.getInformation()[0]);
            			preparedStatement2.setFloat(13, (flighstPanel.getSelectedFlightPrice() + (flighstPanel2 != null ? flighstPanel2.getSelectedFlightPrice() : 0)));
            			preparedStatement2.executeUpdate();
            			JOptionPane.showMessageDialog(null, "Congratss you have booked your flight succesfully.", "Alert", JOptionPane.WARNING_MESSAGE);
            			
            			String ticket = passengers.get(i).getInformation();
            			String ticketFlightInfo = flighstPanel.getSelectedFlightInfo();
            			String ticketFlight2Info = flighstPanel2 != null ? flighstPanel2.getSelectedFlightInfo() : "";
            			String totalPrice = "Total price: " + Float.toString(flighstPanel.getSelectedFlightPrice() + (flighstPanel2 != null ? flighstPanel2.getSelectedFlightPrice() : 0));
            			String PaymentInfo = "Card number: " + paymentInfo.getInformation()[1] + "\n" + "Card Holder name: " + paymentInfo.getInformation()[0] + "\n" +
            					"Card expire date: " + paymentInfo.getInformation()[2] + "\n";
            			
            			String path = "/Users/adnan/desktop/tickets/ticket"+passengers.get(i).getFirstName()+ passengers.get(i).getPhoneNumber()+".txt";
            			
            			try {
            				BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            				writer.write("Turkish Airlines - Online Ticket - Information Message \n");
            				writer.newLine();
            				writer.write("Personale info \n");
            				writer.write(ticket);
            				writer.newLine();
            				writer.write("flight info \n");
            				writer.write(ticketFlightInfo);
            				if(!ticketFlight2Info.equals("")) {
            					writer.newLine();
            					writer.write("Return flight info");
            					writer.newLine();
            					writer.write(ticketFlight2Info);
            				}
            				writer.newLine();
            				writer.write("Payment info");
            				writer.newLine();
            				writer.write(PaymentInfo);
            				writer.newLine();
            				writer.write(totalPrice);
            				writer.newLine();
            				writer.newLine();
            				writer.write("Iyi yolculuklar :) \n");
            				writer.newLine();
//            				writer.write("side note: my project deserves 110% to be honest :) \n");
            				writer.close();
            				
            			}catch(IOException ex) {
            				ex.printStackTrace();
            			}
//					CloseFrame();
            		}catch(SQLException e) {
            			e.printStackTrace();
            		}
            	}
            }
        }
        
        JComponent currentCardComponent = cards.get(currentCard - 1);
        if (currentCard < cards.size()) {
            if (currentCardComponent instanceof PassengerInfo ) {
                PassengerInfo passengerInfo = (PassengerInfo) currentCardComponent;
                if (!passengerInfo.checkValidity() ) {
                    // Display a message or perform an action indicating invalid input
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Alert", JOptionPane.WARNING_MESSAGE);
                    System.out.println("please fill all the inputs");
                    return;
                }
            }
            
            if (currentCardComponent instanceof TestFlight) {
            	
            	TestFlight flight = (TestFlight) currentCardComponent;
                total += flight.getSelectedFlightPrice() * numPeople1;
                System.out.println(flight.getSelectedFlightPrice());
                System.out.println(flight.getSelectedFlightPrice());
                btnText.setForeground(Color.WHITE);
                btnText2.setForeground(Color.WHITE);
                btnPriceText.setForeground(Color.WHITE);
                btnText.setText(flighstPanel.getSelectedFlight());
                btnText2.setText(flighstPanel2 != null ? flighstPanel2.getSelectedFlight() : "");
//                float flight2Price = flighstPanel2.getSelectedFlightPrice();
//                float total2 = (flighstPanel.getSelectedFlightPrice() * numPeople1) + (flighstPanel2 != null ? flighstPanel2.getSelectedFlightPrice() * numPeople1 : 0);
                btnPriceText.setText("<html> price for " + numPeople1 + " passenger(s) <br> TL " + (flighstPanel.getSelectedFlightPrice() + (flighstPanel2 != null ? flighstPanel2.getSelectedFlightPrice() : 0)) * numPeople1 + "</htlm>");
                btnText.setVisible(true);
                btnText2.setVisible(true);
                btnPriceText.setVisible(true);
            }

            currentCard++;
            cardLayout.show(cardPanel, Integer.toString(currentCard));
        }
    }

    	private void handlePreviousButton() {
    		 if (currentCard == 0) {
    			 total =0;
    		 }
    	  if (currentCard > 1) {
    		
    	    currentCard--;
    	    cardLayout.show(cardPanel, Integer.toString(currentCard)); // Use cardPanel here
    	  }
    	  if(currentCard != cards.size()) {
    		  nextButton.setText("Continue"); // Use cardPanel here
      	  }
    	}
    	


}

