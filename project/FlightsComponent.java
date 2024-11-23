package project;
import javax.swing.*;
import java.awt.*;

public class FlightsComponent extends JPanel{
//	private Flight[] flights;
	private ImageIcon betw = new ImageIcon("src/project/resources/betw.png");
	private static boolean isFirstEcoSelected = false;
	JRadioButton ecoRadio;
	JRadioButton busRadio;
	public FlightsComponent(Flight flight,ButtonGroup classesGroup){
		
//		flights = new Flight[5];
//		String[] airports = {"IST", "ESB"};
//		flights[0] = new Flight("Istanbul", "Ankara", "14:00", "15:30", "1h 30m", airports, 2000, 6000);
//		flights[1] = new Flight("Istanbul", "Ankara", "17:00", "18:30", "1h 30m", airports, 3000, 7000);
//		flights[2] = new Flight("Istanbul", "Ankara", "19:00", "21:30", "1h 30m", airports, 1500, 5000);
		ecoRadio = new JRadioButton();
	    busRadio = new JRadioButton();
	    
		JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Adding padding
//        mainPanel.setBackground(Color.WHITE);
		JPanel outerPanel = new JPanel();
		mainPanel.add(outerPanel, BorderLayout.WEST);
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
		outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		JPanel innerPanel2 = new JPanel();
		JLabel label1 = new JLabel("<html><div>" + "<span style='font-weight: bold;'> " + flight.getDepartureTime() + "</span>" + "<br>" + flight.getDepartureAirport() + "<br>" + flight.getDeparture() +"</div></html>");
		JLabel label2 = new JLabel("<html><div>" + "<span style='font-weight: bold;'> " + flight.getArrivelTime() + "</span>" + "<br>" + flight.getDestinationAirports() + "<br>" + flight.getDestination() +"</div></html>");
		JLabel label3 = new JLabel("<html><div>" + "Flight duration " +"<span style='font-weight: bold;'> " +flight.getFlightDuration()+ " </span> " + "Aircraft type: <span style='font-weight: bold;'> Airbus A319-100 </span> - Narrow-body </div></html>");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 9));
		innerPanel1.setPreferredSize(new Dimension(370, 70));
		innerPanel1.add(label1, BorderLayout.WEST);
		innerPanel1.add(label2, BorderLayout.EAST);
		innerPanel1.add(new JLabel(betw), BorderLayout.CENTER);
		innerPanel1.setBackground(Color.WHITE);
		innerPanel2.setBackground(Color.WHITE);
		innerPanel2.add(label3);
		outerPanel.add(innerPanel1);
		outerPanel.add(innerPanel2);
		outerPanel.setBackground(Color.WHITE);
		outerPanel.setSize(500,200);
		
		JPanel classPanel1 = new JPanel(new BorderLayout());
		JPanel classPanel2 = new JPanel(new BorderLayout());
		JLabel classText1 = new JLabel("ECONOMY", JLabel.LEFT);
		JLabel classText2 = new JLabel("BUSINESS", JLabel.LEFT);
		JPanel classInner1 = new JPanel();
		JPanel classInner2 = new JPanel();
		JLabel classPrice1 = new JLabel("<html><div>" + "<small>" +"Per passenger" + "</small>" + "<br>" + "<span style='font-weight: bold;'> TRY " + flight.getEcoPrice() + "</span>" + "</div></html>");
		JLabel classPrice2 = new JLabel("<html><div>" + "<small>" +"Per passenger" + "</small>" + "<br>" + "<span style='font-weight: bold;'> TRY " + flight.getBusPrice() + "</span>" + "</div></html>");
	
		classText1.setBackground(new Color(224,228,236));
		classText2.setBackground(new Color(245,234,229));
		classText1.setOpaque(true);
		classText2.setOpaque(true);

		classPanel1.add(classText1, BorderLayout.NORTH);
		classPanel1.add(classInner1, BorderLayout.CENTER);
		classInner1.add(ecoRadio);
		classInner1.add(classPrice1);
		classInner1.setBackground(Color.WHITE);
		mainPanel.add(Box.createHorizontalStrut(10));
		mainPanel.add(classPanel1, BorderLayout.EAST);
		
		classPanel2.add(classText2, BorderLayout.NORTH);
		classPanel2.add(classInner2, BorderLayout.CENTER);
		classInner2.add(busRadio);
		classInner2.add(classPrice2);
		classInner2.setBackground(Color.WHITE);
		
		ButtonGroup radioGroup = new ButtonGroup();
		classesGroup.add(busRadio);
		classesGroup.add(ecoRadio);
		
		JPanel classesPanel = new JPanel(new BorderLayout());
		classesPanel.add(classPanel1, BorderLayout.WEST);
		classesPanel.add(Box.createHorizontalStrut(10));
		classesPanel.add(classPanel2, BorderLayout.EAST);
		
		mainPanel.add(Box.createHorizontalStrut(10));
		mainPanel.add(classesPanel, BorderLayout.LINE_END);
		
		if (!isFirstEcoSelected) {
            ecoRadio.setSelected(true);
            isFirstEcoSelected = true; 
        }
		
		add(mainPanel);
		setSize(400,200);
		
		
	}
	

	public boolean isEcoSelected() {
        return ecoRadio.isSelected();
    }

    public boolean isBusSelected() {
        return busRadio.isSelected();
    }

}
class Flight{
	private String departure, destination, departureTime, arrivelTime, flightDuration, departureAirport, destinationAirports, flightDate;
//	private String[] airports;
	int id;
	int busPrice, ecoPrice;
	
	public Flight(int id, String departure, String destination, String departureTime, String arrivelTime, String flightDuration, String departureAirport, String destinationAirports, int ecoPrice, int busPrice, String flightDate) {
		this.id = id;
		this.departure = departure;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivelTime = arrivelTime;
		this.flightDuration = flightDuration;
		this.departureAirport = departureAirport;
		this.destinationAirports = destinationAirports;
		this.ecoPrice = ecoPrice;
		this.busPrice = busPrice;
		this.flightDate = flightDate;
	}
	
//	public Flight(Object object) {
//		// TODO Auto-generated constructor stub
//	}

	public int getId() {
		return id;
	}
	public String getDeparture() {
		return departure;
	}
	public String getDestination() {
		return destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public String getArrivelTime() {
		return arrivelTime;
	}
	public String getFlightDuration() {
		return flightDuration;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public String getDestinationAirports() {
		return destinationAirports;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public float getBusPrice() {
		return busPrice;
	}
	public float getEcoPrice() {
		return ecoPrice;
	}
}
