package project;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestFlight extends JPanel{

	private ButtonGroup classesGroup = new ButtonGroup();
	ArrayList<Flight> flights1 = new ArrayList<Flight>();
	private ArrayList<FlightsComponent> flightComponents = new ArrayList<>();
	private float SelectedFlightPrice;
	private String SelectedFlightClass;

	public void populateFlights(String from,String to,String date, int numPassengers) {
		try {
			System.out.println(from);
			System.out.println(to);
			System.out.println(date);
			String query = "SELECT * FROM flights WHERE departure=? AND destination=? AND flightDate=?;";
			String url = "jdbc:sqlite:src/project/airline.db";
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, from);
			preparedStatement.setString(2, to);
			preparedStatement.setString(3, date);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				flights1.add(new Flight(rs.getInt("id"), rs.getString("departure"), rs.getString("destination"),rs.getString("departureTime"),rs.getString("arrivelTime"),rs.getString("flightDuration"), rs.getString("departureAirport"), rs.getString("destinationAirport"), rs.getInt("ecoPrice"),rs.getInt("busPrice"), rs.getString("flightDate")));
			}
			
			
		}catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public TestFlight(String from, String to, String date, int numPeople) {
		populateFlights(from, to, date, numPeople);
		setLayout(new GridLayout(5,1));
		if(flights1.size() == 0) {
			add(new JLabel("There is no flights found, try changing the cities for date :).", JLabel.CENTER));
		}
        for(int i = 0; i < flights1.size(); i++) {
//			add(new FlightsComponent(flights1.get(i), classesGroup));
			flightComponents.add(new FlightsComponent(flights1.get(i), classesGroup));
			add(flightComponents.get(i));
				
		}
        
		
	}
	public String getSelectedFlight() {
        for (int i = 0; i < flightComponents.size(); i++) {
            if (flightComponents.get(i).isEcoSelected() || flightComponents.get(i).isBusSelected()) {
            	SelectedFlightPrice = (flightComponents.get(i).isBusSelected() ? flights1.get(i).getBusPrice() : flights1.get(i).getEcoPrice());
            	SelectedFlightClass = (flightComponents.get(i).isBusSelected() ? "Business" : "Economy");
                return "<html> Departure <br/>"  + flights1.get(i).getDepartureAirport() + " - " + flights1.get(i).getDestinationAirports() + " · " + flights1.get(i).getFlightDate() + "<br/>" + "Departures " + flights1.get(i).getDepartureTime() + " | arrives " + flights1.get(i).getArrivelTime() + "</html>";
            }
        }
		return " ";
    }

	public int getSelectedId() {//i am out of names for real lmao ¯\_(ツ)_/¯ ;)
        for (int i = 0; i < flightComponents.size(); i++) {
            if (flightComponents.get(i).isEcoSelected() || flightComponents.get(i).isBusSelected()) {
                return flights1.get(i).getId();
            }
        }
		return 0;
    }
	public float getSelectedFlightPrice() {
		getSelectedFlight();
		return SelectedFlightPrice;
	}
	public String getSelectedFlightClass() {
		getSelectedFlight();
		return SelectedFlightClass;
	}
	
	public String getSelectedFlightInfo() {
        for (int i = 0; i < flightComponents.size(); i++) {
            if (flightComponents.get(i).isEcoSelected() || flightComponents.get(i).isBusSelected()) {
            	Flight flight = flights1.get(i);
            	return "From: " + flight.getDeparture() + " " + " To: " + flight.getDestination() + " \n" +
            			"Date: " + flight.getFlightDate() + "\n" + "Flight number: " + flight.getId() + "\n"+
            			"Departure time: " + flight.getDepartureTime() + ", Arrival Time: " + flight.getArrivelTime() + "\n" + 
            			"Departure airport: " + flight.getDepartureAirport() + " Arrival Airport " + flight.getDestinationAirports() + "\n" +
            			 "Class: " + getSelectedFlightClass() + "\nPrice: " + getSelectedFlightPrice() + "\n";
            }
        }
		return " ";
    }
	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
//		TestFlight frame = new TestFlight();
//		frame.setTitle("Flights");
//		frame.setSize(800,700);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//	}

}
